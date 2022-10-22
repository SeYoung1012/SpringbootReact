<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
    <title>게시판 목록보기</title>
</head>
 
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(function(){
		
			
		//perPageNum select 박스 설정
		setPerPageNumSelect();
		
	
		//prev 버튼 활성화, 비활성화 처리
		var canPrev = '${pageMaker.prev}';
		if(canPrev !== 'true'){
			$('#page-prev').addClass('disabled');
		}
		
		//next 버튼 활성화, 비활성화 처리
		var canNext = '${pageMaker.next}';
		if(canNext !== 'true'){
			$('#page-next').addClass('disabled');
		}
		
		//현재 페이지 파란색으로 활성화
		var thisPage = '${pageMaker.cri.page}';
		//매번 refresh 되므로 다른 페이지 removeClass 할 필요는 없음->Ajax 이용시엔 해야함
		$('#page'+thisPage).addClass('active');

		
		
		function setPerPageNumSelect(){
		var perPageNum = "${pageMaker.cri.perPageNum}";
		var $perPageSel = $('#perPageSel');
		var thisPage = '${pageMaker.cri.page}';
		$perPageSel.val(perPageNum).prop("selected",true);
		//PerPageNum가 바뀌면 링크 이동
		$perPageSel.on('change',function(){
			//pageMarker.makeQuery 사용 못하는 이유: makeQuery는 page만을 매개변수로 받기에 변경된 perPageNum을 반영못함
			window.location.href = "listForm?page="+thisPage+"&perPageNum="+$perPageSel.val();
		})
				
	
	}
	
	})
</script>

<body>


    <h2>게시판 글목록</h2>
    
   <div class="row">
	<div class="col-md-11"></div>	
	<div class="col-md-1 text-right">
		<!-- perPageNum의 값을 정하는 select 박스 -->
		<select class="form-control" id="perPageSel">
	  		<option value="10">10</option>
	  		<option value="15">15</option>
	  		<option value="20">20</option>
		</select>
	</div>
	</div>
    <table width="500" cellpadding="0" cellspacing="0" border="1" >
        <thead>
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>제목</th>
            <th>날짜</th>
        </tr>
        </thead>
        <tbody style="text-align: center">
       
        <c:forEach var="dto" items="${list }">
       		<tr>
	            <td>${dto.board_idx}</td>
	            <td>${dto.board_name} </td>
	            <td>
	            	<a href="contentForm?board_idx=${dto.board_idx }">${dto.board_ttl }</a>
	            </td>
	            <td>
	            	<c:set var="dateVar" value="${dto.board_date }"/>
	            	<fmt:formatDate value="${dateVar }" pattern="yyyy-MM-dd"/>
	            </td>
        	</tr>        
        </c:forEach>       
        <tr>
            <td style="text-align: left" colspan="5"><a href="writeForm">글작성</a></td>
        </tr>
        </tbody>
    </table>
    <!-- 페이지 번호 -->	
<div class="text-center">
		<ul class="pagination">
			<!-- 이전 페이지: 시작 페이지가 1이 아닌 경우 보인다-->
			<c:if test="${pageMaker.prev }"> 
			<a href="listForm${pageMaker.makeQuery(pageMaker.startPage-1)}" aria-label="Prev">Previous</a>
   			 </c:if>
			
			<!-- 페이지 번호 (시작 페이지 번호부터 끝 페이지 번호까지) -->
			 <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
			    <li id="page${idx}">
				    <a href="listForm${pageMaker.makeQuery(idx)}">${idx }</a></li>
   			 </c:forEach>
			
			<!-- next 버튼 -->
			<c:if test="${pageMaker.next }"> 
			<!-- 다음 페이지: 끝 페이지가 마지막 페이지가 아닌 경우 보인다 -->
   				<li class="paginate_button next"> <a href="listForm${pageMaker.makeQuery(pageMaker.endPage + 1)}" aria-label="Next">Next</a></li>
   			 </c:if>
			
		</ul>
</div>


         
</body>
</html>