<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("utf-8");
%>

<!-- 액션태그 미사용시
	BoardDto dto = new BoardDto();
	request.getParameter("title");
	request.getParameter("writer");
	request.getParameter("content");
-->

<jsp:useBean id="dto" class="board.BoardDto"/>
<jsp:useBean id="dao" class="board.BoardDao"/>
<jsp:setProperty name="dto" property="*"/>

<%
	dao.insert(dto);
	// response.sendRedirect(request.getContextPath() + "/board/list.jsp");
%>

<c:redirect url="${pageContext.request.contextPath}/list.jsp"></c:redirect>
