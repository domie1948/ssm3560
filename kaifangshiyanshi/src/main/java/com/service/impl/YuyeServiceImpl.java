package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.YuyeDao;
import com.entity.YuyeEntity;
import com.service.YuyeService;
import com.entity.view.YuyeView;

/**
 * 实验室预约 服务实现类
 * @since 2021-04-26
 */
@Service("yuyeService")
@Transactional
public class YuyeServiceImpl extends ServiceImpl<YuyeDao, YuyeEntity> implements YuyeService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<YuyeView> page =new Query<YuyeView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
