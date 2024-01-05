<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="dao" class="board.BoardDao"/> <!-- BoardDao dao = new BoardDao(); 와 동일 -->
<%
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDto dto = dao.selectOne(num);
	pageContext.setAttribute("dto", dto);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용</title>
</head>
<body>
<h3>글 정보</h3>
<p>번호 : ${dto.num}</p>
<p>제목 : ${dto.title}</p>
<p>작성자 : ${dto.writer}</p>
<p>내용 : ${dto.content}</p>
<p>등록일자 : ${dto.regdate}</p>
<p>조회수 : ${dto.cnt}</p>

<a href="<c:url value="/board/editForm.jsp?num=${dto.num}"/>"><button>수정</button></a>
<a href="<c:url value="/board/deleteForm.jsp?num=${dto.num}"/>"><button>삭제</button></a>
</body>
</html>