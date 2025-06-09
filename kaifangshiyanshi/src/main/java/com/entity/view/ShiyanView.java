package com.entity.view;

import com.entity.ShiyanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;

/**
 * 学生实验
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @email
 * @date 2021-04-26
 */
@TableName("shiyan")
public class ShiyanView extends ShiyanEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	public ShiyanView() {

	}

	public ShiyanView(ShiyanEntity shiyanEntity) {
		try {
			BeanUtils.copyProperties(this, shiyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
