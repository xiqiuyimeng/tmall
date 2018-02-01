package cn.luwt.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.luwt.com.pojo.Product;
import cn.luwt.com.pojo.PropertyValue;
import cn.luwt.com.service.ProductService;
import cn.luwt.com.service.PropertyValueService;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {
	@Autowired
	PropertyValueService propertyValueService;
	@Autowired
	ProductService productService;
	
	@RequestMapping("admin_propertyValue_edit")
	public String edit(Model model,int pid){
		Product p = productService.get(pid);
		propertyValueService.init(p);
		List<PropertyValue> pvs = propertyValueService.list(p.getId());
		model.addAttribute("pvs", pvs);
		model.addAttribute("p", p);
		return "admin/editPropertyValue";
	}
	
	@RequestMapping("admin_propertyValue_update")
	@ResponseBody
	public String update(PropertyValue pv){
		propertyValueService.update(pv);
		return "success";
		
	}
}
