package com.lee.questionnaire.file.controller;

import cn.hutool.core.io.FileUtil;
import com.lee.questionnaire.common.result.Result;
import com.lee.questionnaire.file.service.FileService;
import com.lee.questionnaire.system.model.dto.FileInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件controller
 *
 * @Date 2024-06-25 13:05
 * @Author LXA
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/files")
public class FileController {


    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FileService fileService;


    @PostMapping
    public Result<FileInfo> upload(@RequestParam MultipartFile file) throws IOException {
        String url=fileService.upload(fileUploadPath, file);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setUrl(url);
        return Result.success(fileInfo);
    }




    /**
     * 文件下载接口
     * @param fileUUID 文件唯一标识码
     * @param response 响应
     * @throws IOException IO异常
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, StandardCharsets.UTF_8));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }



}