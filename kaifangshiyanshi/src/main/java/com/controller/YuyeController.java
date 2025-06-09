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

import com.entity.YuyeEntity;

import com.service.YuyeService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 实验室预约
 * 后端接口
 * @author
 * @email
 * @date 2021-04-26
*/
@RestController
@Controller
@RequestMapping("/yuye")
public class YuyeController {
    private static final Logger logger = LoggerFactory.getLogger(YuyeController.class);

    @Autowired
    private YuyeService yuyeService;

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
        PageUtils page = yuyeService.queryPage(params);
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        YuyeEntity yuye = yuyeService.selectById(id);
        if(yuye!=null){
            return R.ok().put("data", yuye);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YuyeEntity yuye, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<YuyeEntity> queryWrapper = new EntityWrapper<YuyeEntity>()
            .eq("shiyanshi_types", yuye.getShiyanshiTypes())
            .eq("jiaoshi_types", yuye.getJiaoshiTypes())
            .eq("yuye_types", yuye.getYuyeTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuyeEntity yuyeEntity = yuyeService.selectOne(queryWrapper);
            yuye.setInsertTime(new Date());
        if(yuyeEntity==null){
            yuyeService.insert(yuye);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YuyeEntity yuye, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<YuyeEntity> queryWrapper = new EntityWrapper<YuyeEntity>()
            .notIn("id",yuye.getId())
            .eq("shiyanshi_types", yuye.getShiyanshiTypes())
            .eq("jiaoshi_types", yuye.getJiaoshiTypes())
            .eq("yuye_types", yuye.getYuyeTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuyeEntity yuyeEntity = yuyeService.selectOne(queryWrapper);
                yuye.setInsertTime(new Date());
        if(yuyeEntity==null){
            yuyeService.updateById(yuye);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }
    /**
     * 同意
     */
    @RequestMapping("/tongyi")
    public R tongyi(Integer ids) {
        YuyeEntity yuye = yuyeService.selectById(ids);
        yuye.setYuyeTypes(2);
        boolean b = yuyeService.updateById(yuye);
        if(b){
            ShiyanxiangmuEntity shiyanxiangmu = new ShiyanxiangmuEntity();
            shiyanxiangmu.setCreateTime(new Date());
            shiyanxiangmu.setJiaoshiTypes(yuye.getJiaoshiTypes());
            shiyanxiangmu.setYuye(yuye.getId());
            shiyanxiangmuService.insert(shiyanxiangmu);
            return R.ok();
        }
        return R.error();
    }

    /**
     * 拒绝
     */
    @RequestMapping("/jujue")
    public R jujue(Integer ids, HttpServletRequest request) {
        YuyeEntity yuye = yuyeService.selectById(ids);
        yuye.setYuyeTypes(1);
        boolean b = yuyeService.updateById(yuye);
        if(b){
            return R.ok();
        }
        return R.error();
    }

    @RequestMapping("/yuyue")
    public R yuyue(Integer ids, HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(!role.equals("教师")){
            return R.error("只有教师可以预约");
        }
        YuyeEntity yuye = new YuyeEntity();
        yuye.setCreateTime(new Date());
        yuye.setInsertTime(new Date());
        yuye.setShiyanshiTypes(ids);
        yuye.setJiaoshiTypes(userId);
        yuye.setYuyeTypes(0);
        Wrapper<YuyeEntity> queryWrapper = new EntityWrapper<YuyeEntity>()
                .eq("shiyanshi_types", yuye.getShiyanshiTypes())
                .eq("jiaoshi_types", yuye.getJiaoshiTypes())
                .eq("yuye_types", yuye.getYuyeTypes())
                ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuyeEntity yuyeEntity = yuyeService.selectOne(queryWrapper);
        if(yuyeEntity!=null) {
            return R.error("你已经预约过了");
        }
        yuyeService.insert(yuye);
        return R.ok();
    }
    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        logger.debug("Controller:"+this.getClass().getName()+",delete");
        yuyeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

