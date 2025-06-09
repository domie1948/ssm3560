package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShiyanxiangmuEntity;
import java.util.Map;

/**
 * 实验项目 服务类
 * @since 2021-04-26
 */
public interface ShiyanxiangmuService extends IService<ShiyanxiangmuEntity> {

     PageUtils queryPage(Map<String, Object> params);

}