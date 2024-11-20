package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.vo.JobInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.entity.JobInfo;
import com.lee.selection.system.service.JobInfoService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 *  前端控制器
 *
 * @author baomidou
 * @since 2024-11-13
 */
@Tag(name = "接口")
@RestController
@RequestMapping("/api/v1/jobinfos")
@RequiredArgsConstructor
public class JobInfoController {

        private final JobInfoService jobInfoService;

        @Operation(summary = " 分页列表")
        @GetMapping("/page")
        public Result listPagedJobInfos(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        JobInfo queryParams ) {
            IPage<JobInfoVO> result = jobInfoService.listPagedJobInfos(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "分页")
        @GetMapping("/page/apply")
        public Result listPagedJobInfosForApply(@RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "10")Integer pageSize,
                                                JobInfo queryParams ,
                                                Integer userId) {
            IPage<JobInfo> result = jobInfoService.listPagedJobInfosForApply(queryParams,userId,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增")
        @PostMapping
        public Result saveJobInfo(@RequestBody @Valid JobInfo formData ) {
            boolean result = jobInfoService.saveJobInfo(formData);
            return Result.judge(result);
        }

        @Operation(summary = "表单数据")
        @GetMapping("/{id}/form")
        public Result<JobInfo> getJobInfo(
            @Parameter(description = "ID") @PathVariable Long id
        ) {
            JobInfo formData = jobInfoService.getJobInfoData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改")
        @PutMapping(value = "/{id}")
        public Result updateJobInfo(@Parameter(description = "ID") @PathVariable Long id,
        @RequestBody @Validated JobInfo formData) {
            boolean result = jobInfoService.updateJobInfo(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除")
        @DeleteMapping("/{ids}")
        public Result deleteJobInfos(
            @Parameter(description = "ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = jobInfoService.deleteJobInfos(ids);
            return Result.judge(result);
        }
}
