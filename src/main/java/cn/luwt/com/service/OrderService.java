package cn.luwt.com.service;

import cn.luwt.com.pojo.Order;
import cn.luwt.com.pojo.OrderItem;

import java.util.List;

public interface OrderService {
	String waitPay = "waitPay";
	String waitDelivery="waitDelivery";
	String waitConfirm="waitConfirm";
	String waitReview="waitReview";
	String finish="finish";
	String delete="delete";
	
	void add(Order o);
	void delete(int id);
	void update(Order o);
	Order get(int id);
	List list();
	float add(Order o, List<OrderItem> ois);
	List list(int uid, String excludedStatus);
}
