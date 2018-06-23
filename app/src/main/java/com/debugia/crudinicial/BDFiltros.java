package com.debugia.crudinicial;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDFiltros {

    public String cCodigo;
    public ArrayList<List<String>> arrayLista = new ArrayList<>();
    public ArrayList<List<String>> arrayArticulos = new ArrayList<>();

    public ArrayList<List<String>> getListaArticulos(List<String> Familia,
                                                     List<String> SubFamilia,
                                                     List<String> Concepto1,
                                                     List<String> Concepto2,
                                                     List<String> Concepto3,
                                                     List<String> Concepto4,
                                                     List<String> Concepto5,
                                                     List<String> Concepto6,
                                                     List<String> Concepto7) {

        Connection connection = null;
        try {
            arrayArticulos.clear();
            connection= BConexionSQL.getConnection();

            String sql= "Select * from Harticul where ccod_empresa='" + BDUsuario.CodEmp + "'";

            if(Familia.size()>0) {
                sql += " and ( ";
                for (int i = 0; i < Familia.size(); i++) {
                    sql += "cfamilia='" + Familia.get(i) + "'";
                    if (i + 1 < Familia.size())
                        sql += " or ";
                    else
                        sql += " )";
                }
            }
            if(SubFamilia.size()>0) {
                sql += " and ( ";
                for (int i = 0; i < SubFamilia.size(); i++) {
                    sql += "ccod_subfamilia='" + SubFamilia.get(i) + "'";
                    if (i + 1 < SubFamilia.size())
                        sql += " or ";
                    else
                        sql += " )";
                }
            }
            if(Concepto1.size()>0) {
                sql += " and ( ";
                for (int i = 0; i < Concepto1.size(); i++) {
                    sql += "codmarca='" + Concepto1.get(i) + "'";
                    if (i + 1 < Concepto1.size())
                        sql += " or ";
                    else
                        sql += " )";
                }
            }
            if(Concepto2.size()>0) {
                sql += " and ( ";
                for (int i = 0; i < Concepto2.size(); i++) {
                    sql += "modelo='" + Concepto2.get(i) + "'";
                    if (i + 1 < Concepto2.size())
                        sql += " or ";
                    else
                        sql += " )";
                }
            }
            if(Concepto3.size()>0) {
                sql += " and ( ";
                for (int i = 0; i < Concepto3.size(); i++) {
                    sql += "color='" + Concepto3.get(i) + "'";
                    if (i + 1 < Concepto3.size())
                        sql += " or ";
                    else
                        sql += " )";
                }
            }
            if(Concepto4.size()>0) {
                sql += " and ( ";
                for (int i = 0; i < Concepto4.size(); i++) {
                    sql += "tratamiento='" + Concepto4.get(i) + "'";
                    if (i + 1 < Concepto4.size())
                        sql += " or ";
                    else
                        sql += " )";
                }
            }
            if(Concepto5.size()>0) {
                sql += " and ( ";
                for (int i = 0; i < Concepto5.size(); i++) {
                    sql += "fuelle='" + Concepto5.get(i) + "'";
                    if (i + 1 < Concepto5.size())
                        sql += " or ";
                    else
                        sql += " )";
                }
            }
            if(Concepto6.size()>0) {
                sql += " and ( ";
                for (int i = 0; i < Concepto6.size(); i++) {
                    sql += "azas='" + Concepto6.get(i) + "'";
                    if (i + 1 < Concepto6.size())
                        sql += " or ";
                    else
                        sql += " )";
                }
            }
            if(Concepto7.size()>0) {
                sql += " and ( ";
                for (int i = 0; i < Concepto7.size(); i++) {
                    sql += "solapa='" + Concepto7.get(i) + "'";
                    if (i + 1 < Concepto7.size())
                        sql += " or ";
                    else
                        sql += " )";
                }
            }

            Log.d("SQL",sql );
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

        } catch (Exception e) {
            Log.d("BDFamilia", "- getListaArticulos: "+e.getMessage());
        }
        return arrayArticulos;
    }
}
