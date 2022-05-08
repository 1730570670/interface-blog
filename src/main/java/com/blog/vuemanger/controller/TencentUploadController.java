package com.blog.vuemanger.controller;

import com.blog.vuemanger.common.UploadMsg;
import com.blog.vuemanger.tencentupload.TencentConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.File;
import java.util.Calendar;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(value = "/upload")
public class TencentUploadController {
    @Autowired
    private TencentConfig tencentConfig;

    /**
     * 上传文件接口
     * @param file
     * @return
     */
    @PostMapping
    public Object Upload(MultipartFile file) {
        System.out.println(file);
        if (file == null) {
            return new UploadMsg(0, "文件为空", null);
        }
        String oldFileName = file.getOriginalFilename();
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + eName;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tencentConfig.getAccessKey(), tencentConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(tencentConfig.getBucket()));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = tencentConfig.getBucketName();

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            // 指定要上传到 COS 上的路径
            String key = "/" + tencentConfig.getPrefix() + newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            log.info(putObjectResult.getContentMd5());
            log.info(putObjectResult.getDateStr());
            log.info(putObjectResult.getETag());
            log.info(putObjectResult.getRequestId());
            //{"status":1,"msg":"上传成功","path":"图片地址"}
            return new UploadMsg(1, "上传成功", tencentConfig.getPath() + putObjectRequest.getKey());
        } catch (Exception e) {
            return new UploadMsg(-1, e.getMessage(), null);
        } finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
    }

    /**
     * 上传成功接口
     * @return
     */
    @PostMapping("/success")
    public String returnUpload(){
        return "success";
    }
}
