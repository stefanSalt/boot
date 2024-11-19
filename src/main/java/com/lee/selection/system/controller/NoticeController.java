package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.entity.Notice;
import com.lee.selection.system.service.NoticeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 公告信息 前端控制器
 *
 * @author baomidou
 * @since 2024-11-14
 */
@Tag(name = "公告信息接口")
@RestController
@RequestMapping("/api/v1/notices")
@RequiredArgsConstructor
public class NoticeController {

        private final NoticeService noticeService;

        @Operation(summary = "公告信息 分页列表")
        @GetMapping("/page")
        public Result listPagedNotices(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Notice queryParams ) {
            IPage<Notice> result = noticeService.listPagedNotices(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "公告信息详情")
        @GetMapping("/{id}")
        public Result getNoticeDetail(
            @Parameter(description = "公告信息ID") @PathVariable Long id
        ) {
            Notice detail = noticeService.getById(id);
            return Result.success(detail);
        }

        @Operation(summary = "新增公告信息")
        @PostMapping
        public Result saveNotice(@RequestBody @Valid Notice formData ) {
            boolean result = noticeService.saveNotice(formData);
            return Result.judge(result);
        }

        @Operation(summary = "公告信息表单数据")
        @GetMapping("/{id}/form")
        public Result<Notice> getNotice(
            @Parameter(description = "公告信息ID") @PathVariable Long id
        ) {
            Notice formData = noticeService.getNoticeData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改公告信息")
        @PutMapping(value = "/{id}")
        public Result updateNotice(@Parameter(description = "公告信息ID") @PathVariable Long id,
        @RequestBody @Validated Notice formData) {
            boolean result = noticeService.updateNotice(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除公告信息")
        @DeleteMapping("/{ids}")
        public Result deleteNotices(
            @Parameter(description = "公告信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = noticeService.deleteNotices(ids);
            return Result.judge(result);
        }
}
