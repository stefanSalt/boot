package com.lee.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.system.model.entity.Announcement;
import com.lee.system.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 公告表 前端控制器
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Tag(name = "公告表接口")
@RestController
@RequestMapping("/api/v1/notices")
@RequiredArgsConstructor
public class AnnouncementController {

        private final AnnouncementService announcementService;

        @Operation(summary = "公告表 分页列表")
        @GetMapping("/page")
        public Result listPagedAnnouncements(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Announcement queryParams ) {
            IPage<Announcement> result = announcementService.listPagedAnnouncements(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增公告表")
        @PostMapping
        public Result saveAnnouncement(@RequestBody @Valid Announcement formData ) {
            boolean result = announcementService.saveAnnouncement(formData);
            return Result.judge(result);
        }

        @Operation(summary = "公告表表单数据")
        @GetMapping("/{id}/form")
        public Result<Announcement> getAnnouncement(
            @Parameter(description = "公告表ID") @PathVariable Long id
        ) {
            Announcement formData = announcementService.getAnnouncementData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改公告表")
        @PutMapping(value = "/{id}")
        public Result updateAnnouncement(@Parameter(description = "公告表ID") @PathVariable Long id,
        @RequestBody @Validated Announcement formData) {
            boolean result = announcementService.updateAnnouncement(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除公告表")
        @DeleteMapping("/{ids}")
        public Result deleteAnnouncements(
            @Parameter(description = "公告表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = announcementService.deleteAnnouncements(ids);
            return Result.judge(result);
        }
}
