package com.debugia.crudinicial;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FamiliaBD {

    public static ArrayList getListFamilia() {
        ArrayList Familia = new ArrayList<String>();
        Connection connection = null;

        try {
            connection=ConexionSQL.getConnection();
            String stsql = "select * from Hfam_art where ccod_empresa=?";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, UsuarioBD.CodEmp);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Familia.add(rs.getString("cnom_familia"));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("getListFamilia", e.getMessage());
        }
        return Familia;
    }

    public static ArrayList getListFamiliaSearchView(String cnom_familia) {
        ArrayList Familia = new ArrayList<String>();
        Connection connection = null;

        try {
            connection=ConexionSQL.getConnection();

            String stsql = "select * from Hfam_art where ccod_empresa=? and cnom_familia like ? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, UsuarioBD.CodEmp);
            query.setString(2, cnom_familia+"%");

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Familia.add(rs.getString("cnom_familia"));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("getListFamilia", e.getMessage());
        }
        return Familia;
    }

    public ArrayList getListFamiliaArticulos() {
        ArrayList Familia = new ArrayList<String>();
        Connection connection = null;
        try {
            connection=ConexionSQL.getConnection();

            String stsql = "select * from Hfam_art where ccod_empresa=? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, UsuarioBD.CodEmp);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Familia.add(rs.getString("cnom_familia"));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("getListFamilia", e.getMessage());
        }
        return Familia;
    }
}
