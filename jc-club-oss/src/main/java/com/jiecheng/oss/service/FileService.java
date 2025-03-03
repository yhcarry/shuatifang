package com.jiecheng.oss.service;

import com.jiecheng.oss.adapter.StorageAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private final StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter){
        this.storageAdapter = storageAdapter;
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket(){
        return storageAdapter.getAllBucket();
    }

    /**
     * 获取文件路径
     **/
    public String getUrl(String bucket, String objectName) {
        return storageAdapter.getUrl(bucket,objectName);
    }

    /**
     * 上传文件
     **/
    public String uploadFile(MultipartFile uploadFile, String bucket, String objectName){
        storageAdapter.uploadFile(uploadFile,bucket,objectName);
        objectName = objectName + "/" + uploadFile.getOriginalFilename();
        return storageAdapter.getUrl(bucket,objectName);
    }

}
