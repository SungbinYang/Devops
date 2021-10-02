package me.sungbin.review.login.service;

import me.sungbin.review.login.mapper.UserMapper;
import me.sungbin.review.login.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public int updateUserJoin(UserVO userVO) {
		
		return userMapper.updateUserJoin(userVO);
	}
}
