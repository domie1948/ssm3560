package com.entity.vo;

import com.entity.ShiyanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 学生实验
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @email
 * @date 2021-04-26
 */
@TableName("shiyan")
public class ShiyanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 实验学生
     */

    @TableField(value = "yonghu_types")
    private Integer yonghuTypes;


    /**
     * 实验项目
     */

    @TableField(value = "shiyanxiangmu_types")
    private Integer shiyanxiangmuTypes;


    /**
     * 实验进度
     */

    @TableField(value = "shiyan_types")
    private Integer shiyanTypes;


    /**
     * 实验时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 实验结果
     */

    @TableField(value = "shiyan_content")
    private String shiyanContent;


    /**
     * 教师评分
     */

    @TableField(value = "shiyanxiangmu_number")
    private Integer shiyanxiangmuNumber;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：实验学生
	 */
    public Integer getYonghuTypes() {
        return yonghuTypes;
    }


    /**
	 * 获取：实验学生
	 */

    public void setYonghuTypes(Integer yonghuTypes) {
        this.yonghuTypes = yonghuTypes;
    }
    /**
	 * 设置：实验项目
	 */
    public Integer getShiyanxiangmuTypes() {
        return shiyanxiangmuTypes;
    }


    /**
	 * 获取：实验项目
	 */

    public void setShiyanxiangmuTypes(Integer shiyanxiangmuTypes) {
        this.shiyanxiangmuTypes = shiyanxiangmuTypes;
    }
    /**
	 * 设置：实验进度
	 */
    public Integer getShiyanTypes() {
        return shiyanTypes;
    }


    /**
	 * 获取：实验进度
	 */

    public void setShiyanTypes(Integer shiyanTypes) {
        this.shiyanTypes = shiyanTypes;
    }
    /**
	 * 设置：实验时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：实验时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：实验结果
	 */
    public String getShiyanContent() {
        return shiyanContent;
    }


    /**
	 * 获取：实验结果
	 */

    public void setShiyanContent(String shiyanContent) {
        this.shiyanContent = shiyanContent;
    }
    /**
	 * 设置：教师评分
	 */
    public Integer getShiyanxiangmuNumber() {
        return shiyanxiangmuNumber;
    }


    /**
	 * 获取：教师评分
	 */

    public void setShiyanxiangmuNumber(Integer shiyanxiangmuNumber) {
        this.shiyanxiangmuNumber = shiyanxiangmuNumber;
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
