package com.lee.selection.system.converter;

import com.lee.selection.system.model.bo.ReservationBO;
import com.lee.selection.system.model.entity.Reservation;
import com.lee.selection.system.model.form.ReservationForm;
import com.lee.selection.system.model.vo.ReservationPageVO;
import com.lee.selection.system.model.vo.ReservationVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 预约记录转换器
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Mapper(componentModel = "spring",uses = {ConverterWorker.class})
public interface ReservationConverter{

    @Mappings(
            {
//                    @Mapping(target = "studentName",source = "studentId",qualifiedByName = "getStudentName"),
//                    @Mapping(target = "instructorName",source = "instructorId",qualifiedByName = "getInstructorName"),
//                    @Mapping(target = "courseName",source = "courseId",qualifiedByName = "getCourseName")
            }
    )
    ReservationPageVO toPageVo(ReservationBO bo);




    ReservationForm toForm(Reservation entity);

    @InheritInverseConfiguration(name = "toForm")
    Reservation toEntity(ReservationForm entity);


//    @Mappings(
//            {
//                    @Mapping(target = "studentName",source = "studentId",qualifiedByName = "getStudentName"),
//                    @Mapping(target = "instructorName",source = "instructorId",qualifiedByName = "getInstructorName"),
//                    @Mapping(target = "courseName",source = "courseId",qualifiedByName = "getCourseName")
//            }
//    )
    ReservationVO toVo(Reservation entity);
}