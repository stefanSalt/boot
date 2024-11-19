package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.entity.Message;
import com.lee.selection.system.service.MessageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 留言 前端控制器
 *
 * @author baomidou
 * @since 2024-11-14
 */
@Tag(name = "留言接口")
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

        private final MessageService messageService;

        @Operation(summary = "留言 分页列表")
        @GetMapping("/page")
        public Result listPagedMessages(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Message queryParams ) {
            IPage<Message> result = messageService.listPagedMessages(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "留言详情")
        @GetMapping("/{id}")
        public Result getMessageDetail(
            @Parameter(description = "留言ID") @PathVariable Long id
        ) {
            Message result = messageService.getById(id);
            return Result.success(result);
        }

        @Operation(summary = "新增留言")
        @PostMapping
        public Result saveMessage(@RequestBody @Valid Message formData ) {
            boolean result = messageService.saveMessage(formData);
            return Result.judge(result);
        }

        @Operation(summary = "留言表单数据")
        @GetMapping("/{id}/form")
        public Result<Message> getMessage(
            @Parameter(description = "留言ID") @PathVariable Long id
        ) {
            Message formData = messageService.getMessageData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改留言")
        @PutMapping(value = "/{id}")
        public Result updateMessage(@Parameter(description = "留言ID") @PathVariable Long id,
        @RequestBody @Validated Message formData) {
            boolean result = messageService.updateMessage(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除留言")
        @DeleteMapping("/{ids}")
        public Result deleteMessages(
            @Parameter(description = "留言ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = messageService.deleteMessages(ids);
            return Result.judge(result);
        }
}
