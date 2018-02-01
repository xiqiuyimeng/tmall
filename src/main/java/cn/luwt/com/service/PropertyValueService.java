package cn.luwt.com.service;

import cn.luwt.com.pojo.Product;
import cn.luwt.com.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
	void init(Product p);
	void update(PropertyValue pv);
	
	PropertyValue get(int ptid, int pid);//��������id�Ͳ�Ʒid��ȡPropertyValue����
	List<PropertyValue> list(int pid);
}
