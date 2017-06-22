package mikecl.example.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import mikecl.example.entity.User;
import mikecl.example.mapper.UserMapper;
import mikecl.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	
	@Override
	public User findById(Long id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public User findByUserid(String userid) {
		User user = userMapper.selectByUserid(userid);
		return user;
	}

}
