<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, java.text.*, main.java.*, java.sql.*" %>
<%@ page import="main.java.dao.ContentControllerDAO" %>
<%@ page import="main.java.entity.EnContent" %>
<html>
<head>
    <title>News</title>
    <link rel="stylesheet" href="styles.css"/>
</head>
<body>

<%
    ContentControllerDAO cc = new ContentControllerDAO();
    request.setCharacterEncoding("UTF-8");
    EnContent ec = cc.loadContent(request.getParameter("menuButton"));

%>
<form action="admin.jsp">
    <button type="submit">admin</button>
</form>
    <div id="container">
        <jsp:include page="header.jsp"/>
        <div id="sidebar">
            <a href="news.jsp"><h3>Menu</h3></a>
            <form action="">
                <%= cc.loadListButtons() %>
            </form>
        </div>
             <div id="content">
                 <h2><%= ec.getTitle() %></h2><br/>
                 <%= ec.getContent() %>
             </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
