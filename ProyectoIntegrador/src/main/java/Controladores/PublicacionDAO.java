package Controladores;

import Producto.Publicacion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PublicacionDAO {

    public static void createPublicDAO(Publicacion publicacion) {
        Conexion conexion = new Conexion();

        try (Connection connection = conexion.getConnect()) {

            PreparedStatement ps;

            try {
                String query = "INSERT INTO Publicaciones (id_public,id_admin,titulo,descripcion,likes,contador_coment) VALUES(?,?,?,?,?,?)";
                ps = connection.prepareStatement(query);
                ps.setString(1,publicacion.getPublicacionList().get((publicacion.getPublicacionList().size()-1)).getIdPublicacion()+"");
                ps.setString(2,publicacion.getPublicacionList().get((publicacion.getPublicacionList().size()-1)).getUsuario());
                ps.setString(3,publicacion.getPublicacionList().get((publicacion.getPublicacionList().size()-1)).getTituloPublic());
                ps.setString(4,publicacion.getPublicacionList().get((publicacion.getPublicacionList().size()-1)).getDescripPublic());
                ps.setString(5,publicacion.getPublicacionList().get((publicacion.getPublicacionList().size()-1)).getLikePublic()+"");
                ps.setString(6,publicacion.getPublicacionList().get((publicacion.getPublicacionList().size()-1)).getContadorComent()+"");
                ps.executeUpdate();
                System.out.println("Registro de publicacion exitoso");

            } catch (SQLException e) {
                System.out.println("Error:" + e);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+ e);
        }
    }
    public static void selecPublicacion(Publicacion publicacion){
        Conexion conexion = new Conexion();
        try(Connection connection = conexion.getConnect()){
            PreparedStatement ps;
            ResultSet rs;
            try{
                String query = "SELECT * FROM publicaciones";
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                while(rs.next()){
                    publicacion.getPublicacionList().add(new Publicacion(rs.getInt("id_public"),rs.getString("id_admin"),rs.getString("titulo"),rs.getString("descripcion"),rs.getInt("likes"),rs.getInt("contador_coment")));
                }
            }catch (SQLException e){
                System.out.println("Error: " + e);
                System.out.println("No se pudo recuperar registros");
            }
        }catch (SQLException e){
            System.out.println("Error: " + e);
        }
    }
    public static void modificarPublic(String id,String donde,String valor){
        Conexion conexion = new Conexion();

        try (Connection connection = conexion.getConnect()) {
            PreparedStatement ps;

            try {
                String query = "UPDATE Publicaciones set "+donde+" = "+"'"+valor+"'"+" where id_admin = "+id;
                ps = connection.prepareStatement(query);
                ps.executeUpdate();
                System.out.println("Modificacion de Publicacion exitoso");

            } catch (SQLException e) {
                System.out.println("Error:" + e);
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    public static void modificarPublicN(String id,String donde,int valor){
        Conexion conexion = new Conexion();

        try (Connection connection = conexion.getConnect()) {
            PreparedStatement ps;

            try {
                String query = "UPDATE Publicaciones set "+donde+" = "+valor+" where id_public = "+id;
                ps = connection.prepareStatement(query);
                ps.executeUpdate();
                System.out.println("Modificacion de Publicacion exitoso");

            } catch (SQLException e) {
                System.out.println("Error:" + e);
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    public static void eliminarPublic(String id){
        Conexion conexion = new Conexion();
        try(Connection connection = conexion.getConnect()){
            PreparedStatement ps;
            try{
                String query ="DELETE FROM likes where  id_public1 = "+id;
                ps = connection.prepareStatement(query);
                ps.executeUpdate();
            }catch (SQLException e){
                System.out.println("Error: "+e);

            }try{
                String query ="DELETE FROM comentarios where id_public2 = "+id;
                ps = connection.prepareStatement(query);
                ps.executeUpdate();
            }catch (SQLException e){
                System.out.println("Error: "+e);
            }try{
                String query ="DELETE FROM publicaciones where id_public = "+id;
                ps = connection.prepareStatement(query);
                ps.executeUpdate();
            }catch (SQLException e){
                System.out.println("Error: "+e);
            }
        }catch (SQLException e){
            System.out.println("Error: "+e);
        }
    }
    public static void likes(String id_publicacion,String id_usuario){
        Conexion conexion = new Conexion();

        try(Connection connection = conexion.getConnect()){
            PreparedStatement ps;
            try{
                String query  = "INSERT INTO likes (id_public1,id_visitante1) VALUES (?,?)" ;
                ps = connection.prepareStatement(query);
                ps.setString(1,id_publicacion);
                ps.setString(2,id_usuario);
                ps.executeUpdate();
                System.out.println("Registro de like exitoso");
            }catch (SQLException e){
                System.out.println("Error: " + e);
            }
        }catch (SQLException e){
            System.out.println("Error: "+e);
        }

    }
    public static void comentarios(List<Publicacion> ComentList){
        Conexion conexion = new Conexion();
        try(Connection connection = conexion.getConnect()){
            PreparedStatement ps;
            try{
                String query = "INSERT INTO comentarios (id_public2,id_visitante2,contenido) VALUES(?,?,?)";
                ps = connection.prepareStatement(query);
                ps.setString(1,ComentList.get((ComentList.size()-1)).getIdPublicacion()+"");
                ps.setString(2,ComentList.get((ComentList.size()-1)).getVisitante().getId());
                ps.setString(3,ComentList.get((ComentList.size()-1)).getComentPublic());
                ps.executeUpdate();
                System.out.println("Registro comentario exitoso!");
            }catch (SQLException e){
                System.out.println("Error: "+e);
            }
        }catch (SQLException e){
            System.out.println("Error: "+e);
        }
    }
    public static void obtComent(String id_publico){
        Conexion conexion = new Conexion();
        try(Connection connection = conexion.getConnect()){
            PreparedStatement ps;
            ResultSet rs;
            try{
                String procedure = "CALL coment (?)";
                ps = connection.prepareStatement(procedure);
                ps.setString(1,id_publico);
                rs = ps.executeQuery();
                while(rs.next()){
                    System.out.println(rs.getString("nom_visitante")+"\n"+rs.getString("contenido"));
                }
            }catch (SQLException e){
                System.out.println("Error: "+e);
            }
        }catch (SQLException e){
            System.out.println("Error: "+e);
        }
    }
}
