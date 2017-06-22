package mikecl.example.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import mikecl.example.entity.User;
import mikecl.example.service.UserService;

@Controller
public class IndexController 
{
	@Resource
	private UserService userService;
	
	@GetMapping("/**")
	public String index()
	{
		User u = userService.findById(Long.valueOf("1"));
		System.out.println(u.getName());
		System.out.println(u.getUserid());
		System.out.println(1);
		return "index";
	}
}
