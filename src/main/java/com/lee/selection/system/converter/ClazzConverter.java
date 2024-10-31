package com.lee.selection.system.converter;

import com.lee.selection.system.service.UserService;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lee.selection.system.model.dto.ClazzDTO;
import com.lee.selection.system.model.entity.Clazz;
import com.lee.selection.system.model.vo.ClazzPageVO;
import com.lee.selection.system.model.form.ClazzForm;
import com.lee.selection.system.model.bo.ClazzBO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 班级转换器
 *
 * @author baomidou
 * @since 2024-10-30
 */
@Mapper(componentModel = "spring",uses = {ConverterWorker.class,UserConverterWorker.class})
public interface ClazzConverter{

    @Mappings(
            {
                    @Mapping(target = "parentName", source = "parentId", qualifiedByName = "getMajorName"),
                    @Mapping(target = "teacherName", source = "teacherId", qualifiedByName = "getUserName"),
                    @Mapping(target = "parentId",source = "parentId")
            }
    )
    ClazzPageVO toPageVo(ClazzBO bo);

    Page<ClazzPageVO> toPageVo(Page<ClazzBO> bo);

    ClazzForm toForm(Clazz entity);

    @InheritInverseConfiguration(name = "toForm")
    Clazz toEntity(ClazzForm entity);
}