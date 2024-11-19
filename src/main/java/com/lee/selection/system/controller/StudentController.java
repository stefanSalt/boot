package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.entity.Student;
import com.lee.selection.system.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 学生信息 前端控制器
 *
 * @author baomidou
 * @since 2024-11-08
 */
@Tag(name = "学生信息接口")
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

        private final StudentService studentService;

        @Operation(summary = "学生信息 分页列表")
        @GetMapping("/page")
        public Result listPagedStudents(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Student queryParams ) {
            IPage<Student> result = studentService.listPagedStudents(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增学生信息")
        @PostMapping
        public Result saveStudent(@RequestBody @Valid Student formData ) {
            boolean result = studentService.saveStudent(formData);
            return Result.judge(result);
        }

        @Operation(summary = "学生信息表单数据")
        @GetMapping("/{id}/form")
        public Result<Student> getStudent(
            @Parameter(description = "学生信息ID") @PathVariable Long id
        ) {
            Student formData = studentService.getStudentData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改学生信息")
        @PutMapping(value = "/{id}")
        public Result updateStudent(@Parameter(description = "学生信息ID") @PathVariable Long id,
        @RequestBody @Validated Student formData) {
            boolean result = studentService.updateStudent(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除学生信息")
        @DeleteMapping("/{ids}")
        public Result deleteStudents(
            @Parameter(description = "学生信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = studentService.deleteStudents(ids);
            return Result.judge(result);
        }
}
