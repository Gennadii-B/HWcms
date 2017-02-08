<%@ page import="main.java.Filters.Login" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
        <head>
            <title>ADMIN_login</title>
            <link rel="stylesheet" href="templates/styles.css"/>
        </head>
        <body>

        <%
            Login log = new Login();
            if(log.valid(request.getParameter("login"),
                      request.getParameter("password"),
                      request.getSession()))
                response.sendRedirect("admin");
        %>
                    <form id="login" action="" method="post">
                        <h2>AdminLogin</h2>
                        Login:<br/>
                        <input placeholder="admin"  type="text" name="login"/><br />
                        Password:<br/>
                        <input placeholder="admin" type="password" name="password"/><br />
                       <input type="submit" value=" OK " />
                    </form>

                <style>
                    #login {
                    padding: 0.5%;
                    border: 1px solid #0b1454;
                    width: 10%; /* Ширина слоя */
                    height: 15%;
                    margin: 20% auto; /* Выравнивание по центру */
                    background: #f0f0f0; /* Цвет фона левой колонки */
                    }
                </style>

        </body>
</html>



