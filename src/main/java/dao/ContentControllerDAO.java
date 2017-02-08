package main.java.dao;

import main.java.entity.EnContent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by N on 06.02.2017.
 */
public class ContentControllerDAO extends ConnectDS {

    private Connection con = null;
    private PreparedStatement ps = null;
    private Statement st = null;
    private ResultSet rs = null;
    private List<String> res;


    private String selectContent = "SELECT * FROM news_content " +
            "WHERE name_button = ";



    public void putContentToDB(EnContent ec, String menuButton){
        String insertContent = "INSERT INTO news_content " +
                "(pub_time, name_button, title, content) " +
                "VALUES (?, ?, ?, ?);";
        if(deleteContent(ec, menuButton))return;
        else if(menuButton.equals("добавить/изменить запись")) {
            try {
                con = connect();
                if (changeContent(ec)) return;

                ps = con.prepareStatement(insertContent);
                ps.setString(1, timeNow());
                ps.setString(2, ec.getButton());
                ps.setString(3, ec.getTitle());
                ps.setString(4, ec.getContent());
                ps.executeUpdate();

                System.out.println("[DB_INF] content load to db");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                disconnect(con, ps, st, rs);
            }
        }
    }

    public String loadListButtons(){
        String selectListButtons = "SELECT name_button FROM news_content ";
        String res = "";
        try {
            con = connect();
            st = con.createStatement();
            rs = st.executeQuery(selectListButtons);
            while(rs.next()){
                res += "<input class=\'button\' type=\"submit\" name=\"menuButton\" value=\""
                        + rs.getString("name_button") + "\"/><br/>\n";
            }
            System.out.println("[DB_INF] buttons load");
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            disconnect(con, ps, st, rs);
        }
        return res;
    }

    public EnContent loadContent(String menuButton){
        String selectContent = "SELECT * FROM news_content " +
                "WHERE name_button = ";

        EnContent ec = new EnContent();
        if(buttonController(ec, menuButton))return ec;

        try{
            con = connect();
            st = con.createStatement();
            rs = st.executeQuery(selectContent +"'"+ menuButton + "'");
            while(rs.next()){
                System.out.println("[DB_INF] load content:" + rs.getString("title"));
                ec.setTitle(rs.getString("title"));
                ec.setContent(rs.getString("content"));
                ec.setButton(rs.getString("name_button"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            disconnect(con,ps,st,rs);
        }
        return ec;
    }

    private boolean buttonController(EnContent ec, String menuButton){
//        boolean flag = false;
        if(menuButton == null || menuButton.equals("пустой бланк")) {
//            flag = true;
            ec.setButton("");
            ec.setTitle("");
            ec.setContent("");
            return true;
        }
//        } else if(ec.getButton() != null && menuButton.equals("удалить")){
////            flag = true;
//            System.out.println("action method delete");
//            System.out.println(ec.getButton());
//            deleteContent(ec, menuButton);
//            return true;
//        }
        return false;
    }


    private boolean changeContent(EnContent ec) throws SQLException{
        String select = "SELECT name_button FROM news_content";
        String update = "UPDATE news_content SET pub_time = ?, " +
                "title = ?, content = ? WHERE name_button = ?";

        st = con.createStatement();
        rs = st.executeQuery(select);
        while(rs.next()){
            if(ec.getButton().equals(rs.getString("name_button"))){
                ps = con.prepareStatement(update);
                ps.setString(1, timeNow());
                ps.setString(2, ec.getTitle());
                ps.setString(3, ec.getContent());
                ps.setString(4, ec.getButton());
                ps.executeUpdate();
                disconnect(con,ps,st,rs);
                System.out.println("[DB_INF] execute update");
                return true;
            }
        }
        return false;
    }

    private boolean deleteContent(EnContent ec, String menuButton) {
        String delete = "DELETE FROM news_content WHERE name_button = '"
                + ec.getButton() + "'";
        if (ec.getButton() != null && menuButton.equals("удалить")) {
            System.out.println("action method delete");
            System.out.println(ec.getButton());
            try {
                con = connect();
                st = con.createStatement();
                st.execute(delete);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                disconnect(con, ps, st, rs);
            }
        }
        return false;
    }
}
