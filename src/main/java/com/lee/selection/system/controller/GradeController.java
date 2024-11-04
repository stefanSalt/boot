package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.entity.User;
import com.lee.selection.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.form.GradeForm;
import com.lee.selection.system.model.query.GradePageQuery;
import com.lee.selection.system.model.vo.GradePageVO;
import com.lee.selection.system.service.GradeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 成绩前端控制器
 *
 * @author baomidou
 * @since 2024-11-01
 */
@Tag(name = "成绩接口")
@RestController
@RequestMapping("/api/v1/grades")
@RequiredArgsConstructor
public class GradeController {

        private final GradeService gradeService;
        private final UserService userService;

        @Operation(summary = "成绩分页列表")
        @GetMapping("/page")
        public PageResult<GradePageVO> listPagedGrades(GradePageQuery queryParams ) {
            IPage<GradePageVO> result = gradeService.listPagedGrades(queryParams);
            return PageResult.success(result);
        }

    @Operation(summary = "学生端成绩分页列表")
    @GetMapping("/student/page")
    public PageResult<GradePageVO> listPagedGradesByStudent(GradePageQuery queryParams ) {
        User user = userService.getCurrentUser();
        queryParams.setStudentId(user.getId());
        queryParams.setStatus(1);
        IPage<GradePageVO> result = gradeService.listPagedGrades(queryParams);
        return PageResult.success(result);
    }

    //教师端
    @Operation(summary = "教师端成绩分页列表")
    @GetMapping("/teacher/page")
    public PageResult<GradePageVO> listPagedGradesByTeacher(GradePageQuery queryParams ) {
        User user = userService.getCurrentUser();
        queryParams.setTeacherId(user.getId());
        IPage<GradePageVO> result = gradeService.listPagedGrades(queryParams);
        return PageResult.success(result);
    }



        @Operation(summary = "新增成绩")
        @PostMapping
        public Result saveGrade(@RequestBody @Valid GradeForm formData ) {
            boolean result = gradeService.saveGrade(formData);
            return Result.judge(result);
        }

        @Operation(summary = "成绩表单数据")
        @GetMapping("/{id}/form")
        public Result<GradeForm> getGradeForm(
            @Parameter(description = "成绩ID") @PathVariable Long id
        ) {
            GradeForm formData = gradeService.getGradeFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改成绩")
        @PutMapping(value = "/{id}")
        public Result updateGrade(@Parameter(description = "成绩ID") @PathVariable Long id,
        @RequestBody @Validated GradeForm formData) {
            boolean result = gradeService.updateGrade(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除成绩")
        @DeleteMapping("/{ids}")
        public Result deleteGrades(
            @Parameter(description = "成绩ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = gradeService.deleteGrades(ids);
            return Result.judge(result);
        }
}
