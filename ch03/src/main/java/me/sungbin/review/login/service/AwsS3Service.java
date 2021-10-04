package me.sungbin.review.login.service;

import java.io.File;
import java.io.IOException;

import me.sungbin.review.login.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import me.sungbin.review.login.vo.ReviewVO;

@Service
public class AwsS3Service {
	private static final String BUCKET_NAME = "reviewsaws";
	private static final String ACCESS_KEY = "AKIAQSHNZGJGVA72T67H";
	private static final String SECRET_KEY = "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";
	
	private AmazonS3 s3;
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	public AwsS3Service() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		
		s3 = AmazonS3Client.builder()
				.withRegion(Regions.AP_NORTHEAST_2) /* 서울 */
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
	}
	
	public void s3FileUpload(MultipartFile file, ReviewVO reviewVO) throws IllegalStateException, IOException {
		if(file != null && !file.getOriginalFilename().equals("")) {
			System.out.println(file.getOriginalFilename());

			File localFile = new File("/Users/rovert/Downloads/" + file.getOriginalFilename());
			file.transferTo(localFile);

			System.out.println(localFile.getName());
			PutObjectRequest obj = new PutObjectRequest(BUCKET_NAME, localFile.getName(), localFile);
			obj.setCannedAcl(CannedAccessControlList.PublicRead);
			String imageUrl = "https://reviewsaws.s3.ap-northeast-2.amazonaws.com/"+localFile.getName();
			s3.putObject(obj);
			System.out.println(imageUrl);
			reviewVO.setS3ImageUrl(imageUrl);

			reviewMapper.insertReview(reviewVO);
		} else {
			reviewMapper.insertReview(reviewVO);
		}
	}
}
