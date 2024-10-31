package com.lee.selection.system.converter;

import com.lee.selection.system.model.vo.MajorVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lee.selection.system.model.dto.MajorDTO;
import com.lee.selection.system.model.entity.Major;
import com.lee.selection.system.model.vo.MajorPageVO;
import com.lee.selection.system.model.form.MajorForm;
import com.lee.selection.system.model.bo.MajorBO;

/**
 * 专业信息表转换器
 *
 * @author baomidou
 * @since 2024-10-25
 */
@Mapper(componentModel = "spring")
public interface MajorConverter{

    MajorPageVO toPageVo(MajorBO bo);

    Page<MajorPageVO> toPageVo(Page<MajorBO> bo);

    MajorForm toForm(Major entity);

    @InheritInverseConfiguration(name = "toForm")
    Major toEntity(MajorForm entity);

    MajorVO toVO(MajorBO bo);
}