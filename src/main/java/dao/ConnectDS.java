package main.java.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by N on 06.02.2017.
 */
public class ConnectDS {

    protected static String timeNow(){
        java.util.Date curTime = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String present = sdf.format(curTime);
        return present;
    }

    protected static Connection connect(){
        try {
            DataSource ds = null;
            Context context;
            Connection con = null;
            System.out.println("[DB_INF] connection");
            context = new InitialContext();
            ds = (DataSource) context.lookup("PostgresDS");
            con = ds.getConnection();
            System.out.println("[DB_INF] connect [ON]");
            return con;
        }catch (NamingException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static void disconnect(Connection con, PreparedStatement ps, Statement st, ResultSet rs){
        try {
            if(rs != null)rs.close();
            if(st != null)st.close();
            if(ps != null) ps.close();
            if(con != null)con.close();
            System.out.println("[DB_INF] connect [OFF]");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
