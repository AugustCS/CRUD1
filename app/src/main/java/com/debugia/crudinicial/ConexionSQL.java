package com.debugia.crudinicial;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

public class ConexionSQL {

    public static Connection ConnectionHelper() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://192.168.1.111;port=1433;databaseName=Bd_Consultoria_2015;users=sa;password=Solu123456;";
            connection = DriverManager.getConnection(ConnectionURL);
            Log.d("ConnectionHelper","Conexion EXITOSA");
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        return connection;
    }


    public ArrayList getListFamilia() {
        ArrayList Familia = new ArrayList<String>();
        try{

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection conn = DriverManager
                    .getConnection(
                            "192.168.1.111\\SQLSERVER2008R2\\Bd_Consultoria_2015",
                            "sa", "Solu123456");

            String stsql = "select * from Hfam_art";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(stsql);
            while(rs.next()){
                Familia.add(rs.getString(2));
            }
            conn.close();
            Log.d("getListFamilia","exito");

        }catch (Exception e){
            Log.d("getListFamilia",e.getMessage());
        }
        return Familia;
    }

}