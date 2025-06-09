package com.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.entity.ShiyanxiangmuEntity;
import com.service.ShiyanxiangmuService;
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

import com.entity.ShiyanEntity;

import com.service.ShiyanService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 学生实验
 * 后端接口
 * @author
 * @email
 * @date 2021-04-26
*/
@RestController
@Controller
@RequestMapping("/shiyan")
public class ShiyanController {
    private static final Logger logger = LoggerFactory.getLogger(ShiyanController.class);

    @Autowired
    private ShiyanService shiyanService;

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
        PageUtils page = shiyanService.queryPage(params);
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        ShiyanEntity shiyan = shiyanService.selectById(id);
        if(shiyan!=null){
            return R.ok().put("data", shiyan);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShiyanEntity shiyan, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<ShiyanEntity> queryWrapper = new EntityWrapper<ShiyanEntity>()
            .eq("yonghu_types", shiyan.getYonghuTypes())
            .eq("shiyanxiangmu_types", shiyan.getShiyanxiangmuTypes())
            .eq("shiyan_types", shiyan.getShiyanTypes())
            .eq("shiyan_content", shiyan.getShiyanContent())
            .eq("shiyanxiangmu_number", shiyan.getShiyanxiangmuNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShiyanEntity shiyanEntity = shiyanService.selectOne(queryWrapper);
            shiyan.setInsertTime(new Date());
        if(shiyanEntity==null){
            shiyanService.insert(shiyan);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShiyanEntity shiyan, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<ShiyanEntity> queryWrapper = new EntityWrapper<ShiyanEntity>()
            .notIn("id",shiyan.getId())
            .eq("yonghu_types", shiyan.getYonghuTypes())
            .eq("shiyanxiangmu_types", shiyan.getShiyanxiangmuTypes())
            .eq("shiyan_types", shiyan.getShiyanTypes())
            .eq("shiyan_content", shiyan.getShiyanContent())
            .eq("shiyanxiangmu_number", shiyan.getShiyanxiangmuNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShiyanEntity shiyanEntity = shiyanService.selectOne(queryWrapper);
                shiyan.setInsertTime(new Date());
        if(shiyanEntity==null){
            shiyanService.updateById(shiyan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }
    /**
     * 开始实验
     */
    @RequestMapping("/kaishi")
    public R kaishi(Integer ids, HttpServletRequest request){
        ShiyanEntity shiyan = shiyanService.selectById(ids);
        shiyan.setCreateTime(new Date());
        shiyan.setInsertTime(new Date());
        shiyan.setShiyanTypes(2);
        boolean b = shiyanService.updateById(shiyan);
        if(b){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 结束实验
     */
    @RequestMapping("/jieshu")
    public R jieshu(Integer ids, HttpServletRequest request){
        ShiyanEntity shiyan = shiyanService.selectById(ids);
        shiyan.setShiyanTypes(3);
        boolean b = shiyanService.updateById(shiyan);
        if(b){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 进行实验
     */
    @RequestMapping("/kaishishiyan")
    public R kaishishiyan(Integer ids, HttpServletRequest request){
        ShiyanxiangmuEntity shiyanxiangmuEntity = shiyanxiangmuService.selectById(ids);
        if(shiyanxiangmuEntity == null){
            return R.error();
        }
        ShiyanEntity shiyan = new ShiyanEntity();
        shiyan.setShiyanTypes(1);
        shiyan.setCreateTime(new Date());
        shiyan.setYonghuTypes((Integer) request.getSession().getAttribute("userId"));
        shiyan.setShiyanxiangmuTypes(ids);
        Wrapper<ShiyanEntity> queryWrapper = new EntityWrapper<ShiyanEntity>()
                .eq("yonghu_types", shiyan.getYonghuTypes())
                .eq("shiyanxiangmu_types", shiyan.getShiyanxiangmuTypes())
                ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShiyanEntity shiyanEnti = shiyanService.selectOne(queryWrapper);
        if(shiyanEnti!=null){
            return R.error("已经实验过了哦");
        }

        shiyanService.insert(shiyan);
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        logger.debug("Controller:"+this.getClass().getName()+",delete");
        shiyanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

