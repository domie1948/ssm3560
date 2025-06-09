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

import com.entity.ShiyanshiEntity;

import com.service.ShiyanshiService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 实验室
 * 后端接口
 * @author
 * @email
 * @date 2021-04-26
*/
@RestController
@Controller
@RequestMapping("/shiyanshi")
public class ShiyanshiController {
    private static final Logger logger = LoggerFactory.getLogger(ShiyanshiController.class);

    @Autowired
    private ShiyanshiService shiyanshiService;

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
        PageUtils page = shiyanshiService.queryPage(params);
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        ShiyanshiEntity shiyanshi = shiyanshiService.selectById(id);
        if(shiyanshi!=null){
            return R.ok().put("data", shiyanshi);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShiyanshiEntity shiyanshi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<ShiyanshiEntity> queryWrapper = new EntityWrapper<ShiyanshiEntity>()
            .eq("shiyanshi_name", shiyanshi.getShiyanshiName())
            .eq("shiyanshi_dizhi", shiyanshi.getShiyanshiDizhi())
            .eq("shiyanshi_content", shiyanshi.getShiyanshiContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShiyanshiEntity shiyanshiEntity = shiyanshiService.selectOne(queryWrapper);
        if("".equals(shiyanshi.getShiyanshiPhoto()) || "null".equals(shiyanshi.getShiyanshiPhoto())){
            shiyanshi.setShiyanshiPhoto(null);
        }
        if(shiyanshiEntity==null){
            shiyanshiService.insert(shiyanshi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShiyanshiEntity shiyanshi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<ShiyanshiEntity> queryWrapper = new EntityWrapper<ShiyanshiEntity>()
            .notIn("id",shiyanshi.getId())
            .eq("shiyanshi_name", shiyanshi.getShiyanshiName())
            .eq("shiyanshi_dizhi", shiyanshi.getShiyanshiDizhi())
            .eq("shiyanshi_content", shiyanshi.getShiyanshiContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShiyanshiEntity shiyanshiEntity = shiyanshiService.selectOne(queryWrapper);
        if("".equals(shiyanshi.getShiyanshiPhoto()) || "null".equals(shiyanshi.getShiyanshiPhoto())){
                shiyanshi.setShiyanshiPhoto(null);
        }
        if(shiyanshiEntity==null){
            shiyanshiService.updateById(shiyanshi);//根据id更新
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
        shiyanshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

