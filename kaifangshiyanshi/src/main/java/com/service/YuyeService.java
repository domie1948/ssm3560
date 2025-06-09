package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YuyeEntity;
import java.util.Map;

/**
 * 实验室预约 服务类
 * @since 2021-04-26
 */
public interface YuyeService extends IService<YuyeEntity> {

     PageUtils queryPage(Map<String, Object> params);

}