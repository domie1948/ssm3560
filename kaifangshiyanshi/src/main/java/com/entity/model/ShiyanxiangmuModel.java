package com.entity.model;

import com.entity.ShiyanxiangmuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 实验项目
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @email
 * @date 2021-04-26
 */
public class ShiyanxiangmuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 负责教师
     */
    private Integer jiaoshiTypes;


    /**
     * 使用实验室
     */
    private Integer yuye;


    /**
     * 项目名称
     */
    private String shiyanxiangmuName;


    /**
     * 项目内容
     */
    private String shiyanxiangmuContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：负责教师
	 */
    public Integer getJiaoshiTypes() {
        return jiaoshiTypes;
    }


    /**
	 * 获取：负责教师
	 */

    public void setJiaoshiTypes(Integer jiaoshiTypes) {
        this.jiaoshiTypes = jiaoshiTypes;
    }
    /**
	 * 设置：使用实验室
	 */
    public Integer getYuye() {
        return yuye;
    }


    /**
	 * 获取：使用实验室
	 */

    public void setYuye(Integer yuye) {
        this.yuye = yuye;
    }
    /**
	 * 设置：项目名称
	 */
    public String getShiyanxiangmuName() {
        return shiyanxiangmuName;
    }


    /**
	 * 获取：项目名称
	 */

    public void setShiyanxiangmuName(String shiyanxiangmuName) {
        this.shiyanxiangmuName = shiyanxiangmuName;
    }
    /**
	 * 设置：项目内容
	 */
    public String getShiyanxiangmuContent() {
        return shiyanxiangmuContent;
    }


    /**
	 * 获取：项目内容
	 */

    public void setShiyanxiangmuContent(String shiyanxiangmuContent) {
        this.shiyanxiangmuContent = shiyanxiangmuContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
