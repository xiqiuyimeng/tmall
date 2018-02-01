package cn.luwt.com.comparator;

import cn.luwt.com.pojo.Product;

import java.util.Comparator;

public class ProductDateComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		// TODO Auto-generated method stub
		return p1.getCreateDate().compareTo(p2.getCreateDate());
	}

}
