package com.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.ShiyanxiangmuEntity;

import com.service.ShiyanxiangmuService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 实验项目
 * 后端接口
 * @author
 * @email
 * @date 2021-04-26
*/
@RestController
@Controller
@RequestMapping("/shiyanxiangmu")
public class ShiyanxiangmuController {
    private static final Logger logger = LoggerFactory.getLogger(ShiyanxiangmuController.class);

    @Autowired
    private ShiyanxiangmuService shiyanxiangmuService;

    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",page方法");
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtils.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuTypes",request.getSession().getAttribute("userId"));
        }else if(StringUtils.isNotEmpty(role) && "教师".equals(role)){
            params.put("jiaoshiTypes",request.getSession().getAttribute("userId"));
        }
        PageUtils page = shiyanxiangmuService.queryPage(params);
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        ShiyanxiangmuEntity shiyanxiangmu = shiyanxiangmuService.selectById(id);
        if(shiyanxiangmu!=null){
            return R.ok().put("data", shiyanxiangmu);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShiyanxiangmuEntity shiyanxiangmu, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<ShiyanxiangmuEntity> queryWrapper = new EntityWrapper<ShiyanxiangmuEntity>()
            .eq("jiaoshi_types", shiyanxiangmu.getJiaoshiTypes())
            .eq("yuye", shiyanxiangmu.getYuye())
            .eq("shiyanxiangmu_name", shiyanxiangmu.getShiyanxiangmuName())
            .eq("shiyanxiangmu_content", shiyanxiangmu.getShiyanxiangmuContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShiyanxiangmuEntity shiyanxiangmuEntity = shiyanxiangmuService.selectOne(queryWrapper);
        if(shiyanxiangmuEntity==null){
            shiyanxiangmuService.insert(shiyanxiangmu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShiyanxiangmuEntity shiyanxiangmu, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<ShiyanxiangmuEntity> queryWrapper = new EntityWrapper<ShiyanxiangmuEntity>()
            .notIn("id",shiyanxiangmu.getId())
            .eq("jiaoshi_types", shiyanxiangmu.getJiaoshiTypes())
            .eq("yuye", shiyanxiangmu.getYuye())
            .eq("shiyanxiangmu_name", shiyanxiangmu.getShiyanxiangmuName())
            .eq("shiyanxiangmu_content", shiyanxiangmu.getShiyanxiangmuContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShiyanxiangmuEntity shiyanxiangmuEntity = shiyanxiangmuService.selectOne(queryWrapper);
        if(shiyanxiangmuEntity==null){
            shiyanxiangmuService.updateById(shiyanxiangmu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        logger.debug("Controller:"+this.getClass().getName()+",delete");
        shiyanxiangmuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

