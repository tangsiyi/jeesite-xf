package com.thinkgem.jeesite.modules.xf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.xf.entity.Register;

@RestController
@RequestMapping("/xf/user")
public class RegisterController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SystemService systemService;

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(@RequestBody Register register) {
		
		User u1 = systemService.getUserByMobile(register.getMobile());
		if(u1 != null) {//手机号已经注册
			return "1";
		}
		
		User user = new User();
		user.setCompany(new Office("1"));
		user.setOffice(new Office("1"));
		user.setLoginName(register.getMobile());
		user.setPassword(SystemService.entryptPassword(register.getPassword()));
		
		// 保存用户信息
		systemService.saveUser(user);
		
		return "0";
	}
	
	
	
}
