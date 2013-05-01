package com.mkfree.framework.common.spring;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.mkfree.framework.common.utils.date.TimeUtils;

public class KBeanUtils extends BeanUtils {

	static String SET = "set";
	static String GET = "get";

	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {

			A a = new A();
			a.setId(10);
			a.setCreateTime(new Date());
			B b = new B();
			long start = System.currentTimeMillis();
			copyProperties(a, b);
			System.out.println(System.currentTimeMillis() - start);
			System.out.println(b.getId());
			System.out.println(b.getCreateTime());
		}
	}

	/**
	 * 对象copy
	 * 
	 * @param orig
	 * @param dest
	 */
	public static void copyProperties(Object orig, Object dest) {
		copyProperties(orig, dest, new String[] {});
	}

	/**
	 * 对象copy ,可以过滤字段
	 * 
	 * @param orig
	 * @param dest
	 * @param ignoreFiled 不复制的字段
	 */
	public static void copyProperties(Object orig, Object dest, String[] ignoreFiled) {
		try {
			Field[] origFiles = orig.getClass().getDeclaredFields();
			List<String> ignoreFields = new ArrayList<String>();
			for (int i = 0; i < ignoreFiled.length; i++) {
				ignoreFields.add(ignoreFiled[i]);
			}
			for (int i = 0; i < origFiles.length; i++) {
				Field origField = origFiles[i];
				if (origField.getType().isInstance(new Date())) {
					ignoreFields.add(origField.getName());
					Method origMethod = orig.getClass().getDeclaredMethod(GET + origField.getName().substring(0, 1).toUpperCase() + origField.getName().substring(1));
					Object origresult = origMethod.invoke(orig);
					if (origresult == null) {
						continue;
					}
					Method destMethod = dest.getClass().getDeclaredMethod(SET + origField.getName().substring(0, 1).toUpperCase() + origField.getName().substring(1), String.class);
					destMethod.invoke(dest, new Object[] { TimeUtils.tiemDateFormatToString((Date) origresult) });
				}
			}
			BeanUtils.copyProperties(orig, dest, ignoreFields.toArray(new String[ignoreFields.size()]));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}

class A {
	private int id;
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

class B {
	private int id;
	private String createTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}