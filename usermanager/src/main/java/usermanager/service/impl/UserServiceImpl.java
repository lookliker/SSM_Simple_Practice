package usermanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import usermanager.domain.User;
import usermanager.mapper.UserMapper;
import usermanager.service.IUserService;
@Service
@Transactional
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;
	public User get(int id) {
		return userMapper.get(id);
	}

	public List<User> getAll() {
		return userMapper.getAll();
	}

	public void insert(User u) {
		userMapper.insert(u);
	}

	public void delete(int id) {
		userMapper.delete(id);
	}

	public void update(User u, int id) {
		userMapper.update(u, id);
	}

	public void updateImage(String image, int id) {
		userMapper.updateImage(image, id);
	}

}
