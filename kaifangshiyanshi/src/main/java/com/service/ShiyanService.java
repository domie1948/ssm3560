package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShiyanEntity;
import java.util.Map;

/**
 * 学生实验 服务类
 * @since 2021-04-26
 */
public interface ShiyanService extends IService<ShiyanEntity> {

     PageUtils queryPage(Map<String, Object> params);

}