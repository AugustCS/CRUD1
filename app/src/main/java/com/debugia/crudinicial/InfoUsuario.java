package com.debugia.crudinicial;

import android.util.Log;

import java.util.ArrayList;

public class InfoUsuario {
    public  static String CodEmp="prueba";
    public static void GuardarUsuario(ArrayList Familia){
        CodEmp=Familia.get(0).toString();
    }
}
