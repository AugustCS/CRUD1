package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDConcepto {
    public static Integer concepto;
    public static String cConcepto;
    public static ArrayList<List<String>> myArray = new ArrayList<>();
    public static ArrayList getListConceptos() {
        ArrayList arrayList = new ArrayList<String>();
        Connection connection = null;

        try {
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Htableconceptos_erp where ccod_empresa=?";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                arrayList.add(rs.getString(1));
                arrayList.add(rs.getString(2));
                arrayList.add(rs.getString(3));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("getListConceptos", e.getMessage());
        }
        return arrayList;
    }


    public static ArrayList getListConcepto(String cnom_familia) {
        ArrayList Familia = new ArrayList<String>();
        Connection connection = null;

        try {
            myArray.clear();
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Erp_concepto"+concepto+" where erp_codemp=? and erp_nomcon like ? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cnom_familia+"%");

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Familia.add(rs.getString("erp_nomcon"));
                myArray.add(Arrays.asList(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("getListConcepto", e.getMessage());
        }
        return Familia;
    }

    public static ArrayList<List<String>> getListConceptoArticulos() {
        ArrayList<List<String>> articulos = new ArrayList<>();
        Connection connection = null;
        try {
            connection= BConexionSQL.getConnection();
            String stsql=null;
            switch (concepto){
                case 1:
                    stsql = "select * from Harticul where ccod_empresa=? and codmarca = ? ";
                    break;
                case 2:
                    stsql = "select * from Harticul where ccod_empresa=? and modelo = ? ";
                    break;
                case 3:
                    stsql = "select * from Harticul where ccod_empresa=? and color = ? ";
                    break;
                case 4:
                    stsql = "select * from Harticul where ccod_empresa=? and tratamiento = ? ";
                    break;
                case 5:
                    stsql = "select * from Harticul where ccod_empresa=? and fuelle = ? ";
                    break;
                case 6:
                    stsql = "select * from Harticul where ccod_empresa=? and azas = ? ";
                    break;
                case 7:
                    stsql = "select * from Harticul where ccod_empresa=? and solapa = ? ";
                    break;
            }

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cConcepto);

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
            Log.d("ConceptoArticulos", e.getMessage());
        }
        return articulos;
    }

    public static ArrayList<List<String>> getDescripcion(String Cod_Articulo) {
        ArrayList<List<String>> articulos = new ArrayList<>();
        Connection connection = null;
        try {
            connection= BConexionSQL.getConnection();

            String stsql=null;
            switch (concepto){
                case 1:
                    stsql = "select * from Harticul where ccod_empresa=? and codmarca = ? and ccod_articulo=? ";
                    break;
                case 2:
                    stsql = "select * from Harticul where ccod_empresa=? and modelo = ? and ccod_articulo=? ";
                    break;
                case 3:
                    stsql = "select * from Harticul where ccod_empresa=? and color = ? and ccod_articulo=? ";
                    break;
                case 4:
                    stsql = "select * from Harticul where ccod_empresa=? and tratamiento = ? and ccod_articulo=? ";
                    break;
                case 5:
                    stsql = "select * from Harticul where ccod_empresa=? and fuelle = ? and ccod_articulo=? ";
                    break;
                case 6:
                    stsql = "select * from Harticul where ccod_empresa=? and azas = ? and ccod_articulo=? ";
                    break;
                case 7:
                    stsql = "select * from Harticul where ccod_empresa=? and solapa = ? and ccod_articulo=? ";
                    break;
            }

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cConcepto);
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