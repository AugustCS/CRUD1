package com.debugia.crudinicial;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CodigosGenerales {
    public static String Tipo = "";
    public static ArrayList<List<String>> listArrayList = new ArrayList<>();

    public static ArrayList getList(String Nombre) {
        try {
            switch (Tipo) {
                case "Familia":
                    return FamiliaBD.getListFamilia(Nombre);
                case "SubFamilia":
                    return SubFamiliaBD.getListSubFamilia(Nombre);
                case "Concepto":
                    return ConceptosBD.getListConcepto(Nombre);
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
                    return FamiliaBD.myArray;
                case "SubFamilia":
                    return SubFamiliaBD.myArray;
                case "Concepto":
                    return ConceptosBD.myArray;
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
                    FamiliaBD.cfamilia = Codigo;
                case "SubFamilia":
                    SubFamiliaBD.cSubfamilia = Codigo;
                case "Concepto":
                    ConceptosBD.cConcepto = Codigo;
            }
        } catch (Exception e) {
            Log.d("SetCodigLisArticulos", e.getMessage());
        }
    }


    public static ArrayList<List<String>> getListArticulos() {
        try {
            switch (Tipo) {
                case "Familia":
                    return FamiliaBD.getListFamiliaArticulos();
                case "SubFamilia":
                    return SubFamiliaBD.getListSubFamiliaArticulos();
                case "Concepto":
                    return ConceptosBD.getListConceptoArticulos();
            }
        } catch (Exception e) {
            Log.d("getListArticulos", e.getMessage());
        }
        return null;
    }
}