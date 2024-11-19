package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.Result;
import com.lee.selection.common.token.service.TokenService;
import com.lee.selection.system.model.vo.ApplyVO;
import com.lee.selection.system.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.entity.Apply;
import com.lee.selection.system.service.ApplyService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 申请 前端控制器
 *
 * @author baomidou
 * @since 2024-11-13
 */
@Tag(name = "申请接口")
@RestController
@RequestMapping("/api/v1/applys")
@RequiredArgsConstructor
public class ApplyController {

        private final ApplyService applyService;
        private final UserService userService;
        private final TokenService tokenService;

        @Operation(summary = "申请 分页列表")
        @GetMapping("/page")
        public Result listPagedApplys(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        ApplyVO queryParams ) {
            IPage<ApplyVO> result = applyService.listPagedApplys(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "检查学生是否已经申请过")
        @GetMapping("/isApplied/{jobId}")
        public Result isApplied(@PathVariable Integer jobId, HttpServletRequest request) {
            String authorization = request.getHeader("Authorization");
            String token = authorization.replace("Bearer ", "");
            String username = tokenService.getUsernameFromToken(token);
            Integer studentId = userService.getUserByUsername(username).getId();
            boolean result = applyService.isApplied(studentId,jobId);
            return Result.judge(result);
        }

        @Operation(summary = "新增申请")
        @PostMapping
        public Result saveApply(@RequestBody @Valid Apply formData ) {
            boolean result = applyService.saveApply(formData);
            return Result.judge(result);
        }

        @Operation(summary = "申请表单数据")
        @GetMapping("/{id}/form")
        public Result<Apply> getApply(
            @Parameter(description = "申请ID") @PathVariable Long id
        ) {
            Apply formData = applyService.getApplyData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改申请")
        @PutMapping(value = "/{id}")
        public Result updateApply(@Parameter(description = "申请ID") @PathVariable Long id,
        @RequestBody @Validated Apply formData) {
            boolean result = applyService.updateApply(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除申请")
        @DeleteMapping("/{ids}")
        public Result deleteApplys(
            @Parameter(description = "申请ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = applyService.deleteApplys(ids);
            return Result.judge(result);
        }
}
