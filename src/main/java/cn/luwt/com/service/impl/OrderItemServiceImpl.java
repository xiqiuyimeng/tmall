package cn.luwt.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.luwt.com.mapper.OrderItemMapper;
import cn.luwt.com.pojo.Order;
import cn.luwt.com.pojo.OrderItem;
import cn.luwt.com.pojo.OrderItemExample;
import cn.luwt.com.pojo.Product;
import cn.luwt.com.service.OrderItemService;
import cn.luwt.com.service.ProductService;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	OrderItemMapper orderItemMapper;
	@Autowired
	ProductService productService;
	@Override
	public void add(OrderItem oi) {
		// TODO Auto-generated method stub
		orderItemMapper.insert(oi);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		orderItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(OrderItem oi) {
		// TODO Auto-generated method stub
		orderItemMapper.updateByPrimaryKeySelective(oi);
	}

	@Override
	public OrderItem get(int id) {
		// TODO Auto-generated method stub
		OrderItem result = orderItemMapper.selectByPrimaryKey(id);
		setProduct(result);
		return result;
	}

	@Override
	public List<OrderItem> list() {
		// TODO Auto-generated method stub
		OrderItemExample example = new OrderItemExample();
		example.setOrderByClause("id desc");
		return orderItemMapper.selectByExample(example);
	}

	@Override
	public void fill(List<Order> os) {
		// TODO Auto-generated method stub
		for(Order o:os){
			fill(o);
		}
	}

	@Override
	public void fill(Order o) {
		// TODO Auto-generated method stub
		OrderItemExample example = new OrderItemExample();
		example.createCriteria().andOidEqualTo(o.getId());
		example.setOrderByClause("id desc");
		List<OrderItem> ois = orderItemMapper.selectByExample(example);
		setProduct(ois);
		float total=0;
		int totalNumber = 0;
		for(OrderItem oi:ois){
			total+=oi.getNumber()*oi.getProduct().getPromotePrice();
			totalNumber+=oi.getNumber();
		}
		o.setTotal(total);
		o.setTotalNumber(totalNumber);
		o.setOrderItems(ois);
	}
	
	public void setProduct(List<OrderItem> ois){
		for(OrderItem oi:ois){
			setProduct(oi);
		}
	}
	
	public void setProduct(OrderItem oi){
		Product p = productService.get(oi.getPid());
		oi.setProduct(p);
	}

	@Override
	public int getSaleCount(int pid) {
		// TODO Auto-generated method stub
		OrderItemExample example=new OrderItemExample();
		example.createCriteria().andPidEqualTo(pid);
		List<OrderItem> ois = orderItemMapper.selectByExample(example);
		int result=0;
		for(OrderItem oi:ois){
			result+=oi.getNumber();
		}
		return result;
	}

	@Override
	public List<OrderItem> listByUser(int uid) {
		// TODO Auto-generated method stub
		OrderItemExample example = new OrderItemExample();
		example.createCriteria().andUidEqualTo(uid).andOidIsNull();
		List<OrderItem> result = orderItemMapper.selectByExample(example);
		setProduct(result);
		return result;
	}

}
