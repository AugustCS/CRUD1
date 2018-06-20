package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDSubFamilia {
    public static String cSubfamilia;
    public static ArrayList<List<String>> myArray = new ArrayList<>();

    public static ArrayList getListSubFamilia(String cnom_familia) {
        ArrayList Familia = new ArrayList<String>();
        Connection connection = null;

        try {
            myArray.clear();
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Hsubfamilia_art where ccod_empresa=? and cnom_subfamilia like ? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cnom_familia+"%");

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Familia.add(rs.getString("cnom_subfamilia"));
                myArray.add(Arrays.asList(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("getListSubFamilia", e.getMessage());
        }
        return Familia;
    }
    public static ArrayList<List<String>> getListSubFamiliaArticulos() {
        ArrayList<List<String>> articulos = new ArrayList<>();
        Connection connection = null;
        try {
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Harticul where ccod_empresa=? and ccod_subfamilia=?  ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cSubfamilia);

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
            Log.d("SubamiliaArticulos", e.getMessage());
        }
        return articulos;
    }


    public static ArrayList<List<String>> getDescripcion(String Cod_Articulo) {
        ArrayList<List<String>> articulos = new ArrayList<>();
        Connection connection = null;
        try {
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Harticul where ccod_empresa=? and ccod_subfamilia=? and ccod_articulo=? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cSubfamilia);
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
