package me.sungbin.review.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import me.sungbin.review.login.vo.ReviewVO;

@Mapper
public interface ReviewMapper {

	List<ReviewVO> getReviewList();

	int insertReview(ReviewVO reviewVO);
}
