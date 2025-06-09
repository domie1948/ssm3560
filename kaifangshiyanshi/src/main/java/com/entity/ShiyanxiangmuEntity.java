package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 实验项目
 *
 * @email
 * @date 2021-04-26
 */
@TableName("shiyanxiangmu")
public class ShiyanxiangmuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public ShiyanxiangmuEntity() {

    }

    public ShiyanxiangmuEntity(T t) {
    try {
    BeanUtils.copyProperties(this, t);
    } catch (IllegalAccessException | InvocationTargetException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
    }


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;

    /**
     * 负责教师
     */
    @TableField(value = "jiaoshi_types")

    private Integer jiaoshiTypes;


    /**
     * 使用实验室
     */
    @TableField(value = "yuye")

    private Integer yuye;


    /**
     * 项目名称
     */
    @TableField(value = "shiyanxiangmu_name")

    private String shiyanxiangmuName;


    /**
     * 项目内容
     */
    @TableField(value = "shiyanxiangmu_content")

    private String shiyanxiangmuContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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

    @Override
    public String toString() {
    return "Shiyanxiangmu{" +
            "id=" + id +
            ", jiaoshiTypes=" + jiaoshiTypes +
            ", yuye=" + yuye +
            ", shiyanxiangmuName=" + shiyanxiangmuName +
            ", shiyanxiangmuContent=" + shiyanxiangmuContent +
            ", createTime=" + createTime +
    "}";
    }
    }
