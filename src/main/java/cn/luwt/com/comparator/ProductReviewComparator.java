package cn.luwt.com.comparator;

import cn.luwt.com.pojo.Product;

import java.util.Comparator;

public class ProductReviewComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		// TODO Auto-generated method stub
		
		return p2.getReviewCount()-p1.getReviewCount();
	}

}
