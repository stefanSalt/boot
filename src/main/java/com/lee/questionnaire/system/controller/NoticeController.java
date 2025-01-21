package com.lee.questionnaire.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.questionnaire.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.questionnaire.system.model.entity.Notice;
import com.lee.questionnaire.system.service.NoticeService;
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
 * @since 2025-01-20
 */
@Tag(name = "公告表接口")
@RestController
@RequestMapping("/api/v1/notices")
@RequiredArgsConstructor
public class NoticeController {

        private final NoticeService noticeService;

        @Operation(summary = "公告表 分页列表")
        @GetMapping("/page")
        public Result listPagedNotices(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Notice queryParams ) {
            IPage<Notice> result = noticeService.listPagedNotices(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增公告表")
        @PostMapping
        public Result saveNotice(@RequestBody @Valid Notice formData ) {
            boolean result = noticeService.saveNotice(formData);
            return Result.judge(result);
        }

        @Operation(summary = "公告表表单数据")
        @GetMapping("/{id}/form")
        public Result<Notice> getNotice(
            @Parameter(description = "公告表ID") @PathVariable Long id
        ) {
            Notice formData = noticeService.getNoticeData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改公告表")
        @PutMapping(value = "/{id}")
        public Result updateNotice(@Parameter(description = "公告表ID") @PathVariable Long id,
        @RequestBody @Validated Notice formData) {
            boolean result = noticeService.updateNotice(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除公告表")
        @DeleteMapping("/{ids}")
        public Result deleteNotices(
            @Parameter(description = "公告表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = noticeService.deleteNotices(ids);
            return Result.judge(result);
        }
}
