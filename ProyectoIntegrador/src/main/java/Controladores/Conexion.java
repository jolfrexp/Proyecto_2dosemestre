package Controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexion {
    public Connection getConnect(){

        Connection connection = null;

        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "");

            if(connection!= null){
            }


        }catch (SQLException e){

            System.out.println("Error: "+e);
        }

        return connection;



    }
}
