package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDFamilia {
    public String cCodigo;
    public ArrayList<List<String>> arrayLista = new ArrayList<>();
    public ArrayList<List<String>> arrayArticulos = new ArrayList<>();

    public List<String> getListaNombres(String Nombre) {
        List<String>listaNombres = new ArrayList<String>();
        Connection connection = null;

        try {
            arrayLista.clear();
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Hfam_art where ccod_empresa=? and cnom_familia like ? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, Nombre+"%");

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                listaNombres.add(rs.getString("cnom_familia"));
                arrayLista.add(Arrays.asList(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("BDFamilia", "- getListaNombres: "+e.getMessage());
        }
        return listaNombres;
    }

    public ArrayList<List<String>> getListaArticulos(String Nombre) {

        Connection connection = null;
        try {
            arrayArticulos.clear();
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Harticul where ccod_empresa=? and cfamilia=?  and cnom_articulo like ? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cCodigo);
            query.setString(3, Nombre+"%");

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                arrayArticulos.add(Arrays.asList(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("BDFamilia", "- getListaArticulos: "+e.getMessage());
        }
        return arrayArticulos;
    }
    public ArrayList<List<String>> getArticuloSeleccionado(String Codigo) {
        ArrayList<List<String>> arrayArticuloSeleccionado = new ArrayList<>();
        Connection connection = null;
        try {
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Harticul where ccod_empresa=? and cfamilia=? and ccod_articulo=? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, cCodigo);
            query.setString(3, Codigo);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                arrayArticuloSeleccionado.add(Arrays.asList(
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
            Log.d("BDFamilia", "- getArticuloSeleccionado: "+e.getMessage());
        }
        return arrayArticuloSeleccionado;
    }
}
