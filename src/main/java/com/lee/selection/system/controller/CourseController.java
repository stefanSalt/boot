package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.form.CourseForm;
import com.lee.selection.system.model.option.CourseOption;
import com.lee.selection.system.model.query.CoursePageQuery;
import com.lee.selection.system.model.vo.CoursePageVO;
import com.lee.selection.system.model.vo.CourseVO;
import com.lee.selection.system.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 课程信息 前端控制器
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Tag(name = "课程信息接口")
@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

        private final CourseService courseService;

        @Operation(summary = "课程信息 分页列表")
        @GetMapping("/page")
        public PageResult<CoursePageVO> listPagedCourses(CoursePageQuery queryParams ) {
            IPage<CoursePageVO> result = courseService.listPagedCourses(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary ="上架课程信息分页列表" )
        @GetMapping("/my-page")
        public PageResult<CoursePageVO> listPagedPublishCourses(CoursePageQuery queryParams ) {
            queryParams.setStatus(1);
            IPage<CoursePageVO> result = courseService.listPagedCourses(queryParams);
            return PageResult.success(result);
        }
    @Operation(summary = "教练列表")
    @GetMapping("/list")
    public Result<List<CourseOption>> listInstructors(CoursePageQuery queryParams ) {
        List<CourseOption> result = courseService.listCourses(queryParams);
        return Result.success(result);
    }

        @Operation(summary = "课程信息详情")
        @GetMapping("/{id}/detail")
        public Result<CourseVO> getCourseDetail(
            @Parameter(description = "课程信息ID") @PathVariable  Long id
        ) {
            CourseVO detail = courseService.getCourseDetail(id);
            return Result.success(detail);
        }

        @Operation(summary = "新增课程信息")
        @PostMapping
        public Result saveCourse(@RequestBody @Valid CourseForm formData ) {
            boolean result = courseService.saveCourse(formData);
            return Result.judge(result);
        }

        @Operation(summary = "课程信息表单数据")
        @GetMapping("/{id}/form")
        public Result<CourseForm> getCourseForm(
            @Parameter(description = "课程信息ID") @PathVariable Long id
        ) {
            CourseForm formData = courseService.getCourseFormData(id);
            return Result.success(formData);
        }

        //发布课程信息
    @Operation(summary = "发布课程信息")
    @PutMapping("/{id}/publish")
    public Result publishCourse(@Parameter(description = "课程信息ID") @PathVariable Long id) {
        boolean result = courseService.publishCourse(id);
        return Result.judge(result);
    }
    //下架课程信息
    @Operation(summary = "下架课程信息")
    @PutMapping("/{id}/revoke")
    public Result unPublishCourse(@Parameter(description = "课程信息ID") @PathVariable Long id) {
        boolean result = courseService.revokeCourse(id);
        return Result.judge(result);
    }

        @Operation(summary = "修改课程信息")
        @PutMapping(value = "/{id}")
        public Result updateCourse(@Parameter(description = "课程信息ID") @PathVariable Long id,
        @RequestBody @Validated CourseForm formData) {
            boolean result = courseService.updateCourse(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除课程信息")
        @DeleteMapping("/{ids}")
        public Result deleteCourses(
            @Parameter(description = "课程信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = courseService.deleteCourses(ids);
            return Result.judge(result);
        }
}
