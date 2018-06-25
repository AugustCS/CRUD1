package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDCarritoPedidos {

    public static ArrayList<List<String>> arraylistPedidos = new ArrayList<>();

    public static void AgregarProducto(String Codigo, String Cantidad) {
        Boolean agregar = true;
        for (int i = 0; i < arraylistPedidos.size(); i++) {

            if (arraylistPedidos.get(i).get(0).equals(Codigo)) {
                Integer CantidadNueva = Integer.parseInt(Cantidad) + Integer.parseInt(arraylistPedidos.get(i).get(1));
                arraylistPedidos.set(i, Arrays.asList(
                        Codigo,
                        CantidadNueva.toString()
                ));
                agregar = false;
                break;
            }
            agregar = true;
        }
        if (agregar) {
            arraylistPedidos.add(Arrays.asList(
                    Codigo,
                    Cantidad
                    )
            );
        }
    }

    public static ArrayList<List<String>> getArraylistPedidos(ArrayList<List<String>> arrayCodArticulos ) {

        ArrayList<List<String>> arrayArticulos = new ArrayList<>();

        Connection connection = null;
        try {
            connection = BConexionSQL.getConnection();
            String CodigosArticulos = "";

            for (int i = 0; i < arrayCodArticulos.size(); i++) {
                CodigosArticulos += " '" + arrayCodArticulos.get(i).get(0) + "' ";
                if (i + 1 < arrayCodArticulos.size()) {
                    CodigosArticulos += ", ";
                }
            }

            String sql = "select * from Harticul where ccod_empresa='" + BDUsuario.CodEmp + "' and ccod_articulo in (" + CodigosArticulos + ") ";

            Log.d("sql", sql);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                arrayArticulos.add(Arrays.asList(
                        rs.getString("ccod_articulo"),
                        rs.getString("cnom_articulo"),
                        rs.getString("cunidad"),
                        rs.getString("cmonedav"),
                        rs.getString(2),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("BDCarritoPedidos", "- getArraylistPedidos: " + e.getMessage());
        }
        return arrayArticulos;
    }

    public static ArrayList<List<String>> getListCodProductos() {
        ArrayList<List<String>> arrayArticulos = new ArrayList<>();
        Connection connection;

        try {
            connection = BConexionSQL.getConnection();
            String stsql = "select * from HlistDeseo where ccod_empresa=? and erp_coduser = ? ";
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, BDUsuario.CodUsu);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                arrayArticulos.add(Arrays.asList(
                        rs.getString("ccod_articulo"),
                        rs.getString("nitem")));
            }
            connection.close();
        } catch (Exception e) {
            Log.d("BDFamilia", "- GetCantidad: " + e.getMessage());
        }
        return arrayArticulos;
    }


    public static boolean GuardarProductoDeseado(String nitem, String ccod_articulo) {

        Connection connection;
        try {
            String Cantidad;
            String stsql;
            Integer CantidadAnterior = Integer.parseInt(GetCantidad(ccod_articulo));
            if (CantidadAnterior > 0) {
                stsql = "update HlistDeseo set nitem=? where ccod_empresa=? and ccod_articulo=? and erp_coduser=? ";
                Cantidad = String.valueOf(CantidadAnterior + Integer.parseInt(nitem));
            } else {
                stsql = "insert into HlistDeseo (nitem,ccod_empresa, ccod_articulo,erp_coduser) values (?,?,?,?)";
                Cantidad = nitem;
            }
            connection = BConexionSQL.getConnection();
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, Cantidad);
            query.setString(2, BDUsuario.CodEmp);
            query.setString(3, ccod_articulo);
            query.setString(4, BDUsuario.CodUsu);
            query.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            Log.d("BDCarritoPedidos", "- GuardarProductoDeseado: " + e.getMessage());
        }
        return false;
    }

    public static String GetCantidad(String ccod_articulo) {
        List<String> listaNombres = new ArrayList<String>();
        Connection connection;

        try {
            connection = BConexionSQL.getConnection();
            String stsql = "select * from HlistDeseo where ccod_empresa=? and ccod_articulo = ? ";
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, ccod_articulo);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                listaNombres.add(rs.getString("nitem"));
            }
            connection.close();
            return listaNombres.get(0);

        } catch (Exception e) {
            Log.d("BDFamilia", "- GetCantidad: " + e.getMessage());
            return "0";
        }
    }

    public static Boolean EnviarPedidos() {
        Connection connection = null;
        try {
            String sql = "delete from HListDeseo where ccod_empresa=? and erp_coduser=?";
            connection = BConexionSQL.getConnection();
            PreparedStatement query = connection.prepareStatement(sql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, BDUsuario.CodUsu);

            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.close();
            return true;

            //
            //delete from HListDeseo where ccod_empresa='01'  and erp_coduser='01'
        /*
        ArrayList<List<String>> arrayArticulos = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = "insert into HlistDeseo (ccod_empresa, nitem,ccod_articulo) values";
            connection = BConexionSQL.getConnection();
            String CodigosArticulos = "";


            for (int i = 0; i < arraylistPedidos.size(); i++) {
                sql += " (";
                sql += "'" + BDUsuario.CodEmp + "', ";
                sql += "'" + arraylistPedidos.get(i).get(0) + "', ";
                sql += "'" + arraylistPedidos.get(i).get(1) + "'";
                sql += ") ";
                if (i + 1 < arraylistPedidos.size()) {
                    CodigosArticulos += ", ";
                }

            }
            Log.d("Insert", sql);
/*
            String sql= "select * from Harticul where ccod_empresa='"+BDUsuario.CodEmp+"' and ccod_articulo in ("+CodigosArticulos+") ";

            Log.d("sql",sql);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                arrayArticulos.add(Arrays.asList(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
            connection.close();
*/
        } catch (Exception e) {
            Log.d("BDCarritoPedidos", "- EnviarPedidos: " + e.getMessage());
        }
        return false;
    }
}