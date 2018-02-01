package cn.luwt.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.luwt.com.mapper.ProductImageMapper;
import cn.luwt.com.pojo.ProductImage;
import cn.luwt.com.pojo.ProductImageExample;
import cn.luwt.com.service.ProductImageService;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	@Autowired
	ProductImageMapper productImageMapper;

	@Override
	public void add(ProductImage pi) {
		// TODO Auto-generated method stub
		productImageMapper.insert(pi);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		productImageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(ProductImage pi) {
		// TODO Auto-generated method stub
		productImageMapper.updateByPrimaryKeySelective(pi);
	}

	@Override
	public ProductImage get(int id) {
		// TODO Auto-generated method stub
		return productImageMapper.selectByPrimaryKey(id);
	}

	@Override
	public List list(int pid, String type) {
		// TODO Auto-generated method stub
		ProductImageExample example = new ProductImageExample();
		example.createCriteria().andPidEqualTo(pid).andTypeEqualTo(type);//ͬʱƥ��pid��type
		example.setOrderByClause("id desc");//��������
		return productImageMapper.selectByExample(example);
	}
	
}
