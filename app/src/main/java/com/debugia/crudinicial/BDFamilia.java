package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDFamilia {
    public static String cfamilia;
    public static ArrayList<List<String>> myArray = new ArrayList<>();

    public static ArrayList getListFamilia(String cnom_familia) {
        ArrayList Familia = new ArrayList<String>();
        Connection connection = null;

        try {
            myArray.clear();
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Hfam_art where ccod_empresa=? and cnom_familia like ? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cnom_familia+"%");

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Familia.add(rs.getString("cnom_familia"));
                myArray.add(Arrays.asList(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("getListFamilia", e.getMessage());
        }
        return Familia;
    }

    public static ArrayList<List<String>> getListFamiliaArticulos() {
        ArrayList<List<String>> articulos = new ArrayList<>();
        Connection connection = null;
        try {
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Harticul where ccod_empresa=? and cfamilia=?  ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cfamilia);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                articulos.add(Arrays.asList(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("FamiliaArticulos", e.getMessage());
        }
        return articulos;
    }
    public static ArrayList<List<String>> getDescripcion(String Cod_Articulo) {
        ArrayList<List<String>> articulos = new ArrayList<>();
        Connection connection = null;
        try {
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Harticul where ccod_empresa=? and cfamilia=? and ccod_articulo=? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cfamilia);
            query.setString(3, Cod_Articulo);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                articulos.add(Arrays.asList(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("ArticulosDescripción", e.getMessage());
        }
        return articulos;
    }
}
