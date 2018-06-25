package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BDUsuario {
    public static String CodEmp = "prueba";
    public  static String CodUsu="01";
    public static Boolean getLogin(String RUC, String Usuario, String Clave) {
        ArrayList Familia = new ArrayList<String>();
        Connection connection = null;
        String ClaveEncriptada = null;
        try {
            ClaveEncriptada=getClaveEncriptada(Clave);
            connection= BConexionSQL.getConnection();
            String stsql = "select * from sv_list_user_login where ruc=? and coduser=? and pass=? and state='A'";
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, RUC);
            query.setString(2, Usuario);
            query.setString(3, ClaveEncriptada);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Familia.add(rs.getString(1));
                Familia.add(rs.getString(2));
                Familia.add(rs.getString(3));
                Familia.add(rs.getString(4));
                Familia.add(rs.getString(5));
                Familia.add(rs.getString(7));
            }
            CodEmp = Familia.get(0).toString();
            connection.close();
            if (Familia.size() > 0)
                return true;
        } catch (Exception e) {
            Log.d("getLogin", e.getMessage());
        }
        return false;
    }

    public static String getClaveEncriptada(String clave) {
        String strexpresion = "";
        int li_longitud, li_ascii, li_ascii_new;
        String ls_letra;
        try {
            li_longitud = clave.length();
            for (int i = 0; i < li_longitud; i++) {
                ls_letra = clave.substring(i, i+1);
                li_ascii = ls_letra.codePointAt(0);
                li_ascii_new = li_ascii + ((li_longitud + 1 - (i+1)) * (li_longitud + 1 - (i+1))) - ((li_longitud + 2 - (i+1)) * (li_longitud + 2 - (i+1)));
                strexpresion = strexpresion + String.valueOf((char)li_ascii_new);
            }
        } catch (Exception e) {
            Log.d("getClaveEncriptada", "Fallo en algo: "+e.getMessage());
        }
        return strexpresion;
    }

    public static void getExitLogin() {
        CodEmp=null;
    }
}
