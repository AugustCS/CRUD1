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

public class ConexionSQL {
    //192.168.1.111:1433/SQLSERVER2008R2
    //String ip = "192.168.1.111\\SQLSERVER2008R2:1433";
    String ip = "192.168.1.111:1433/SQLSERVER2008R2";
    //String ip = "148.102.21.175:1433/SQLSERVER2008R2";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "Bd_Consultoria_2015";
    String un = "sa";
    String password = "Solu123456";

    public Connection ConnectionHelper() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName(classs);
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
            Log.d("ConnectionHelper", "Conexion EXITOSA");
        } catch (SQLException se) {
            Log.e("ERROR SQLException", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR ClassNotFound", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR Exception", e.getMessage());
        }
        return connection;
    }


    public ArrayList getListFamilia() {
        ArrayList Familia = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName(classs);
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);


            String stsql = "select * from Hfam_art";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(stsql);
            while (rs.next()) {
                Familia.add(rs.getString(3));
            }
            connection.close();
            Log.d("getListFamilia", "exito");

        } catch (Exception e) {
            Log.d("getListFamilia", e.getMessage());
        }
        return Familia;
    }

    public ArrayList getListFamiliaProductos() {
        ArrayList Familia = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName(classs);
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);


            String stsql = "select * from Harticul";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(stsql);
            while (rs.next()) {
                Familia.add(rs.getString("cnom_articulo"));
                Familia.add(rs.getString("cnom_articulo"));
            }
            connection.close();
            Log.d("getListFamilia", "exito");

        } catch (Exception e) {
            Log.d("getListFamilia", e.getMessage());
        }
        return Familia;
    }

    public Boolean getLogin(String RUC, String Usuario, String Clave) {
        ArrayList Familia = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        String ClaveEncriptada = null;
        try {
            ClaveEncriptada=getClaveEncriptada(Clave);
            Class.forName(classs);
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);

            String stsql = "select * from sv_list_user_login where ruc=? and coduser=? and pass=? and state='A'";
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, RUC);
            query.setString(2, Usuario);
            query.setString(3, ClaveEncriptada);
            ;

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Familia.add(rs.getString(1));
            }
            connection.close();
            if (Familia.size() > 0)
                return true;
            Log.d("getListFamilia", "exito");

        } catch (Exception e) {
            Log.d("getListFamilia", e.getMessage());
        }
        return false;
    }

    public String getClaveEncriptada(String clave) {
        String strexpresion = "";
        int li_longitud, li_ascii, li_ascii_new;
        String ls_letra;
        try {
            li_longitud = clave.length();
            for (int i = 0; i < li_longitud; i++) {
                ls_letra = clave.substring(i, i+1);
                Log.d("ls_letra", ls_letra);

                li_ascii = ls_letra.codePointAt(0);
                Log.d("li_ascii", li_ascii+"");

                li_ascii_new = li_ascii + ((li_longitud + 1 - (i+1)) * (li_longitud + 1 - (i+1))) - ((li_longitud + 2 - (i+1)) * (li_longitud + 2 - (i+1)));
                Log.d("li_ascii_new", li_ascii_new+"");

                strexpresion = strexpresion + String.valueOf((char)li_ascii_new);
            }
        } catch (Exception e) {
            Log.d("getClaveEncriptada", "Fallo en algo: "+e.getMessage());
        }
        Log.d("strexpresionFinal", "" + strexpresion);
        return strexpresion;
    }
    //1234=&),/
    // #&),/25
    // le sale . :46
    // me sale , :44
}