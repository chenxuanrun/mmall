package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: mmall
 * @description: IFileService
 * @author: cxr
 * @create: 2019-08-12 17:57
 **/
public interface IFileService {
    String upload(MultipartFile file, String path);
}
