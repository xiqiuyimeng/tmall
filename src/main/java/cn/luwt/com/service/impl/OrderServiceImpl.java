package cn.luwt.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.luwt.com.mapper.OrderMapper;
import cn.luwt.com.pojo.Order;
import cn.luwt.com.pojo.OrderExample;
import cn.luwt.com.pojo.OrderItem;
import cn.luwt.com.pojo.User;
import cn.luwt.com.service.OrderItemService;
import cn.luwt.com.service.OrderService;
import cn.luwt.com.service.UserService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	UserService userService;
	@Autowired
	OrderItemService orderItemService;
	@Override
	public void add(Order o) {
		// TODO Auto-generated method stub
		orderMapper.insert(o);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		orderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Order o) {
		// TODO Auto-generated method stub
		orderMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public Order get(int id) {
		// TODO Auto-generated method stub
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Order> list() {
		// TODO Auto-generated method stub
		OrderExample example=new OrderExample();
		example.setOrderByClause("id desc");
		List<Order> result = orderMapper.selectByExample(example);
		setUser(result);
		return result;
	}
	public void setUser(List<Order> os){
		for(Order o:os){
			setUser(o);
		}
	}
	public void setUser(Order o){
		int uid = o.getUid();
		User u = userService.get(uid);
		o.setUser(u);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackForClassName="Exception")
	public float add(Order o, List<OrderItem> ois) {
		// TODO Auto-generated method stub
		float total = 0;
		add(o);
		if(false)
			throw new RuntimeException();
		for(OrderItem oi:ois){
			oi.setOid(o.getId());
			orderItemService.update(oi);
			total+=oi.getProduct().getPromotePrice()*oi.getNumber();
		}
		return total;
	}

	@Override
	public List list(int uid, String excludedStatus) {
		// TODO Auto-generated method stub
		OrderExample example = new OrderExample();
		example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
		example.setOrderByClause("id desc");
		return orderMapper.selectByExample(example);
	}
}
