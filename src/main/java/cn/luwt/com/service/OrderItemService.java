package cn.luwt.com.service;

import cn.luwt.com.pojo.Order;
import cn.luwt.com.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
	void add(OrderItem oi);
	void delete(int id);
	void update(OrderItem oi);
	OrderItem get(int id);
	List list();
	void fill(List<Order> os);
	void fill(Order o);
	int getSaleCount(int pid);
	List<OrderItem> listByUser(int uid);
}
