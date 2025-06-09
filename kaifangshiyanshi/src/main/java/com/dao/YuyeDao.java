package com.dao;

import com.entity.YuyeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YuyeView;

/**
 * 实验室预约 Dao 接口
 *
 * @since 2021-04-26
 */
public interface YuyeDao extends BaseMapper<YuyeEntity> {

   List<YuyeView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}
