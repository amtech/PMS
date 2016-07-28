package com.cjhb.ssm.credit.system.service.customer;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.dao.customer.CustomerMapper;
import com.credit.vo.customer.CustomerVO;

@Service
@Transactional
public class CustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	
	
	public List<CustomerVO> queryList(Map<String, Object> map){
		return customerMapper.listByMap(map);
	}
	
}
