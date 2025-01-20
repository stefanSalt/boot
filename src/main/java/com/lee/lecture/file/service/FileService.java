package com.lee.lecture.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件服务
 *
 * @Date 2024-06-25 16:08
 * @Author LXA
 */
public interface FileService {

    String upload(String filePath, MultipartFile file) throws IOException;

}
