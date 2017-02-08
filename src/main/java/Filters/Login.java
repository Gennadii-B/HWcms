package main.java.Filters;

import main.java.entity.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 * Created by N on 09.02.2017.
 */
public class Login{

    public boolean valid(String login, String password, HttpSession session){
        Admin admin = new Admin();

        if(login != null &&
           password != null &&
           login.equals(admin.getLogin()) &&
           password.equals(admin.getPassword())){
                session.setAttribute("LOGIN_ADMIN", admin);
                return true;
        }
        return false;
    }
}
