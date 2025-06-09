package com.entity.view;

import com.entity.YuyeEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;

/**
 * 实验室预约
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @email
 * @date 2021-04-26
 */
@TableName("yuye")
public class YuyeView extends YuyeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	public YuyeView() {

	}

	public YuyeView(YuyeEntity yuyeEntity) {
		try {
			BeanUtils.copyProperties(this, yuyeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
