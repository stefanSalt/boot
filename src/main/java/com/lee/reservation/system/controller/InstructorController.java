package com.lee.reservation.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.reservation.common.result.PageResult;
import com.lee.reservation.common.result.Result;
import com.lee.reservation.system.model.form.InstructorForm;
import com.lee.reservation.system.model.form.PasswordChangeForm;
import com.lee.reservation.system.model.form.ProfileForm;
import com.lee.reservation.system.model.option.InstructorOption;
import com.lee.reservation.system.model.query.InstructorPageQuery;
import com.lee.reservation.system.model.vo.InstructorPageVO;
import com.lee.reservation.system.model.vo.InstructorVO;
import com.lee.reservation.system.model.vo.ProfileVO;
import com.lee.reservation.system.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 教练 前端控制器
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Tag(name = "教练接口")
@RestController
@RequestMapping("/api/v1/Instructors")
@RequiredArgsConstructor
public class InstructorController {

        private final InstructorService instructorService;

        @Operation(summary = "教练 分页列表")
        @GetMapping("/page")
        public PageResult<InstructorPageVO> listPagedInstructors(InstructorPageQuery queryParams ) {
            IPage<InstructorPageVO> result = instructorService.listPagedInstructors(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary = "教练列表")
        @GetMapping("/list")
        public Result<List<InstructorOption>> listInstructors(InstructorPageQuery queryParams ) {
            List<InstructorOption> result = instructorService.listInstructors(queryParams);
            return Result.success(result);
        }


        @GetMapping("/me")
        public Result<InstructorVO> getInstructorInfo() {
            InstructorVO currentInstructorInfo = instructorService.getCurrentInstructorInfo();
            return Result.success(currentInstructorInfo);
        }

    @Operation(summary = "获取个人中心用户信息")
    @GetMapping("/profile")
    public Result<ProfileVO> getUserProfile() {
        ProfileVO userProfile = instructorService.getProfile();
        return Result.success(userProfile);
    }

    @Operation(summary = "修改个人中心用户信息")
    @PutMapping("/profile")
    public Result<?> updateUserProfile(@RequestBody ProfileForm formData) {
        boolean result = instructorService.updateProfile(formData);
        return Result.judge(result);
    }

    @Operation(summary = "重置用户密码")
    @PutMapping(value = "/{userId}/password/reset")
    public Result<?> resetPassword(
            @Parameter(description = "用户ID") @PathVariable Integer userId,
            @RequestParam String password
    ) {
        boolean result = instructorService.resetPassword(userId, password);
        return Result.judge(result);
    }

    @Operation(summary = "修改密码")
    @PutMapping(value = "/password")
    public Result<?> changePassword(
            @RequestBody PasswordChangeForm data
    ) {
        boolean result = instructorService.changePassword( data);
        return Result.judge(result);
    }


        @Operation(summary = "新增教练")
        @PostMapping
        public Result saveInstructor(@RequestBody @Valid InstructorForm formData ) {
            boolean result = instructorService.saveInstructor(formData);
            return Result.judge(result);
        }

        @Operation(summary = "教练表单数据")
        @GetMapping("/{id}/form")
        public Result<InstructorForm> getInstructorForm(
            @Parameter(description = "教练ID") @PathVariable Long id
        ) {
            InstructorForm formData = instructorService.getInstructorFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改教练")
        @PutMapping(value = "/{id}")
        public Result updateInstructor(@Parameter(description = "教练ID") @PathVariable Long id,
        @RequestBody @Validated InstructorForm formData) {
            boolean result = instructorService.updateInstructor(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除教练")
        @DeleteMapping("/{ids}")
        public Result deleteInstructors(
            @Parameter(description = "教练ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = instructorService.deleteInstructors(ids);
            return Result.judge(result);
        }
}
