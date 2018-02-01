package cn.luwt.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.luwt.com.mapper.PropertyValueMapper;
import cn.luwt.com.pojo.Product;
import cn.luwt.com.pojo.Property;
import cn.luwt.com.pojo.PropertyValue;
import cn.luwt.com.pojo.PropertyValueExample;
import cn.luwt.com.service.PropertyService;
import cn.luwt.com.service.PropertyValueService;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
	@Autowired
	PropertyValueMapper propertyValueMapper;
	@Autowired
	PropertyService propertyService;
	@Override
	public void init(Product p) {
		// TODO Auto-generated method stub
		List<Property> pts = propertyService.list(p.getCid());//���ݲ�Ʒ��ȡ���࣬��ȡ�˷�������������
		for(Property pt:pts){
			PropertyValue pv = get(pt.getId(),p.getId());//��������id�Ͳ�Ʒid��ѯ���������ֵ
			if(null==pv){				//�������ֵΪ�գ��򴴽�
				pv = new PropertyValue();
				pv.setPid(p.getId());
				pv.setPtid(pt.getId());
				propertyValueMapper.insert(pv);
			}
		}
	}

	@Override
	public void update(PropertyValue pv) {
		// TODO Auto-generated method stub
		propertyValueMapper.updateByPrimaryKeySelective(pv);
	}

	@Override
	public PropertyValue get(int ptid, int pid) {
		// TODO Auto-generated method stub
		PropertyValueExample example = new PropertyValueExample();
		example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
		List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
		if(pvs.isEmpty())
			return null;
		return pvs.get(0);
	}

	@Override
	public List<PropertyValue> list(int pid) {
		// TODO Auto-generated method stub
		PropertyValueExample example = new PropertyValueExample();
		example.createCriteria().andPidEqualTo(pid);
		List<PropertyValue> result = propertyValueMapper.selectByExample(example);
		for(PropertyValue pv:result){
			Property property = propertyService.get(pv.getPtid());
			pv.setProperty(property);
		}
		return result;
	}

}
