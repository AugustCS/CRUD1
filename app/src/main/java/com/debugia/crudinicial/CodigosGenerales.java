package com.debugia.crudinicial;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CodigosGenerales {
    public static Boolean Iniciar=true;
    public static String Tipo = "";
    public static ArrayList<List<String>> listArrayList = new ArrayList<>();
    public static String Cod_Articulo = "";
    public static String nom_categoria="";

    public static ArrayList getList(String Nombre) {
        try {
            switch (Tipo) {
                case "Familia":
                    return BDFamilia.getListFamilia(Nombre);
                case "SubFamilia":
                    return BDSubFamilia.getListSubFamilia(Nombre);
                case "Concepto":
                    return BDConcepto.getListConcepto(Nombre);
            }
        } catch (Exception e) {
            Log.d("getList", e.getMessage());
        }
        return null;
    }

    public static ArrayList<List<String>> getlistArrayList() {
        try {
            switch (Tipo) {
                case "Familia":
                    return BDFamilia.myArray;
                case "SubFamilia":
                    return BDSubFamilia.myArray;
                case "Concepto":
                    return BDConcepto.myArray;
            }
        } catch (Exception e) {
            Log.d("getlistArrayList", e.getMessage());
        }
        return null;
    }

    public static void SetCodigLisArticulos(String Codigo) {
        try {
            switch (Tipo) {
                case "Familia":
                    BDFamilia.cfamilia = Codigo;
                case "SubFamilia":
                    BDSubFamilia.cSubfamilia = Codigo;
                case "Concepto":
                    BDConcepto.cConcepto = Codigo;
            }
        } catch (Exception e) {
            Log.d("SetCodigLisArticulos", e.getMessage());
        }
    }


    public static ArrayList<List<String>> getListArticulos() {
        try {
            switch (Tipo) {
                case "Familia":
                    return BDFamilia.getListFamiliaArticulos();
                case "SubFamilia":
                    return BDSubFamilia.getListSubFamiliaArticulos();
                case "Concepto":
                    return BDConcepto.getListConceptoArticulos();
            }
        } catch (Exception e) {
            Log.d("getListArticulos", e.getMessage());
        }
        return null;
    }

    public static ArrayList<List<String>> getArticulosDescripcion() {
        try {
            switch (Tipo) {
                case "Familia":
                    return BDFamilia.getDescripcion(Cod_Articulo);
                case "SubFamilia":
                   return   BDSubFamilia.getDescripcion(Cod_Articulo);
                case "Concepto":
                    return BDConcepto.getDescripcion(Cod_Articulo);
            }
        } catch (Exception e) {
            Log.d("SetCodigLisArticulos", e.getMessage());
        }
        return null;
    }
}