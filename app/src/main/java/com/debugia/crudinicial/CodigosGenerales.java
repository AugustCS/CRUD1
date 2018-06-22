package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodigosGenerales {

    public static Integer ConceptoElegido;
    public static Boolean Iniciar=true;
    public static String Tipo = "";
    public static ArrayList<List<String>> listArrayList = new ArrayList<>();
    public static String Cod_Articulo = "";
    public static String nom_categoria="";

    public static BDFamilia bdFamilia=new BDFamilia();
    public static BDSubFamilia bdSubFamilia=new BDSubFamilia();
    public static BDConcepto bdConcepto= new BDConcepto();

    public static List getListaNombres(String Nombre) {
        try {
            switch (Tipo) {
                case "Familia":
                    return bdFamilia.getListaNombres(Nombre);
                case "SubFamilia":
                    return bdSubFamilia.getListaNombres(Nombre);
                case "Concepto":
                    return bdConcepto.getListaNombres(Nombre);
            }
        } catch (Exception e) {
            Log.d("CodigosGenerales"," -getList: "+ e.getMessage());
        }
        return null;
    }

    public static ArrayList<List<String>> getlistArrayList() {
        try {
            switch (Tipo) {
                case "Familia":
                    return bdFamilia.arrayLista;
                case "SubFamilia":
                    return bdSubFamilia.arrayLista;
                case "Concepto":
                    return bdConcepto.arrayLista;
            }
        } catch (Exception e) {
            Log.d("CodigosGenerales"," -getlistArrayList: "+ e.getMessage());
        }
        return null;
    }

    public static void SetCodigLisArticulos(String Codigo) {
        try {
            switch (Tipo) {
                case "Familia":
                    bdFamilia.cCodigo = Codigo;
                case "SubFamilia":
                    bdSubFamilia.cCodigo = Codigo;
                case "Concepto":
                    bdConcepto.cCodigo = Codigo;
            }
        } catch (Exception e) {
            Log.d("CodigosGenerales"," -SetCodigLisArticulos: "+ e.getMessage());
        }
    }


    public static ArrayList<List<String>> getListArticulos(String Nombre) {
        try {
            switch (Tipo) {
                case "Familia":
                    return bdFamilia.getListaArticulos(Nombre);
                case "SubFamilia":
                    return bdSubFamilia.getListaArticulos(Nombre);
                case "Concepto":
                    return bdConcepto.getListaArticulos(Nombre);
            }
        } catch (Exception e) {
            Log.d("CodigosGenerales"," -getListArticulos: "+ e.getMessage());
        }
        return null;
    }

    public static ArrayList<List<String>> getArticulosDescripcion() {
        try {
            switch (Tipo) {
                case "Familia":
                    return bdFamilia.getArticuloSeleccionado(Cod_Articulo);
                case "SubFamilia":
                   return   bdSubFamilia.getArticuloSeleccionado(Cod_Articulo);
                case "Concepto":
                    return bdConcepto.getArticuloSeleccionado(Cod_Articulo);
            }
        } catch (Exception e) {
            Log.d("SetCodigLisArticulos", e.getMessage());
        }
        return null;
    }

    public static ArrayList getListFiltros() {

        return null;
    }

}