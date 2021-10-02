package me.sungbin.review.login.service;

import me.sungbin.review.login.mapper.ReviewMapper;
import me.sungbin.review.login.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
	
	
	@Autowired
	private ReviewMapper reviewMapper;
	

	public List<ReviewVO> getReviewList() {
		
		return reviewMapper.getReviewList();
	}
	
	
}
