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
		margin: 5px auto;
		text-align: center;
	}
	.itemList{
	    width: 300px;
	    display: inline-block;
	    height: 400px;
	    margin: 5px;
	    border-radius: 5px;
	    border: 2px solid #DDDDDD;
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
    .write-btn{
    	width: 100px;
    	height: 28px;
    	margin: 0px auto;
    	background: linear-gradient(to right, #C02425, #F0CB35);
    	color: white;
    	border-radius: 5px;
    	opacity: 0.8;
    	cursor: pointer;
    	padding-top: 5px;
    	margin-top: 20px;
    	border-top: 1px solid #DDDDDD99;
    } 
    .write-btn:hover {
    	opacity: 1;
    }
   
    .cover-form{
	    width: 300px;
	    height: 450px;
	    background: white;
	    position: fixed;
	    z-index: 10;
	    border-radius: 10px;
	    padding-top: 10px;
    }
    .form-title{
    	font-size: 25px;
    	font-weight: bold;
    	width: 100%;
    	text-align: center;
    }
    .form-desc{
    	font-size: 14px;
    	text-align: left;
    	padding: 20px;
    }
    
    .input-title{
    	width: 150px;
    	height: 40px;
    	text-align: left;
    	font-size: 14px;
    }
    .input-content{
    	width: 250px;
    	height: 200px;
    	font-size: 14px;
    }
</style>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#cover-form").hide();
	<c:if test="${ user == null }" >
	$("#write-btn").hide();
	
	Kakao.init('8c12ea928faf95441620a1c3eda08d70');
    // ????????? ????????? ????????? ???????????????.
    Kakao.Auth.createLoginButton({
      container: '#kakao-login-btn',
      success: function(authObj) {
        //alert(JSON.stringify(authObj));
       Kakao.API.request({url:'/v2/user/me',
    	   success:function (res){
    		   var id = res.id;
    		   var email = (res.kaccount_email ? res.kaccount_email : '');
    		   var nickname = (res.properties && res.properties.nickname ? res.properties.nickname : '');

    		   nickname = '??????';
    		   
    		   $("#logininfo").text(nickname);
    		   $.post("/kakaoLogin",
	   			   {id:id, email : email, nickname : nickname}
	   			 	, function (data){
	   			 		if(data == 1){
	   			 			alert("???????????? ?????? ???????????????.");
	   			 			$("#kakao-login-btn").hide();
		   			 		$("#write-btn").show();
	   			 		}
	   			 	}
    		   )
    	   },
    	   fail:function (error){
    		   
    	   }})
       
      },
      fail: function(err) {
         alert(JSON.stringify(err));
      }
    });
    </c:if>
    var slideAelements = $('.slide-child')
    
    
    function animateSlideA() {
      slideAelements.each(function (i) {
          setTimeout(function () {
              slideAelements.eq(i).addClass('is-visible');
          }, 300 * (i + 1));
      });
    }
    animateSlideA() ;
    
    
    $("#write-btn").click(function (){
    	$("#cover-form").show();
    });

	$("#cancelBtn").click(function (){
    	$("#cover-form").hide();
    });
    
    
    $("#btnSubmit").click(function (event) {
        //preventDefault ??? submit??? ??????
        event.preventDefault();
 
        var form = $('#fileUploadForm')[0];
 
        var data = new FormData(form);
 
        $("#btnSubmit").prop("disabled", true);
 
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/fileUpload",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                alert("complete");
                $("#btnSubmit").prop("disabled", false);
                $("#cover-form").hide();
                location.reload(true);
            },
            error: function (e) {
                console.log("ERROR : ", e);
                $("#btnSubmit").prop("disabled", false);
                alert("fail");
                $("#cover-form").hide();
            }
        });
 
    });
    
});
</script>

<html>
<title>???????????? ?????? ????????????</title>

<body>

<div class="loginWrapper">
	<div>
		<div class="slide-child">OffREV ??? Offline</div>
		<div class="slide-child">Review Flatform ??? ????????????</div>
		<div class="slide-child">???????????? ?????? ????????????</div>
		<div class="slide-child">???????????? ???????????????.</div>
	</div>
	<c:if test="${ user == null }" >
	<div id="kakao-login-btn">
		
	</div>
	</c:if>
	<div id="cover-form" class="cover-form">
		<div class="form-title">?????? ??????</div>
		<div class="form-desc">???????????? ?????? ????????? ??????????????????.</div>
		<form method="POST" action="/fileUpload" enctype="multipart/form-data" id="fileUploadForm">
			?????? <input type=text name="title" class="input-title" />
			<br/>??????<br/>
			<textarea name="content" class="input-content"></textarea>
		
		    <input type=file name="mediaFile" > <br/>
		    <input type="submit" value="????????????" id="btnSubmit"/>
		    <input type="button" value="????????????" id="cancelBtn"/>
		</form>
		
	</div>
	<div id="write-btn" class="write-btn">
		?????????
	</div>
	
</div>

<div class="itemListWrapper">
	<c:forEach var="item" items="${reviewList}" varStatus="status">
		<div class="itemList slide-child">
		   <div class="imageArea"><img src="<c:out value="${ item.s3ImageUrl }" />"/></div>
		   <div class="reviewArea">
			  <div class="reviewTitle" ><c:out value="${ item.title }" /> </div>
			  <textarea readonly><c:out value="${ item.content }" /></textarea>
		   </div>
		</div>
	</c:forEach>
</div>

</body>
</html>

