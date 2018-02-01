package cn.luwt.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.luwt.com.mapper.UserMapper;
import cn.luwt.com.pojo.User;
import cn.luwt.com.pojo.UserExample;
import cn.luwt.com.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	@Override
	public void add(User u) {
		// TODO Auto-generated method stub
		userMapper.insert(u);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(u);
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List list() {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		example.setOrderByClause("id desc");
		return userMapper.selectByExample(example);
	}

	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name);
		List<User> result = userMapper.selectByExample(example);
		if(!result.isEmpty())
			return true;
		return false;
	}

	@Override
	public User get(String name, String password) {
		// TODO Auto-generated method stub
		UserExample example=new UserExample();
		example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
		List<User> result = userMapper.selectByExample(example);
		if(result.isEmpty())
			return null;
		return result.get(0);
	}

}
