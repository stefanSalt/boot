package com.lee.selection.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.InstructorBO;
import com.lee.selection.system.model.entity.Instructor;
import com.lee.selection.system.model.form.InstructorForm;
import com.lee.selection.system.model.form.ProfileForm;
import com.lee.selection.system.model.option.InstructorOption;
import com.lee.selection.system.model.vo.InstructorPageVO;
import com.lee.selection.system.model.vo.InstructorVO;
import com.lee.selection.system.model.vo.ProfileVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 教练转换器
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Mapper(componentModel = "spring")
public interface InstructorConverter {

    InstructorPageVO toPageVo(InstructorBO bo);

    Page<InstructorPageVO> toPageVo(Page<InstructorBO> bo);

    InstructorForm toForm(Instructor entity);

    @InheritInverseConfiguration(name = "toForm")
    Instructor toEntity(InstructorForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    InstructorVO toVo(Instructor entity);

    ProfileVO toProfileVo(Instructor entity);

    Instructor toEntity(ProfileForm entity);

    InstructorOption toOption(Instructor entity);
}