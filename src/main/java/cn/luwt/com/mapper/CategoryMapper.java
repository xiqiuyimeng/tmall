package cn.luwt.com.mapper;

import cn.luwt.com.pojo.Category;
import cn.luwt.com.pojo.CategoryExample;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);//����model�в�Ϊ�յ��ֶ�

    int updateByPrimaryKey(Category record);//���ֶ������ݿ��и���Ϊnull
}