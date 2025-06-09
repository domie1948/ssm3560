package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JiaoshiEntity;
import java.util.Map;

/**
 * 教师 服务类
 * @since 2021-04-26
 */
public interface JiaoshiService extends IService<JiaoshiEntity> {

     PageUtils queryPage(Map<String, Object> params);

}