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
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        return connection;
    }

    /*public boolean ObtenerDatos() {
        String datosConsultado = "";
        try {
            //Se obtiene la conexión
            Connection connect = ConexionSQL.ConnectionHelper();
            //Se genera la consulta
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("select campo1,campo2 from tabla");
            while (rs.next()) {
                //Se extraen los datos
                datosConsultado = rs.getString("campo1");
            }
            //Se cierra la conexión
            connect.close();
            //Mostramos los datos obtenidos
            Toast.makeText(getApplicationContext(),
                    datosConsultado, Toast.LENGTH_SHORT).show();
            return  true;
        } catch (SQLException e) {
            //Mostramos el error en caso de no conectarse
            Toast.makeText(getApplicationContext(),
                    e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }*/
}