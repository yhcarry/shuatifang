package com.jiecheng.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.jiecheng.oss.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FIleController {

    @Resource
    private FileService fileService;

    @NacosValue(value = "${storage.service.type}", autoRefreshed = true)
    private String storageType;

    @RequestMapping("/testGetAllBuckets")
    public List<String> testGetAllBuckets() throws Exception{
        List<String> allBucket = fileService.getAllBucket();
        return allBucket;
    }

    @RequestMapping("/testNacos")
    public String testNacos(){
        return storageType;
    }

    @RequestMapping("/getUrl")
    public String getUrl(String bucket, String objectName){
        return fileService.getUrl(bucket,objectName);
    }

    /**+
     * 上传文件
     * @param bucket
     * @param objectName
     * @return
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile, String bucket, String objectName){
        return fileService.uploadFile(uploadFile, bucket, objectName);
    }
}
