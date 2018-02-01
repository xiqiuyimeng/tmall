package cn.luwt.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.luwt.com.mapper.PropertyMapper;
import cn.luwt.com.pojo.Property;
import cn.luwt.com.pojo.PropertyExample;
import cn.luwt.com.service.PropertyService;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	PropertyMapper propertyMapper;
	
	@Override
	public void add(Property p) {
		// TODO Auto-generated method stub
		propertyMapper.insert(p);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		propertyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Property p) {
		// TODO Auto-generated method stub
		propertyMapper.updateByPrimaryKeySelective(p);
	}

	@Override
	public Property get(int id) {
		// TODO Auto-generated method stub
		return propertyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List list(int cid) {
		// TODO Auto-generated method stub
		PropertyExample example = new PropertyExample();
		example.createCriteria().andCidEqualTo(cid);
		example.setOrderByClause("id desc");//��id��������
		return propertyMapper.selectByExample(example);
	}

}
