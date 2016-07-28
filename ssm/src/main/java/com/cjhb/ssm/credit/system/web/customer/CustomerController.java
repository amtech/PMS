package com.cjhb.ssm.credit.system.web.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.credit.comm.util.page.PageForJqGrid;
import com.credit.entity.customer.Customer;
import com.credit.service.customer.CustomerService;
import com.credit.vo.customer.CustomerVO;
import com.credit.web.BaseController;


@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController{
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/")
	public ModelAndView index(){
		return new ModelAndView("customer/customer-index.jsp");
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageForJqGrid<CustomerVO> getList(PageForJqGrid<CustomerVO> page,Customer customer) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customername", customer.getCustomername());
		map.put("risklevel", customer.getRisklevel());
		map = page.pageToMap(page,map);
		List<CustomerVO> list = customerService.queryList(map);
		page.listToPage(page, list);
		return page;
	}
	
}
