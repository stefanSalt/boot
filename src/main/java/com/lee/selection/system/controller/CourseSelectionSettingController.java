package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.converter.CourseSelectionSettingConverter;
import com.lee.selection.system.model.entity.CourseSelectionSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.form.CourseSelectionSettingForm;
import com.lee.selection.system.model.query.CourseSelectionSettingPageQuery;
import com.lee.selection.system.model.vo.CourseSelectionSettingPageVO;
import com.lee.selection.system.service.CourseSelectionSettingService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 选课设置 前端控制器
 *
 * @author baomidou
 * @since 2024-11-04
 */
@Tag(name = "选课设置接口")
@RestController
@RequestMapping("/api/v1/courseSelectionSettings")
@RequiredArgsConstructor
public class CourseSelectionSettingController {

        private final CourseSelectionSettingService courseSelectionSettingService;
        private final CourseSelectionSettingConverter courseSelectionSettingConverter;

        @Operation(summary = "选课设置查询")
        @GetMapping
        public Result listPagedCourseSelectionSettings( ) {
            CourseSelectionSetting byId = courseSelectionSettingService.getById(1L);
            CourseSelectionSettingForm form = courseSelectionSettingConverter.toForm(byId);
            return Result.success(form);
        }





        @Operation(summary = "修改选课设置")
        @PutMapping(value = "/{id}")
        public Result updateCourseSelectionSetting(@Parameter(description = "选课设置ID") @PathVariable Long id,
        @RequestBody @Validated CourseSelectionSettingForm formData) {
            boolean result = courseSelectionSettingService.updateCourseSelectionSetting(id, formData);
            return Result.judge(result);
        }

}
