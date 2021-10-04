package me.sungbin.review.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import me.sungbin.review.login.vo.UserVO;

@Mapper
public interface UserMapper {
	int updateUserJoin(UserVO userVO);
}
