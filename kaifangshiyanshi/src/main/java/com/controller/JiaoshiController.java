package com.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.annotation.IgnoreAuth;
import com.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.JiaoshiEntity;

import com.service.JiaoshiService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 教师
 * 后端接口
 * @author
 * @email
 * @date 2021-04-26
*/
@RestController
@Controller
@RequestMapping("/jiaoshi")
public class JiaoshiController {
    private static final Logger logger = LoggerFactory.getLogger(JiaoshiController.class);

    @Autowired
    private JiaoshiService jiaoshiService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        JiaoshiEntity jiaoshi = jiaoshiService.selectOne(new EntityWrapper<JiaoshiEntity>().eq("username", username));
        if(jiaoshi==null || !jiaoshi.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
        //  // 获取监听器中的字典表
        String token = tokenService.generateToken(jiaoshi.getId(),jiaoshi.getJiaoshiName(), "jiaoshi", "教师");
        R r = R.ok();
        r.put("token", token);
        r.put("role","教师");
        r.put("username",jiaoshi.getJiaoshiName());
        r.put("tableName","jiaoshi");
        r.put("userId",jiaoshi.getId());
        return r;
    }

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody JiaoshiEntity jiaoshi){
        //    	ValidatorUtils.validateEntity(user);
        if(jiaoshiService.selectOne(new EntityWrapper<JiaoshiEntity>().eq("username", jiaoshi.getUsername()).orNew().eq("jiaoshi_phone",jiaoshi.getJiaoshiPhone()).orNew().eq("jiaoshi_number",jiaoshi.getJiaoshiNumber())) !=null) {
            return R.error("账户已存在或手机号或身份证号已经被使用");
        }
        jiaoshiService.insert(jiaoshi);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        JiaoshiEntity jiaoshi = new JiaoshiEntity();
        jiaoshi.setPassword("123456");
        jiaoshi.setId(id);
        jiaoshiService.updateById(jiaoshi);
        return R.ok();
    }

    /**
     * 获取教师的session教师信息
     */
    @RequestMapping("/session")
    public R getCurrJiaoshi(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        JiaoshiEntity jiaoshi = jiaoshiService.selectById(id);
        return R.ok().put("data", jiaoshi);
    }


    /**
     * 退出
     */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }

    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        logger.debug("Controller:"+this.getClass().getName()+",page方法");
        PageUtils page = jiaoshiService.queryPage(params);
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        JiaoshiEntity jiaoshi = jiaoshiService.selectById(id);
        if(jiaoshi!=null){
            return R.ok().put("data", jiaoshi);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<JiaoshiEntity> queryWrapper = new EntityWrapper<JiaoshiEntity>()
            .eq("jiaoshi_bianhao", jiaoshi.getJiaoshiBianhao())
            .eq("username", jiaoshi.getUsername())
            .eq("password", jiaoshi.getPassword())
            .eq("jiaoshi_name", jiaoshi.getJiaoshiName())
            .eq("sex_types", jiaoshi.getSexTypes())
            .eq("jiaoshi_number", jiaoshi.getJiaoshiNumber())
            .eq("jiaoshi_phone", jiaoshi.getJiaoshiPhone())
            .eq("jiaoshi_bumen", jiaoshi.getJiaoshiBumen())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaoshiEntity jiaoshiEntity = jiaoshiService.selectOne(queryWrapper);
        if("".equals(jiaoshi.getJiaoshiPhoto()) || "null".equals(jiaoshi.getJiaoshiPhoto())){
            jiaoshi.setJiaoshiPhoto(null);
        }
        if(jiaoshiEntity==null){
            jiaoshiService.insert(jiaoshi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<JiaoshiEntity> queryWrapper = new EntityWrapper<JiaoshiEntity>()
            .notIn("id",jiaoshi.getId())
            .eq("jiaoshi_bianhao", jiaoshi.getJiaoshiBianhao())
            .eq("username", jiaoshi.getUsername())
            .eq("password", jiaoshi.getPassword())
            .eq("jiaoshi_name", jiaoshi.getJiaoshiName())
            .eq("sex_types", jiaoshi.getSexTypes())
            .eq("jiaoshi_number", jiaoshi.getJiaoshiNumber())
            .eq("jiaoshi_phone", jiaoshi.getJiaoshiPhone())
            .eq("jiaoshi_bumen", jiaoshi.getJiaoshiBumen())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaoshiEntity jiaoshiEntity = jiaoshiService.selectOne(queryWrapper);
        if("".equals(jiaoshi.getJiaoshiPhoto()) || "null".equals(jiaoshi.getJiaoshiPhoto())){
                jiaoshi.setJiaoshiPhoto(null);
        }
        if(jiaoshiEntity==null){
            jiaoshiService.updateById(jiaoshi);//根据id更新
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
        jiaoshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

