package cn.luwt.com.controller;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import cn.luwt.com.comparator.*;
import cn.luwt.com.pojo.*;
import cn.luwt.com.service.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ForeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	PropertyValueService propertyValueService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	ReviewService reviewService;

	@RequestMapping("forehome")
	public String home(Model model) {
		List<Category> cs = categoryService.list();
		productService.fill(cs);
		productService.fillByRow(cs);
		model.addAttribute("cs", cs);
		return "fore/home";
	}

	@RequestMapping("foreregister")
	public String register(Model model, User u) {
		String name = u.getName();
		name = HtmlUtils.htmlEscape(name);
		u.setName(name);
		boolean exist = userService.isExist(name);
		if (exist) {
			String m = "�û����Ѿ���ռ�ã�����ʹ��";
			model.addAttribute("m", m);
			model.addAttribute("u", null);
			return "fore/register";
		}
		userService.add(u);
		return "redirect:registerSuccessPage";
	}

	@RequestMapping("forelogin")
	public String login(@RequestParam("name") String name,
			@RequestParam("password") String password, Model model,
			HttpSession session) {
		name = HtmlUtils.htmlEscape(name);
		User user = userService.get(name, password);
		if (null == user) {
			model.addAttribute("msg", "�˺��������");
			return "fore/login";
		}
		session.setAttribute("user", user);
		return "redirect:forehome";
	}

	@RequestMapping("forelogout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:forehome";
	}

	@RequestMapping("foreproduct")
	public String product(int pid, Model model) {
		Product p = productService.get(pid);
		List<ProductImage> productSingleImages = productImageService.list(
				p.getId(), ProductImageService.type_single);
		List<ProductImage> productDetailImages = productImageService.list(
				p.getId(), ProductImageService.type_detail);
		p.setProductSingleImages(productSingleImages);
		p.setProductDetailImages(productDetailImages);
		List<PropertyValue> pvs = propertyValueService.list(p.getId());
		List<Review> rs = reviewService.list(p.getId());
		productService.setSaleAndReviewNumber(p);
		model.addAttribute("pvs", pvs);
		model.addAttribute("rs", rs);
		model.addAttribute("p", p);
		return "fore/product";
	}

	@RequestMapping("forecheckLogin")
	@ResponseBody
	public String checkLogin(HttpSession session) {
		User u = (User) session.getAttribute("user");
		if (null != u)
			return "success";
		return "fail";
	}

	@RequestMapping("foreloginAjax")
	@ResponseBody
	public String loginAjax(@RequestParam("name") String name,
			@RequestParam("password") String password, HttpSession session) {
		name = HtmlUtils.htmlEscape(name);
		User u = userService.get(name, password);
		if (null == u) {
			return "fail";
		}
		session.setAttribute("user", u);
		return "success";
	}

	@RequestMapping("forecategory")
	public String category(int cid, String sort, Model model) {
		Category c = categoryService.get(cid);
		productService.fill(c);
		productService.setSaleAndReviewNumber(c.getProducts());
		if (null != sort) {
			switch (sort) {
			case "review":
				Collections
						.sort(c.getProducts(), new ProductReviewComparator());
				break;
			case "date":
				Collections.sort(c.getProducts(), new ProductDateComparator());
				break;
			case "saleCount":
				Collections.sort(c.getProducts(),
						new ProductSaleCountComparator());
				break;
			case "price":
				Collections.sort(c.getProducts(), new ProductPriceComparator());
				break;
			case "all":
				Collections.sort(c.getProducts(), new ProductAllComparator());
				break;
			}
		}
		model.addAttribute("c", c);
		return "fore/category";
	}

	@RequestMapping("foresearch")
	public String search(String keyword, Model model) {
		PageHelper.offsetPage(0, 20);
		List<Product> ps = productService.search(keyword);
		productService.setSaleAndReviewNumber(ps);
		model.addAttribute("ps", ps);
		return "fore/searchResult";
	}

	@RequestMapping("forebuyone")
	public String buyone(int pid, int num, HttpSession session) {
		Product p = productService.get(pid);
		int oiid = 0;
		User user = (User) session.getAttribute("user");
		boolean found = false;
		List<OrderItem> ois = orderItemService.listByUser(user.getId());
		for (OrderItem oi : ois) {
			if (oi.getProduct().getId().intValue() == p.getId().intValue()) {
				oi.setNumber(oi.getNumber() + num);
				orderItemService.update(oi);
				found = true;
				oiid = oi.getId();
				break;
			}
		}
		if (!found) {
			OrderItem oi = new OrderItem();
			oi.setUid(user.getId());
			oi.setNumber(num);
			oi.setPid(pid);
			orderItemService.add(oi);
			oiid = oi.getId();
		}
		return "redirect:forebuy?oiid=" + oiid;
	}

	@RequestMapping("forebuy")
	public String buy(Model model, String[] oiid, HttpSession session) {
		List<OrderItem> ois = new ArrayList<>();
		float total = 0;
		for (String strid : oiid) {
			int id = Integer.parseInt(strid);
			OrderItem oi = orderItemService.get(id);
			total += oi.getProduct().getPromotePrice() * oi.getNumber();
			ois.add(oi);
		}
		session.setAttribute("ois", ois);
		model.addAttribute("total", total);
		return "fore/buy";
	}

	@RequestMapping("forecart")
	public String cart(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<OrderItem> ois = orderItemService.listByUser(user.getId());
		model.addAttribute("ois", ois);
		return "fore/cart";
	}

	@RequestMapping("foreaddCart")
	@ResponseBody
	public String addCart(int pid, int num, Model model, HttpSession session) {
		Product p = productService.get(pid);
		User user = (User) session.getAttribute("user");
		boolean found = false;

		List<OrderItem> ois = orderItemService.listByUser(user.getId());
		for (OrderItem oi : ois) {
			if (oi.getProduct().getId().intValue() == p.getId().intValue()) {
				oi.setNumber(oi.getNumber() + num);
				orderItemService.update(oi);
				found = true;
				break;
			}
		}
		if (!found) {
			OrderItem oi = new OrderItem();
			oi.setUid(user.getId());
			oi.setNumber(num);
			oi.setPid(pid);
			orderItemService.add(oi);
		}
		return "success";
	}
	@RequestMapping("forechangeOrderItem")
	@ResponseBody
	public String changeOrderItem(Model model,HttpSession session,int pid,int number){
		User u = (User) session.getAttribute("user");
		if(null==u)
			return "fail";
		List<OrderItem> ois = orderItemService.listByUser(u.getId());
		for(OrderItem oi:ois){
			if(oi.getProduct().getId().intValue()==pid){
				oi.setNumber(number);
				orderItemService.update(oi);
				break;
			}
		}
		return "success";
	}
	@RequestMapping("foredeleteOrderItem")
	@ResponseBody
	public String deleteOrderItem(Model model,HttpSession session,int oiid){
		User u = (User) session.getAttribute("user");
		if(null==u)
			return "fail";
		orderItemService.delete(oiid);
		return "success";
	}
	@RequestMapping("foredeleteOrder")
	@ResponseBody
	public String delete(Model model,int oid){
		Order o = orderService.get(oid);
		o.setStatus(orderService.delete);
		orderService.delete(oid);
		return "success";
	}
	@RequestMapping("forecreateOrder")
	public String createOrder(Model model,Order order,HttpSession session){
		User u = (User) session.getAttribute("user");
		String orderCode = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+RandomUtils.nextInt(10000);
		order.setOrderCode(orderCode);
		order.setCreateDate(new Date());
		order.setUid(u.getId());
		order.setStatus(orderService.waitPay);
		List<OrderItem> ois = (List<OrderItem>) session.getAttribute("ois");
		float total = orderService.add(order, ois);
		return "redirect:forealipay?oid="+order.getId()+"&total="+total;
	}
	@RequestMapping("forepayed")
	public String payed(int oid,float total,Model model){
		Order o = orderService.get(oid);
		o.setStatus(OrderService.waitDelivery);
		o.setPayDate(new Date());
		orderService.update(o);
		model.addAttribute("o", o);
		return "fore/payed";
	}
	@RequestMapping("forebought")
	public String bought(Model model,HttpSession session){
		User u = (User) session.getAttribute("user");
		List<Order> os = orderService.list(u.getId(),OrderService.delete);
		orderItemService.fill(os);
		model.addAttribute("os", os);
		return "fore/bought";
	}
	@RequestMapping("foreconfirmPay")
	public String confirmPay(Model model,int oid){
		Order o = orderService.get(oid);
		orderItemService.fill(o);
		model.addAttribute("o", o);
		return "fore/confirmPay";
	}
	@RequestMapping("foreorderConfirmed")
	public String orderConfirmed(Model model,int oid){
		Order o =orderService.get(oid);
		o.setStatus(orderService.waitReview);
		o.setConfirmDate(new Date());
		orderService.update(o);
		return "fore/orderConfirmed";
	}
	@RequestMapping("forereview")
	public String review(Model model,int oid){
		Order o = orderService.get(oid);
		orderItemService.fill(o);
		Product p = o.getOrderItems().get(0).getProduct();
		List<Review> reviews = reviewService.list(p.getId());
		productService.setSaleAndReviewNumber(p);
		model.addAttribute("p", p);
		model.addAttribute("o", o);
		model.addAttribute("reviews", reviews);
		return "fore/review";
	}
	@RequestMapping("foredoreview")
	public String doreview(Model mdoel,HttpSession session,@RequestParam("oid")int oid,@RequestParam("pid")int pid,String content){
		Order o = orderService.get(oid);
		o.setStatus(orderService.finish);
		orderService.update(o);
		Product p = productService.get(pid);
		content = HtmlUtils.htmlEscape(content);
		User u = (User) session.getAttribute("user");
		Review r = new Review();
		r.setContent(content);
		r.setCreateDate(new Date());
		r.setPid(pid);
		r.setUid(u.getId());
		reviewService.add(r);
		return "redirect:forereview?oid="+oid+"&showonly=true";
	}
}
