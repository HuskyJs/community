package tk.quanjia.community.provider;

import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.exception.ObsException;
import com.obs.services.model.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.quanjia.community.utils.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Service
public class HuaweiCloudProvider {
    private String endPoint = "https://obs.cn-east-3.myhuaweicloud.com";
    private String accessKey = "JOTO9PWRAB5RZRIWEKEQ";
    private String secretKey = "3Cb0Bt0xOHdMPT8bD2BP0EJb6CApbUvoKZ3DtHJT";
    private String bucketName = "myimg-rqj";

    /**
     * 通过 HTTP 图片 URL 上传
     *
     * @param url HTTP 图片 URL
     * @return
     * @throws FileNotFoundException
     */
    public HFileResult upload(String url) {
        File newFile = FileUtils.newFile(url);
        assert newFile != null;
        HFileResult fileResult;
        try {
            fileResult = upload(new FileInputStream(newFile), "image/png", newFile.getName());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("new file exception", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileUtils.deleteFile(newFile);
        return fileResult;
    }

    public HFileResult upload(InputStream inputStream, String mimeType, String fileName) throws IOException {
        ObsClient obsClient = new ObsClient(accessKey, secretKey, endPoint);//初始化


        //防止每次放进来的文件重名，设置随机数重设文件名字
        String keyName = FileUtils.newUUIDFileName(fileName);

        // 上传图片  设置对象MIME类型
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(mimeType);

        // /待上传的本地文件路径，需要指定到具体的文件名
        PutObjectRequest request = new PutObjectRequest();
        request.setBucketName(bucketName);//桶名
        request.setObjectKey(keyName);//对象名
        request.setInput(inputStream);//文件输入流
        request.setMetadata(metadata);//设置对象MIME类型
        request.setProgressListener(new ProgressListener() {
            @Override
            public void progressChanged(ProgressStatus status) {
                // 获取上传平均速率
                System.out.println("AverageSpeed:" + status.getAverageSpeed());
                // 获取上传进度百分比
                System.out.println("TransferPercentage:" + status.getTransferPercentage());
            }
        });
        // 每上传1MB数据反馈上传进度
        request.setProgressInterval(1024 * 1024L);
        obsClient.putObject(request);

        long expireSeconds =(3600 * 24 * 365);

        TemporarySignatureRequest requestDown = new TemporarySignatureRequest(HttpMethodEnum.GET, expireSeconds);
        requestDown.setBucketName(bucketName);
        requestDown.setObjectKey(keyName);

        // 生成临时授权URL
        TemporarySignatureResponse response = obsClient.createTemporarySignature(requestDown);
        System.out.println(response.getSignedUrl());

        HFileResult hFileResult = new HFileResult();
        hFileResult.setFileName(keyName);
        hFileResult.setFileUrl(response.getSignedUrl());


        return hFileResult;

    }


}
