package me.sungbin.review.login.mapper;

import me.sungbin.review.login.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	int updateUserJoin(UserVO userVO);
}
