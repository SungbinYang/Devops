package me.sungbin.review.login.mapper;

import java.util.List;

import me.sungbin.review.login.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

	List<ReviewVO> getReviewList();
	
}
