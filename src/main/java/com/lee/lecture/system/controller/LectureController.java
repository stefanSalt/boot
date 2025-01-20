package com.lee.lecture.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.lecture.common.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.lecture.system.model.entity.Lecture;
import com.lee.lecture.system.service.LectureService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


/**
 * 讲座信息表 前端控制器
 *
 * @author baomidou
 * @since 2025-01-07
 */
@Tag(name = "讲座信息表接口")
@RestController
@RequestMapping("/api/v1/lectures")
@RequiredArgsConstructor
public class LectureController {

        private final LectureService lectureService;

        @Operation(summary = "讲座信息表 分页列表")
        @GetMapping("/page")
        public Result listPagedLectures(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Lecture queryParams ) {
            IPage<Lecture> result = lectureService.listPagedLectures(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "导出预约人员名单xslx")
        @GetMapping("/export")
        public void exportReservations(
            @Parameter(description = "讲座信息表ID") @RequestParam Long lectureId,
            HttpServletResponse response
        ) {
            lectureService.exportReservations(lectureId, response);
        }

        //获取下拉框数据
        @Operation(summary = "获取下拉框数据")
        @GetMapping("/options")
        public Result listLectureOptions() {
            List<Lecture> result = lectureService.listLectureOptions();
            return Result.success(result);
        }


        @Operation(summary = "新增讲座信息表")
        @PostMapping
        public Result saveLecture(@RequestBody @Valid Lecture formData ) {
            boolean result = lectureService.saveLecture(formData);
            return Result.judge(result);
        }

        @Operation(summary = "讲座信息表表单数据")
        @GetMapping("/{id}/form")
        public Result<Lecture> getLecture(
            @Parameter(description = "讲座信息表ID") @PathVariable Long id
        ) {
            Lecture formData = lectureService.getLectureData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改讲座信息表")
        @PutMapping(value = "/{id}")
        public Result updateLecture(@Parameter(description = "讲座信息表ID") @PathVariable Long id,
        @RequestBody @Validated Lecture formData) {
            boolean result = lectureService.updateLecture(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除讲座信息表")
        @DeleteMapping("/{ids}")
        public Result deleteLectures(
            @Parameter(description = "讲座信息表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = lectureService.deleteLectures(ids);
            return Result.judge(result);
        }
}
