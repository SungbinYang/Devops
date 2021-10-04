package me.sungbin.review.login.controller;

import lombok.RequiredArgsConstructor;
import me.sungbin.review.login.service.AwsS3Service;
import me.sungbin.review.login.service.ReviewService;
import me.sungbin.review.login.service.UserService;
import me.sungbin.review.login.vo.ReviewVO;
import me.sungbin.review.login.vo.UserVO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	//private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final ReviewService reviewService;

    private final UserService userService;

    private final AwsS3Service awsService;
	
	@PostMapping("/kakaoLogin")
	@ResponseBody
	public int kakaoLogin(UserVO user) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	HttpServletRequest request = attr.getRequest();
    	HttpServletResponse response = attr.getResponse();
    	
		System.out.println("TEST");
		System.out.println(user.getEmail());
		System.out.println(user.getId());
		System.out.println(user.getNickname());
		
		List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

		UsernamePasswordAuthenticationToken userAuthenication = new UsernamePasswordAuthenticationToken(user.getId(), "pass", roles);
		
		userAuthenication.setDetails(user);
		userService.updateUserJoin(user);
		
		SecurityContextHolder.getContext().setAuthentication(userAuthenication);
		HttpSession session= request.getSession();
		session.setAttribute("user", user);
		return 1;
	}
	
	
	@PostMapping("/fileUpload")
	@ResponseBody
	public int fileUpload(@RequestParam("mediaFile") MultipartFile file,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			Model model) throws IllegalStateException, IOException {

    	System.out.println("fileUpload11");
    	System.out.println("title::");
    	System.out.println(title);
    	System.out.println("content::");
    	//System.out.println(content);
    	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	HttpServletRequest request = attr.getRequest();
    	HttpServletResponse response = attr.getResponse();
    	
    	HttpSession session= request.getSession();
    	UserVO userVO = (UserVO)session.getAttribute("user");
    	
    	ReviewVO reviewVO = new ReviewVO();
    	reviewVO.setTitle(title);
    	reviewVO.setContent(content);
    	reviewVO.setUserId(userVO.getId());
    	
		awsService.s3FileUpload(file, reviewVO);
		
		return 1;	
	}

    @RequestMapping(value="/login")
    public String login(Model model, String error, String logout) {

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    	model.addAttribute("reviewList", reviewService.getReviewList());
    	
    	if(authentication != null) {
    		System.out.println("LOGIN");

    		return "login/login";
	    	//if(!authentication.getPrincipal().equals("anonymousUser"))
	    	//	return "redirect:/";
    	}

        return "login/login";
    }
}
