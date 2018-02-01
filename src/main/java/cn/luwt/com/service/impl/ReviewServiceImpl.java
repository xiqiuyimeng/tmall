package cn.luwt.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.luwt.com.mapper.ReviewMapper;
import cn.luwt.com.pojo.Review;
import cn.luwt.com.pojo.ReviewExample;
import cn.luwt.com.pojo.User;
import cn.luwt.com.service.ReviewService;
import cn.luwt.com.service.UserService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewMapper reviewMapper;
	@Autowired
	UserService userService;
	@Override
	public void add(Review r) {
		// TODO Auto-generated method stub
		reviewMapper.insert(r);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		reviewMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Review r) {
		// TODO Auto-generated method stub
		reviewMapper.updateByPrimaryKeySelective(r);
	}

	@Override
	public Review get(int id) {
		// TODO Auto-generated method stub
		return reviewMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Review> list(int pid) {
		// TODO Auto-generated method stub
		ReviewExample example=new ReviewExample();
		example.createCriteria().andPidEqualTo(pid);
		example.setOrderByClause("id desc");
		List<Review> result = reviewMapper.selectByExample(example);
		setUser(result);
		return result;
	}

	@Override
	public int getCount(int pid) {
		// TODO Auto-generated method stub
		return list(pid).size();
	}
	public void setUser(List<Review> rs){
		for(Review r:rs){
			setUser(r);
		}
	}
	public void setUser(Review r){
		int uid = r.getUid();
		User u = userService.get(uid);
		r.setUser(u);
	}

}
