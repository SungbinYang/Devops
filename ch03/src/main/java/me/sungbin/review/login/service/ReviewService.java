package me.sungbin.review.login.service;

import java.util.List;

import me.sungbin.review.login.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.sungbin.review.login.vo.ReviewVO;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;

	public List<ReviewVO> getReviewList() {
		return reviewMapper.getReviewList();
	}
	
	
}
