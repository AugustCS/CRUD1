package com.debugia.crudinicial;

import java.util.ArrayList;

public class ConexionSQL {


    ArrayList nicks = new ArrayList<String>();

    public Boolean VerificarIngreso(String RUC, String Usuario, String Clave) {
/*

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection conn = DriverManager.getConnection("192.168.1.111\SQLSERVER2008R2\
                    Bd_Consultoria_2015", "sa", "Solu123456
                    ");

                    String stsql = "select * from Hcliente where RUC='' and Usuario='' and Clave='' and Estado=''";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(stsql);

            while(rs.next()){
                nicks.add(rs.getString("Name"));
            }

            conn.close();
            if(nicks.Rows.count>0)
                return true;

        }catch (SQLException se) {
            System.out.println("No se puede conectar. Error: " + se.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la clase. Error: " + e.getMessage());
        }
        return false;
        */
        return false;
    }
}