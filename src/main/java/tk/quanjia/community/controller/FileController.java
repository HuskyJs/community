package tk.quanjia.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tk.quanjia.community.dto.FileDTO;
import tk.quanjia.community.provider.HFileResult;
import tk.quanjia.community.provider.HuaweiCloudProvider;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Controller
public class FileController {
    @Autowired
    private HuaweiCloudProvider huaweiCloudProvider;
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        System.out.println("文件为："+ file.getOriginalFilename());
        assert 0==0;
        try {
            HFileResult hFileResult = huaweiCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());//上传文件
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(hFileResult.getFileUrl());
            return fileDTO;
        } catch (Exception e) {
            log.error("upload error", e);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败");
            return fileDTO;
        }

    }
}

