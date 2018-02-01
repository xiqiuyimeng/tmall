package cn.luwt.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.luwt.com.mapper.ProductMapper;
import cn.luwt.com.pojo.Category;
import cn.luwt.com.pojo.Product;
import cn.luwt.com.pojo.ProductExample;
import cn.luwt.com.pojo.ProductImage;
import cn.luwt.com.service.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductMapper productMapper;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	ReviewService reviewService;
	@Override
	public void add(Product p) {
		// TODO Auto-generated method stub
		productMapper.insert(p);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		productMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Product p) {
		// TODO Auto-generated method stub
		productMapper.updateByPrimaryKeySelective(p);
	}

	@Override
	public Product get(int id) {
		// TODO Auto-generated method stub
		Product p = productMapper.selectByPrimaryKey(id);
		setFirstProductImage(p);
		setCategory(p);
		return p;
	}

	public void setCategory(Product p) {
		// TODO Auto-generated method stub
		int cid = p.getCid();
		Category c = categoryService.get(cid);
		p.setCategory(c);
	}

	@Override
	public List list(int cid) {
		// TODO Auto-generated method stub
		ProductExample example = new ProductExample();
		example.createCriteria().andCidEqualTo(cid);
		example.setOrderByClause("id desc");
		List result = productMapper.selectByExample(example);
		setFirstProductImage(result);
		setCategory(result);
		return result;
	}

	public void setCategory(List<Product> ps) {
		// TODO Auto-generated method stub
		for(Product p:ps)
			setCategory(p);
	}

	@Override
	public void setFirstProductImage(Product p) {
		// TODO Auto-generated method stub
		List<ProductImage> pis = productImageService.list(p.getId(),ProductImageService.type_single);
		if(!pis.isEmpty()){
			ProductImage pi = pis.get(0);
			p.setFirstProductImage(pi);
		}
	}

	@Override
	public void fill(List<Category> cs) {
		// TODO Auto-generated method stub
		for(Category c:cs){
			fill(c);
		}
	}

	@Override
	public void fill(Category c) {
		// TODO Auto-generated method stub
		List<Product> ps = list(c.getId());
		c.setProducts(ps);
	}

	@Override
	public void fillByRow(List<Category> cs) {
		// TODO Auto-generated method stub
		int productNumberEachRow = 8;
		for(Category c:cs){
			List<Product> products = c.getProducts();
			List<List<Product>> productsByRow = new ArrayList<>();
			for(int i=0;i < products.size();i+=productNumberEachRow){
				int size = i+productNumberEachRow;
				size = size > products.size()?products.size():size;
				List<Product> productsOfEachRow = products.subList(i, size);
				productsByRow.add(productsOfEachRow);
			}
			c.setProductsByRow(productsByRow);
		}
	}
	
	public void setFirstProductImage(List<Product> ps){
		for(Product p:ps){
			setFirstProductImage(p);
		}
	}

	@Override
	public void setSaleAndReviewNumber(Product p) {
		// TODO Auto-generated method stub
		int saleCount = orderItemService.getSaleCount(p.getId());
		p.setSaleCount(saleCount);
		int reviewCount = reviewService.getCount(p.getId());
		p.setReviewCount(reviewCount);
	}

	@Override
	public void setSaleAndReviewNumber(List<Product> ps) {
		// TODO Auto-generated method stub
		for(Product p:ps){
			setSaleAndReviewNumber(p);
		}
		
	}

	@Override
	public List<Product> search(String keyword) {
		// TODO Auto-generated method stub
		ProductExample example = new ProductExample();
		example.createCriteria().andNameLike("%"+keyword+"%");
		example.setOrderByClause("id desc");
		List<Product> result = productMapper.selectByExample(example);
		setFirstProductImage(result);
		setCategory(result);
		return result;
	}

}
