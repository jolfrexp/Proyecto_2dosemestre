package Controladores;
import Usuario.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AdministradorDAO {
    public static void createAdministradoDAO(Administrador administrador) {
        Conexion conexion = new Conexion();

        try (Connection connection = conexion.getConnect()) {

            PreparedStatement ps;

            try {
                String query = "INSERT INTO Administrador (id_admin,nom_admin,ape_admin,tipo_docum,tel_admin,correo_admin,usuario_admin,cont_admin,cod_admin) VALUES(?,?,?,?,?,?,?,?,?)";
                ps = connection.prepareStatement(query);
                ps.setString(1, administrador.getAdminList().get(0).getId());
                ps.setString(2, administrador.getAdminList().get(0).getNombre());
                ps.setString(3, administrador.getAdminList().get(0).getApellido());
                ps.setString(4, administrador.getAdminList().get(0).getTipoDocumento());
                ps.setString(5, administrador.getAdminList().get(0).getTelefono());
                ps.setString(6, administrador.getAdminList().get(0).getCorreo());
                ps.setString(7, administrador.getAdminList().get(0).getUsuarioAdmin());
                ps.setString(8, administrador.getAdminList().get(0).getContrasenaAdmin());
                ps.setString(9,(administrador.getAdminList().get(0).getCodAdmin())+"");
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
    }
    public void selecAdmin(Administrador administrador){
        Conexion conexion = new Conexion();

        try(Connection connection = conexion.getConnect()){
            PreparedStatement ps;
            ResultSet rs;
            try{
                String query = "SELECT * FROM administrador ";
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                while(rs.next()){
                    administrador.getAdminList().add(new Administrador(rs.getString("nom_admin") , rs.getString("ape_admin"),rs.getString("tipo_docum") ,(rs.getInt("id_admin")+""),rs.getString("tel_admin"),rs.getString("correo_admin"),rs.getString("usuario_admin"), rs.getString("cont_admin"), rs.getInt("cod_admin")));
                }
            }catch (SQLException e){
                System.out.println("Error: "+e);
                System.out.println("No se pudo recuperar registros");
            }
        }catch (SQLException e){
            System.out.println("Error: "+e);
        }
    }
    public static void modificarAdmin(String id,String donde,String valor){
        Conexion conexion = new Conexion();

        try (Connection connection = conexion.getConnect()) {
        PreparedStatement ps;

        try {
            String query = "UPDATE Administrador set "+donde+" = "+"'"+valor+"'"+" where id_admin = "+id;
            ps = connection.prepareStatement(query);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error"+e);
        }
    } catch (SQLException e) {
        System.out.println("Error: "+e);
    }
    }
    public static void eliminarAdmin(String id){
        Conexion conexion = new Conexion();
        try(Connection connection = conexion.getConnect()){
            PreparedStatement ps;
            try{
                String query ="DELETE FROM publicaciones where  id_admin = ?";
                ps = connection.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(id));
                ps.executeUpdate();
            }catch (SQLException e){
                System.out.println("Error: "+e);

            }try{
                String query ="DELETE FROM administrador where id_admin = ?";
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
