package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.ShiyanDao;
import com.entity.ShiyanEntity;
import com.service.ShiyanService;
import com.entity.view.ShiyanView;

/**
 * 学生实验 服务实现类
 * @since 2021-04-26
 */
@Service("shiyanService")
@Transactional
public class ShiyanServiceImpl extends ServiceImpl<ShiyanDao, ShiyanEntity> implements ShiyanService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<ShiyanView> page =new Query<ShiyanView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
