<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*, java.text.*, main.java.*, java.sql.*" %>
<%@ page import="main.java.dao.ContentControllerDAO" %>
<%@ page import="main.java.entity.EnContent" %>
<html>
    <head>
        <title>ADMIN_PAGE</title>
        <link rel="stylesheet" href="styles.css"/>
    </head>
    <body>

        <%
        ContentControllerDAO cc = new ContentControllerDAO();
        request.setCharacterEncoding("UTF-8");
        EnContent ecOut = new EnContent();
        ecOut.setButton(request.getParameter("name_button"));
        ecOut.setTitle(request.getParameter("title"));
        ecOut.setContent(request.getParameter("content"));
        System.out.println("[PAGE_INF]" + ecOut.getButton() + " " + ecOut.getTitle());
        if(ecOut.getContent() != null && ecOut.getButton() != null && ecOut.getTitle() != null)
           cc.putContentToDB(ecOut, request.getParameter("menuButton"));

        EnContent ecIn = cc.loadContent(request.getParameter("menuButton"));

        %>
        <form action="news.jsp">
            <button type="submit">page</button>
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
            <h2>create article</h2>
            <form action="">
                <div>Название кнопки (идентификатор)</div>
                <input maxlength="26" size="27" type="text" name="name_button" value="<%= ecIn.getButton() %>" required><span></span><br />
                <div>Заголовок</div>
                <input maxlength="100" size="70" type="text" name="title" value="<%= ecIn.getTitle() %>" required><span></span><br />
                <div>содержание</div>
                <textarea COLS="72" ROWS="23" WRAP="hard" maxlength="850" type="text" name="content" placeholder="введите текст" required><%= ecIn.getContent() %></textarea><br/>
                <input type="submit" name="menuButton" value="добавить/изменить запись"/>
                <input type="submit" name="menuButton" value="удалить"/>
            </form>
            <form action="">
                <input type="submit" name="menuButton" value="пустой бланк"/>
            </form>
        </div>
        <jsp:include page="footer.jsp"/>
        </div>
    </body>
</html>
