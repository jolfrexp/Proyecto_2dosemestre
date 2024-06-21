import Controladores.AdministradorDAO;
import Controladores.PublicacionDAO;
import Controladores.VisitanteDAO;
import Producto.Menu;
import Producto.Publicacion;
import Usuario.Administrador;
import Usuario.Visitante;

public class MusticApplication {
    public static void main(String[] args) {
        AdministradorDAO administradorDAO = new AdministradorDAO();
        Administrador administrador = new Administrador();//Inicializacion de las clases

        Publicacion publicacion = new Publicacion();
        Visitante visitante = new Visitante();
        Menu menu = new Menu();
        publicacion.setAdministrador(administrador);
        visitante.setAdministrador(administrador);
        administrador.setVisitante(visitante);
        publicacion.setVisitante(visitante);
        PublicacionDAO.selecPublicacion(publicacion);
        administradorDAO.selecAdmin(administrador);//Bajando informacion de la base de datos
        VisitanteDAO.selecVisitante(visitante);
        String salir,salir1,salir2;
        salir = "no";
        while (salir.equals("no")){

            menu.menuPrincipal();
            switch (menu.getRpta1()){
                case "1":
                    visitante.crearUsuario();
                    VisitanteDAO.createVistanteDAO(visitante);
                    break;
                case "2":
                    visitante.loginVisit();
                    if (visitante.isValid()){
                        salir1 = "no";
                        while(salir1.equals("no")){
                            menu.menuUsuario();
                            switch (menu.getRpta2()){
                                case "1":
                                    publicacion.seleccionarPublicacion();
                                    break;
                                case "2":
                                    administrador.seleccionarUsuario();
                                    break;
                                case "3":
                                    visitante.seleccionarUsuario();
                                    break;
                                case "4":
                                    visitante.modificarUsuario();
                                    break;
                                case"5":
                                    visitante.eliminarUsuario();
                                    if (visitante.isValid()){
                                        salir1 = "si";
                                    }
                                    break;
                                case "6":
                                    System.out.println("Cerrando Sesión...");
                                    salir1 = "si";
                                    break;
                            }
                        }

                    }else{
                        System.out.println("Error en el ingreso o no hay usuarios registrados");
                    }
                    break;
                case "3":
                    administrador.seleccionarUsuario();
                    break;
                case "4":
                    salir = "si";
                    break;
                case "5":
                    menu.menuInicioArtista();
                    switch (menu.getRpta3()){
                        case "1":
                            administrador.crearUsuario();
                            AdministradorDAO.createAdministradoDAO(administrador);
                            break;
                        case "2":
                            administrador.loginAdmin();
                            if (administrador.isValid()){
                                salir2 = "no";
                                while (salir2.equals("no")) {
                                    menu.menuArtista();
                                    switch (menu.getRpta4()) {
                                        case "1":
                                            publicacion.crearPublicacion();
                                            PublicacionDAO.createPublicDAO(publicacion);
                                            break;
                                        case "2":
                                            publicacion.modificarPublicacion();
                                            break;
                                        case "3":
                                            publicacion.seleccionarPublicacion();
                                            break;
                                        case "4":
                                            publicacion.eliminarPublicacion();
                                            break;
                                        case "5":
                                            administrador.seleccionarUsuario();
                                            break;
                                        case "6":
                                            administrador.modificarUsuario();
                                            break;
                                        case "7":
                                            administrador.eliminarUsuario();
                                            if (administrador.isValid()){
                                             salir2 = "si";
                                            }
                                            break;
                                        case "8":
                                            System.out.println("Cerrando sesión...");
                                            salir2 = "si";
                                            break;
                                    }
                                }
                            }else {
                                System.out.println("Error en el ingreso");
                            }
                            break;
                    }
                    break;
            }
        }
    }

}
