package cn.luwt.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.luwt.com.mapper.CategoryMapper;
import cn.luwt.com.pojo.Category;
import cn.luwt.com.pojo.CategoryExample;
import cn.luwt.com.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
CategoryMapper categoryMapper;
	
	@Override
	public void add(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.insert(category);
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		categoryMapper.deleteByPrimaryKey(id);
	}
	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		return categoryMapper.selectByPrimaryKey(id);
	}
	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.updateByPrimaryKeySelective(category);
	}
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		CategoryExample example = new CategoryExample();
		example.setOrderByClause("id desc");//��id��������
		return categoryMapper.selectByExample(example);
	}

}
