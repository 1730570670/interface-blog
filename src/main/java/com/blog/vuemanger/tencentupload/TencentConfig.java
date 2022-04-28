package com.blog.vuemanger.tencentupload;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TencentConfig {
    @Value("${tencent.cloud.accessKey}")
    private String accessKey;
    @Value("${tencent.cloud.secretKey}")
    private String secretKey;
    @Value("${tencent.cloud.bucket}")
    private String bucket;
    @Value("${tencent.cloud.bucketName}")
    private String bucketName;
    @Value("${tencent.cloud.path}")
    private String path;
    @Value("${tencent.cloud.prefix}")
    private String prefix;
}