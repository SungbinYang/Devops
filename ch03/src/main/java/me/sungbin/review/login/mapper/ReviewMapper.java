package me.sungbin.review.login.mapper;

import me.sungbin.review.login.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



@Mapper
public interface ReviewMapper {

	List<ReviewVO> getReviewList();
	
}
