package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Student;
import com.lee.selection.system.mapper.StudentMapper;
import com.lee.selection.system.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.entity.Student;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 学生信息服务实现类
 *
 * @author baomidou
 * @since 2024-11-08
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {


    /**
    * 获取学生信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Student>} 学生信息分页列表
    */
    @Override
    public IPage<Student> listPagedStudents(Student queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Student> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        //DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<Student> boPage = this.baseMapper.listPagedStudents(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取学生信息表单数据
     *
     * @param id 学生信息ID
     * @return
     */
    @Override
    public Student getStudentData(Long id) {
        Student entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增学生信息
     *
     * @param formData 学生信息表单对象
     * @return
     */
    @Override
    public boolean saveStudent(Student formData) {

        return this.save(formData);
    }
    
    /**
     * 更新学生信息
     *
     * @param id   学生信息ID
     * @param formData 学生信息表单对象
     * @return
     */
    @Override
    public boolean updateStudent(Long id,Student formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除学生信息
     *
     * @param ids 学生信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteStudents(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的学生信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
