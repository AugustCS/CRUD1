package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConceptosBD {

    public static ArrayList getListConceptos() {
        ArrayList arrayList = new ArrayList<String>();
        Connection connection = null;

        try {
            connection=ConexionSQL.getConnection();

            String stsql = "select * from Htableconceptos_erp where ccod_empresa=?";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, UsuarioBD.CodEmp);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                arrayList.add(rs.getString(1));
                arrayList.add(rs.getString(2));
                arrayList.add(rs.getString(3));
            }
            Log.d("Arrays",arrayList+"");
            Log.d("Arrays",arrayList.toString());
            connection.close();

        } catch (Exception e) {
            Log.d("getListConceptos", e.getMessage());
        }
        return arrayList;
    }
}
