package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.form.PasswordChangeForm;
import com.lee.selection.system.model.form.ProfileForm;
import com.lee.selection.system.model.form.StudentForm;
import com.lee.selection.system.model.option.StudentOption;
import com.lee.selection.system.model.query.StudentPageQuery;
import com.lee.selection.system.model.vo.ProfileVO;
import com.lee.selection.system.model.vo.StudentPageVO;
import com.lee.selection.system.model.vo.StudentVO;
import com.lee.selection.system.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 学员 前端控制器
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Tag(name = "学员接口")
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

        private final StudentService studentService;

        @Operation(summary = "学员 分页列表")
        @GetMapping("/page")
        public PageResult<StudentPageVO> listPagedStudents(StudentPageQuery queryParams ) {
            IPage<StudentPageVO> result = studentService.listPagedStudents(queryParams);
            return PageResult.success(result);
        }

        @GetMapping("/me")
        public Result<StudentVO> getStudentInfo() {
            StudentVO currentStudentInfo = studentService.getCurrentStudentInfo();
            return Result.success(currentStudentInfo);
        }

        @Operation(summary = "学员列表")
        @GetMapping("/list")
        public Result<List<StudentOption>> listStudents(StudentPageQuery queryParams ) {
            List<StudentOption> result = studentService.listStudents(queryParams);
            return Result.success(result);
        }

    @Operation(summary = "获取个人中心用户信息")
    @GetMapping("/profile")
    public Result<ProfileVO> getUserProfile() {
        ProfileVO userProfile = studentService.getProfile();
        return Result.success(userProfile);
    }

    @Operation(summary = "修改个人中心用户信息")
    @PutMapping("/profile")
    public Result<?> updateUserProfile(@RequestBody ProfileForm formData) {
        boolean result = studentService.updateProfile(formData);
        return Result.judge(result);
    }

    @Operation(summary = "重置用户密码")
    @PutMapping(value = "/{userId}/password/reset")
    public Result<?> resetPassword(
            @Parameter(description = "用户ID") @PathVariable Integer userId,
            @RequestParam String password
    ) {
        boolean result = studentService.resetPassword(userId, password);
        return Result.judge(result);
    }

    @Operation(summary = "修改密码")
    @PutMapping(value = "/password")
    public Result<?> changePassword(
            @RequestBody PasswordChangeForm data
    ) {
        boolean result = studentService.changePassword( data);
        return Result.judge(result);
    }


        @Operation(summary = "新增学员")
        @PostMapping
        public Result saveStudent(@RequestBody @Valid StudentForm formData ) {
            boolean result = studentService.saveStudent(formData);
            return Result.judge(result);
        }

        @Operation(summary = "学员表单数据")
        @GetMapping("/{id}/form")
        public Result<StudentForm> getStudentForm(
            @Parameter(description = "学员ID") @PathVariable Long id
        ) {
            StudentForm formData = studentService.getStudentFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改学员")
        @PutMapping(value = "/{id}")
        public Result updateStudent(@Parameter(description = "学员ID") @PathVariable Long id,
        @RequestBody @Validated StudentForm formData) {
            boolean result = studentService.updateStudent(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除学员")
        @DeleteMapping("/{ids}")
        public Result deleteStudents(
            @Parameter(description = "学员ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = studentService.deleteStudents(ids);
            return Result.judge(result);
        }
}
