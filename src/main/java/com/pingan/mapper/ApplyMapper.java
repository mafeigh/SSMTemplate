package com.pingan.mapper;

import com.pingan.model.Apply;

import java.util.List;

public interface ApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Apply record);

    int insertSelective(Apply record);

    List<Apply> selectList();

    Apply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);

    Integer selectItemCount();
}