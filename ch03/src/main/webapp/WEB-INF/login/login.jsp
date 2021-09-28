<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<style>
	
	
	.loginWrapper{
		margin-top: 50px;
		margin-left:auto;
		margin-right:auto;
		max-width: 400px;
		height: 250px;
		border-radius:5px;
		text-align: center;
		line-height:1.8;
	}
	
	#kakao-login-btn{
		margin-top:20px;
    	border-top: 1px solid #DDDDDD99;
	}
	.itemListWrapper{
		width: 960px;
		height: 480px;
		margin: 5px auto;
		text-align: center;
	}
	.itemList{
		float:left;
		width: 306px;
		height: 400px;
		margin: 5px;
		border-radius: 5px;
		border : 2px solid #DDDDDD;
	}
	.imageArea{
		width: 100%;
		height: 200px;
		background: #EEEEEE;
		overflow:hidden;
	}
	
	.imageArea > img{
		width: 100%;
		height: 100%;
	}
	.reviewArea{
		width: 100%;
		height: 180px;
		text-align: left;
		padding : 4px;
	}
	.reviewTitle{
		width: 100%;
		height: 20px;
		text-align: left;
		padding : 4px;
	}
	
	.reviewArea > textarea {
		resize: none;
		width: 98%;
		height: 168px;
		background:#AAAA0011;
		overflow:hidden;
    	border: 0px !important;
	}
	.slide-child{
		transform: translateY(50px);
        opacity: 0;
        transition: all 1s;
    }
    .is-visible{
		transform: translateY(0px);
        opacity: 1;
    }
    
   
</style>

<script type="text/javascript">

$(document).ready(function(){
	Kakao.init('b30526376249afa40b9d4f5c977a841f');
    // 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
      container: '#kakao-login-btn',
      success: function(authObj) {
        alert(JSON.stringify(authObj));
      },
      fail: function(err) {
         alert(JSON.stringify(err));
      }
    });
    
    var slideAelements = $('.slide-child')
    
    
    function animateSlideA() {
      slideAelements.each(function (i) {
          setTimeout(function () {
              slideAelements.eq(i).addClass('is-visible');
          }, 300 * (i + 1));
      });
    }
    animateSlideA() ;
    
});
</script>

<html>
<title>오프라인 리뷰 웹테스트</title>



<body>

<div class="loginWrapper">
	<div>
		<div class="slide-child">OffREV 는 Offline</div>
		<div class="slide-child">Review Flatform 의 약자로써</div>
		<div class="slide-child">오프라인 후기 정보들을</div>
		<div class="slide-child">모아모아 제공합니다.</div>
	</div>
	<div id="kakao-login-btn">
		
	</div>
	
	
</div>

<div class="itemListWrapper">
<div class="itemList slide-child">
   <div class="imageArea"><img src="img/img1.jpg"/></div>
   <div class="reviewArea">
   	  <div class="reviewTitle" ><c:out value="${ reviewList[0].title }" /> </div>
      <textarea readonly><c:out value="${ reviewList[0].content }" /></textarea>
   </div>
</div>
<div class="itemList slide-child">
   <div class="imageArea"><img src="img/img2.jpg"/></div>
   <div class="reviewArea">
  	  <div class="reviewTitle" ><c:out value="${ reviewList[1].title }" /> </div>
      <textarea readonly><c:out value="${reviewList[1].content}" /></textarea>

   </div>
</div>
<div class="itemList slide-child">
   <div class="imageArea"><img src="img/img3.jpg"/></div>
   <div class="reviewArea" >
      <div class="reviewTitle" ><c:out value="${ reviewList[2].title }" /> </div>
      <textarea readonly><c:out value="${reviewList[2].content}" /></textarea>
   </div>
</div> 

</div>



</body>
</html>
