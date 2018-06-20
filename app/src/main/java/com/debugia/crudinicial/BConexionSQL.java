package com.debugia.crudinicial;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BConexionSQL {


    public static Connection getConnection() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        //192.168.1.111:1433/SQLSERVER2008R2
        //String ip = "192.168.1.111\\SQLSERVER2008R2:1433";
        String ip = "192.168.1.111:1433/SQLSERVER2008R2";
        //String ip = "148.102.21.175:1433/SQLSERVER2008R2";
        String classs = "net.sourceforge.jtds.jdbc.Driver";
        String db = "Bd_Consultoria_2015";
        String un = "sa";
        String password = "Solu123456";
        try {
            Class.forName(classs);
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {
            Log.e("ERROR SQLException", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR ClassNotFound", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR Exception", e.getMessage());
        }
        return connection;
    }


}