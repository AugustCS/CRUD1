package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDConcepto {
    public Integer concepto=CodigosGenerales.ConceptoElegido;
    public String cCodigo;
    public ArrayList<List<String>> arrayLista = new ArrayList<>();
    public ArrayList<List<String>> arrayArticulos = new ArrayList<>();


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

    public ArrayList getListaNombres(String Nombre) {
        ArrayList listaNombres = new ArrayList<String>();
        Connection connection = null;
        Integer concepto=CodigosGenerales.ConceptoElegido;
        try {
            arrayLista.clear();
            connection= BConexionSQL.getConnection();

            String stsql = "select * from Erp_concepto"+concepto+" where erp_codemp=? and erp_nomcon like ? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, BDUsuario.CodEmp);
            query.setString(2, Nombre+"%");

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                listaNombres.add(rs.getString("erp_nomcon"));
                arrayLista.add(Arrays.asList(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("BDConcepto", "- getListaNombres: "+e.getMessage());
        }
        return listaNombres;
    }

    public ArrayList<List<String>> getListaArticulos(String Nombre) {

        Connection connection = null;
        try {
            arrayArticulos.clear();
            connection= BConexionSQL.getConnection();

            String stsql=null;
            switch (concepto){
                case 1:
                    stsql = "select * from Harticul where ccod_empresa=? and codmarca = ? and cnom_articulo like ?";
                    break;
                case 2:
                    stsql = "select * from Harticul where ccod_empresa=? and modelo = ? and cnom_articulo like ?";
                    break;
                case 3:
                    stsql = "select * from Harticul where ccod_empresa=? and color = ? and cnom_articulo like ?";
                    break;
                case 4:
                    stsql = "select * from Harticul where ccod_empresa=? and tratamiento = ? and cnom_articulo like ?";
                    break;
                case 5:
                    stsql = "select * from Harticul where ccod_empresa=? and fuelle = ? and cnom_articulo like ?";
                    break;
                case 6:
                    stsql = "select * from Harticul where ccod_empresa=? and azas = ? and cnom_articulo like ?";
                    break;
                case 7:
                    stsql = "select * from Harticul where ccod_empresa=? and solapa = ? and cnom_articulo like ?";
                    break;
            }

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
            Log.d("BDConcepto", "- getListaArticulos: "+e.getMessage());
        }
        return arrayArticulos;
    }
    public ArrayList<List<String>> getArticuloSeleccionado(String Codigo) {
        ArrayList<List<String>> arrayArticuloSeleccionado = new ArrayList<>();
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
            query.setString(2, cCodigo);
            query.setString(3, Codigo);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                arrayArticuloSeleccionado.add(Arrays.asList(
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
            Log.d("BDConcepto", "- getArticuloSeleccionado: "+e.getMessage());
        }
        return arrayArticuloSeleccionado;
    }
}
