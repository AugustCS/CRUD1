package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubFamiliaBD {

    public static ArrayList getListSubFamiliaSearchView(String cnom_familia) {
        ArrayList Familia = new ArrayList<String>();
        Connection connection = null;

        try {
            connection=ConexionSQL.getConnection();

            String stsql = "select * from Hsubfamilia_art where ccod_empresa=? and cnom_subfamilia like ? ";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, UsuarioBD.CodEmp);
            query.setString(2, cnom_familia+"%");

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Familia.add(rs.getString("cnom_familia"));
            }
            connection.close();

        } catch (Exception e) {
            Log.d("getListSubFamiliaSearchView", e.getMessage());
        }
        return Familia;
    }
}
