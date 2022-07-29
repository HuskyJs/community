package tk.quanjia.community.provider;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Data
@Service
public class HuaweiFileProperties {
    private String endPoint="https://obs.cn-east-3.myhuaweicloud.com";
    private String accessKey ="JOTO9PWRAB5RZRIWEKEQ";
    private String secretKey="3Cb0Bt0xOHdMPT8bD2BP0EJb6CApbUvoKZ3DtHJT";
    private String bucketName="myimg-rqj";
//    private String uploadDomain;
//    private String downloadDomain;

//    /**
//     * bucket 类型，默认可以不填，可选参数 public 和 private
//     */
//    private String bucketType;
//
//    /**
//     * bucketType 如果 bucketType  传递了，当前参数必填
//     * 有效时限 (当前时间开始计算的一个有效时间段, 单位：Unix time second。 eg: 24*60*60 = 1天有效)
//     */
//    private Integer expiresDuration;
}
