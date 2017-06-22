package mikecl.example.service;

import mikecl.example.entity.User;

public interface UserService 
{
	public User findById(Long id);
	
	public User findByUserid(String userid);
}
