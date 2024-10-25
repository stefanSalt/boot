package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.form.NoticeForm;
import com.lee.selection.system.model.query.NoticePageQuery;
import com.lee.selection.system.model.vo.NoticePageVO;
import com.lee.selection.system.model.vo.NoticeVO;
import com.lee.selection.system.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 公告信息 前端控制器
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Tag(name = "公告信息接口")
@RestController
@RequestMapping("/api/v1/notices")
@RequiredArgsConstructor
public class NoticeController {

        private final NoticeService noticeService;

        @Operation(summary = "公告信息 分页列表")
        @GetMapping("/page")
        public PageResult<NoticePageVO> listPagedNotices(NoticePageQuery queryParams ) {
            IPage<NoticePageVO> result = noticeService.listPagedNotices(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary = "公告信息列表")
        @GetMapping("/my-page")
        public PageResult<NoticePageVO> listMyPagedNotices(NoticePageQuery queryParams ) {
            queryParams.setStatus(1);
            IPage<NoticePageVO> result = noticeService.listPagedNotices(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary = "新增公告信息")
        @PostMapping
        public Result saveNotice(@RequestBody @Valid NoticeForm formData ) {
            boolean result = noticeService.saveNotice(formData);
            return Result.judge(result);
        }

        @Operation(summary = "公告信息详情")
        @GetMapping("/{id}/detail")
        public Result<NoticeVO> getNoticeDetail(
            @Parameter(description = "公告信息ID") @PathVariable  Long id
        ) {
            NoticeVO detail = noticeService.getNoticeDetail(id);
            return Result.success(detail);
        }

        @Operation(summary = "公告信息表单数据")
        @GetMapping("/{id}/form")
        public Result<NoticeForm> getNoticeForm(
            @Parameter(description = "公告信息ID") @PathVariable Long id
        ) {
            NoticeForm formData = noticeService.getNoticeFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "发布公告信息")
        @PutMapping(value = "/{id}/publish")
        public Result updateNotice(@Parameter(description = "公告信息ID") @PathVariable Long id) {
            boolean result = noticeService.publishNotice(id);
            return Result.judge(result);
        }

        //撤回
        @PutMapping(value = "/{id}/unPublish")
        public Result revokeNotice(@Parameter(description = "公告信息ID") @PathVariable Long id) {
            boolean result = noticeService.unPublishNotice(id);
            return Result.judge(result);
        }

        @Operation(summary = "修改公告信息")
        @PutMapping("/{id}")
        public Result updateNotice(@RequestBody @Valid NoticeForm formData,@PathVariable Long id) {
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
