package com.entity.view;

import com.entity.ShiyanxiangmuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;

/**
 * 实验项目
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @email
 * @date 2021-04-26
 */
@TableName("shiyanxiangmu")
public class ShiyanxiangmuView extends ShiyanxiangmuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	public ShiyanxiangmuView() {

	}
	private String shiyanshiName;

	public String getshiyanshiName() {
		return shiyanshiName;
	}

	public void setshiyanshiName(String shiyanshiName) {
		this.shiyanshiName = shiyanshiName;
	}

	public ShiyanxiangmuView(ShiyanxiangmuEntity shiyanxiangmuEntity) {
		try {
			BeanUtils.copyProperties(this, shiyanxiangmuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
