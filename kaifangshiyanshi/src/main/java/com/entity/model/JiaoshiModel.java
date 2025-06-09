package com.entity.model;

import com.entity.JiaoshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 教师
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @email
 * @date 2021-04-26
 */
public class JiaoshiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 教师编号
     */
    private String jiaoshiBianhao;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 教师姓名  Search 
     */
    private String jiaoshiName;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 身份证号
     */
    private String jiaoshiNumber;


    /**
     * 手机号
     */
    private String jiaoshiPhone;


    /**
     * 照片
     */
    private String jiaoshiPhoto;


    /**
     * 教师部门
     */
    private String jiaoshiBumen;


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
	 * 设置：教师编号
	 */
    public String getJiaoshiBianhao() {
        return jiaoshiBianhao;
    }


    /**
	 * 获取：教师编号
	 */

    public void setJiaoshiBianhao(String jiaoshiBianhao) {
        this.jiaoshiBianhao = jiaoshiBianhao;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：教师姓名  Search 
	 */
    public String getJiaoshiName() {
        return jiaoshiName;
    }


    /**
	 * 获取：教师姓名  Search 
	 */

    public void setJiaoshiName(String jiaoshiName) {
        this.jiaoshiName = jiaoshiName;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：身份证号
	 */
    public String getJiaoshiNumber() {
        return jiaoshiNumber;
    }


    /**
	 * 获取：身份证号
	 */

    public void setJiaoshiNumber(String jiaoshiNumber) {
        this.jiaoshiNumber = jiaoshiNumber;
    }
    /**
	 * 设置：手机号
	 */
    public String getJiaoshiPhone() {
        return jiaoshiPhone;
    }


    /**
	 * 获取：手机号
	 */

    public void setJiaoshiPhone(String jiaoshiPhone) {
        this.jiaoshiPhone = jiaoshiPhone;
    }
    /**
	 * 设置：照片
	 */
    public String getJiaoshiPhoto() {
        return jiaoshiPhoto;
    }


    /**
	 * 获取：照片
	 */

    public void setJiaoshiPhoto(String jiaoshiPhoto) {
        this.jiaoshiPhoto = jiaoshiPhoto;
    }
    /**
	 * 设置：教师部门
	 */
    public String getJiaoshiBumen() {
        return jiaoshiBumen;
    }


    /**
	 * 获取：教师部门
	 */

    public void setJiaoshiBumen(String jiaoshiBumen) {
        this.jiaoshiBumen = jiaoshiBumen;
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
