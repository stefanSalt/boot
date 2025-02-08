package com.lee.questionnaire.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.questionnaire.common.result.Result;
import com.lee.questionnaire.common.token.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.questionnaire.system.model.entity.Response;
import com.lee.questionnaire.system.service.ResponseService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 答卷表 前端控制器
 *
 * @author baomidou
 * @since 2025-02-07
 */
@Tag(name = "答卷表接口")
@RestController
@RequestMapping("/api/v1/responses")
@RequiredArgsConstructor
public class ResponseController {

        private final ResponseService responseService;

        private final TokenService tokenService;

        @Operation(summary = "答卷表 分页列表")
        @GetMapping("/page")
        public Result listPagedResponses(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Response queryParams ) {
            IPage<Response> result = responseService.listPagedResponses(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "答卷表 用户分页列表")
        @GetMapping("/my/page")
        public Result listPagedResponsesByUser(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Response queryParams ) {
            //获取request
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization" );
            token = token.replace("Bearer ", "");
            Integer userId = tokenService.getUserIdFromToken(token);
            queryParams.setUserId(userId);

            IPage<Response> result = responseService.listPagedResponses(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增答卷表")
        @PostMapping
        public Result saveResponse(@RequestBody @Valid Response formData ) {
            boolean result = responseService.saveResponse(formData);
            return Result.judge(result);
        }

        @Operation(summary = "答卷表表单数据")
        @GetMapping("/{id}/form")
        public Result<Response> getResponse(
            @Parameter(description = "答卷表ID") @PathVariable Long id
        ) {
            Response formData = responseService.getResponseData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改答卷表")
        @PutMapping(value = "/{id}")
        public Result updateResponse(@Parameter(description = "答卷表ID") @PathVariable Long id,
        @RequestBody @Validated Response formData) {
            boolean result = responseService.updateResponse(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除答卷表")
        @DeleteMapping("/{ids}")
        public Result deleteResponses(
            @Parameter(description = "答卷表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = responseService.deleteResponses(ids);
            return Result.judge(result);
        }
}
