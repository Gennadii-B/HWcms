package main.java.dao;

import main.java.entity.EnContent;

import java.sql.*;

/**
 * Created by N on 10.02.2017.
 */
public class ContentServiceDAO extends ConnectDS {
    private static Connection con = null;
    private static Statement st = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;


    static EnContent getContent(String id) {
        String selectContent = "SELECT * FROM news_content " +
                "WHERE id = ";

        EnContent ec = new EnContent();
//        if(buttonController(ec, menuButton))return ec;
        EnContent enContent = new EnContent();
        try {
            con = connect();
            st = con.createStatement();
            rs = st.executeQuery(selectContent + "'" + id + "'");
            while(rs.next()) {
                System.out.println("[DB_INF] load content: " + rs.getString("title"));
                enContent.setTitle(rs.getString("title"));
                enContent.setContent(rs.getString("content"));
                enContent.setButton(rs.getString("name_button"));
                return enContent;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect(con, ps, st, rs);
        }
        return null;
    }
}
