package com.lee.reservation.file.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.lee.reservation.file.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 文件服务实现类
 *
 * @Date 2024-06-25 16:09
 * @Author LXA
 */
@Service
public class FileServiceImpl  implements FileService {

    @Value("${server.ip}")
    private String serverIp;

    @Value("${server.port}")
    private String serverPort;


    @Override
    public String upload(String filePath, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);

        // 定义一个文件唯一的标识码
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;

        File uploadFile = new File(filePath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
       Path filePaths = uploadFile.toPath();
        Files.createDirectories(filePaths.getParent());

        String url;
        InputStream inputStream = file.getInputStream();
        file.transferTo(uploadFile);
        // 数据库若不存在重复文件，则不删除刚才上传的文件
        url = "http://" + serverIp + ":"+serverPort+"/" + fileUUID;


        // 存储数据

        return url;
    }

}