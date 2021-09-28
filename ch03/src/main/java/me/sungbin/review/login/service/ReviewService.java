package me.sungbin.review.login.service;

import java.util.List;

import me.sungbin.review.login.mapper.ReviewMapper;
import me.sungbin.review.login.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;

	public List<ReviewVO> getReviewList() {
		
		return reviewMapper.getReviewList();
	}
	
	
}
