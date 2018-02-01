package cn.luwt.com.service;

import cn.luwt.com.pojo.Review;

import java.util.List;

public interface ReviewService {
	void add(Review r);
	void delete(int id);
	void update(Review r);
	Review get(int id);
	List list(int pid);
	int getCount(int pid);
}
