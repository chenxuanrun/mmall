package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: mmall
 * @description: FileServiceImpl
 * @author: cxr
 * @create: 2019-08-12 17:58
 **/
@Service("IFileService")
@Slf4j
public class FileServiceImpl implements IFileService {
    @Override
    public String upload(MultipartFile file,String path){
       String fileName=file.getOriginalFilename();
       //扩展名
       String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
       String uploadFileName= UUID.randomUUID().toString()+"."+fileExtensionName;
       log.info("开始上传文件,上传文件的原文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);
        File fileDir=new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件已经上传成功了
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上
            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }
}
