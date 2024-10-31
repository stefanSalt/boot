package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.form.CourseForm;
import com.lee.selection.system.model.query.CoursePageQuery;
import com.lee.selection.system.model.vo.CoursePageVO;
import com.lee.selection.system.service.CourseService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 课程信息表 前端控制器
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Tag(name = "课程信息表接口")
@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

        private final CourseService courseService;

        @Operation(summary = "课程信息表 分页列表")
        @GetMapping("/page")
        public PageResult<CoursePageVO> listPagedCourses(CoursePageQuery queryParams ) {
            IPage<CoursePageVO> result = courseService.listPagedCourses(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary = "新增课程信息表")
        @PostMapping
        public Result saveCourse(@RequestBody @Valid CourseForm formData ) {
            boolean result = courseService.saveCourse(formData);
            return Result.judge(result);
        }

        @Operation(summary = "课程信息表表单数据")
        @GetMapping("/{id}/form")
        public Result<CourseForm> getCourseForm(
            @Parameter(description = "课程信息表ID") @PathVariable Long id
        ) {
            CourseForm formData = courseService.getCourseFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改课程信息表")
        @PutMapping(value = "/{id}")
        public Result updateCourse(@Parameter(description = "课程信息表ID") @PathVariable Long id,
        @RequestBody @Validated CourseForm formData) {
            boolean result = courseService.updateCourse(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除课程信息表")
        @DeleteMapping("/{ids}")
        public Result deleteCourses(
            @Parameter(description = "课程信息表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = courseService.deleteCourses(ids);
            return Result.judge(result);
        }
}
