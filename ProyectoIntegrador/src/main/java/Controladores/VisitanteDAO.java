package Controladores;
import Usuario.Visitante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class VisitanteDAO {
    public static void createVistanteDAO(Visitante visitante) {
        Conexion conexion = new Conexion();

        try (Connection connection = conexion.getConnect()) {

            PreparedStatement ps;

            try {
                String query = "INSERT INTO Visitante (id_visitante,nom_visitante,ape_visitante,tipo_docum,tel_visit,correo_visit,usuario_visit,cont_visit,cod_visit) VALUES(?,?,?,?,?,?,?,?,?)";
                ps = connection.prepareStatement(query);
                ps.setString(1, visitante.getVisitList().get((visitante.getVisitList().size()-1)).getId());
                ps.setString(2, visitante.getVisitList().get((visitante.getVisitList().size()-1)).getNombre());
                ps.setString(3, visitante.getVisitList().get((visitante.getVisitList().size()-1)).getApellido());
                ps.setString(4, visitante.getVisitList().get((visitante.getVisitList().size()-1)).getTipoDocumento());
                ps.setString(5, visitante.getVisitList().get((visitante.getVisitList().size()-1)).getTelefono());
                ps.setString(6, visitante.getVisitList().get((visitante.getVisitList().size()-1)).getCorreo());
                ps.setString(7, visitante.getVisitList().get((visitante.getVisitList().size()-1)).getUsuarioVisit());
                ps.setString(8, visitante.getVisitList().get((visitante.getVisitList().size()-1)).getContrasenaVisit());
                ps.setString(9,(visitante.getVisitList().get((visitante.getVisitList().size()-1)).getCodVist())+"");
                ps.executeUpdate();
                System.out.println("Registro de Visitante exitoso");

            } catch (SQLException e) {
                System.out.println("Error: "+e);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
    }
    public static void selecVisitante(Visitante visitante){
        Conexion conexion = new Conexion();

        try(Connection connection = conexion.getConnect()){
            PreparedStatement ps;
            ResultSet rs;
            try{
                String query = "SELECT * FROM Visitante ";
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                while(rs.next()){
                    visitante.getVisitList().add(new Visitante(rs.getString("nom_visitante") , rs.getString("ape_visitante"),rs.getString("tipo_docum") ,(rs.getInt("id_visitante")+""),rs.getString("tel_visit"),rs.getString("correo_visit"),rs.getString("usuario_visit"), rs.getString("cont_visit"), rs.getInt("cod_visit")));
                }
            }catch (SQLException e){
                System.out.println("Error: "+e);
                System.out.println("No se pudo recuperar registros");
            }
        }catch (SQLException e){
            System.out.println("Error: "+e);
        }
    }
    public static void modificarVisit(String id,String donde,String valor){
        Conexion conexion = new Conexion();

        try (Connection connection = conexion.getConnect()) {
            PreparedStatement ps;

            try {
                String query = "UPDATE visitante set "+donde+" = '"+valor+"' where id_visitante = "+id;
                ps = connection.prepareStatement(query);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error: "+e);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
    }
    public static void eliminarVisit(String id){
        Conexion conexion = new Conexion();
        try(Connection connection = conexion.getConnect()){
            PreparedStatement ps;
            try{
                String query ="DELETE FROM likes where  id_visitante1 = ?";
                ps = connection.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(id));
                ps.executeUpdate();
                System.out.println("Visitante eliminado con exito!");
            }catch (SQLException e){
                System.out.println("Error: "+e);

            }try{
                String query ="DELETE FROM comentarios where id_visitante2 = ?";
                ps = connection.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(id));
                ps.executeUpdate();
            }catch (SQLException e){
                System.out.println("Error: "+e);
            }try{
                String query ="DELETE from visitante where id_visitante = ?";
                ps = connection.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(id));
                ps.executeUpdate();
            }catch (SQLException e){
                System.out.println("Error: "+e);
            }
        }catch (SQLException e){
            System.out.println("Error: "+e);
        }
    }
}
