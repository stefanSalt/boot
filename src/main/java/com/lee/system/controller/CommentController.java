package com.lee.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.system.model.entity.Comment;
import com.lee.system.service.CommentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 评论表 前端控制器
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Tag(name = "评论表接口")
@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

        private final CommentService commentService;

        @Operation(summary = "评论表 分页列表")
        @GetMapping("/page")
        public Result listPagedComments(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Comment queryParams ) {
            IPage<Comment> result = commentService.listPagedComments(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增评论表")
        @PostMapping
        public Result saveComment(@RequestBody @Valid Comment formData ) {
            boolean result = commentService.saveComment(formData);
            return Result.judge(result);
        }

        @Operation(summary = "评论表表单数据")
        @GetMapping("/{id}/form")
        public Result<Comment> getComment(
            @Parameter(description = "评论表ID") @PathVariable Long id
        ) {
            Comment formData = commentService.getCommentData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改评论表")
        @PutMapping(value = "/{id}")
        public Result updateComment(@Parameter(description = "评论表ID") @PathVariable Long id,
        @RequestBody @Validated Comment formData) {
            boolean result = commentService.updateComment(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除评论表")
        @DeleteMapping("/{ids}")
        public Result deleteComments(
            @Parameter(description = "评论表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = commentService.deleteComments(ids);
            return Result.judge(result);
        }
}
