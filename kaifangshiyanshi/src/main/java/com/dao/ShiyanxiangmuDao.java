package com.dao;

import com.entity.ShiyanxiangmuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShiyanxiangmuView;

/**
 * 实验项目 Dao 接口
 *
 * @since 2021-04-26
 */
public interface ShiyanxiangmuDao extends BaseMapper<ShiyanxiangmuEntity> {

   List<ShiyanxiangmuView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}
