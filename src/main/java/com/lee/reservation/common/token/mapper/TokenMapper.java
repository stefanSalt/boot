package com.lee.reservation.common.token.mapper;

import com.lee.reservation.common.token.model.entity.Token;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * token Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-15
 */

@Mapper
public interface TokenMapper extends BaseMapper<Token> {

}
