package com.lee.online_store.common.token.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.online_store.common.token.model.entity.Token;
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
