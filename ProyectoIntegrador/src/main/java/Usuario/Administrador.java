package Usuario;

import Controladores.AdministradorDAO;
import enums.Confirmacion;
import helpers.ValidacionOpciones;
import helpers.ValidacionPerfil;
import helpers.ValidacionUsuario;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Administrador extends Usuario{
    List<Administrador> adminList = new ArrayList<>();
    private Visitante visitante;
    private int a;
    private String email_Usuario, contrasena;
    private boolean valid = false;
    private String usuarioAdmin, contrasenaAdmin;
    private int codAdmin = 0;
    public Administrador() {

    }

    public Administrador(String nombre, String apellido, String tipoDocumento, String id, String telefono, String correo, String usuarioAdmin, String contrasenaAdmin, int codAdmin) {
        super(nombre, apellido, tipoDocumento, id, telefono, correo);
        this.usuarioAdmin = usuarioAdmin;
        this.contrasenaAdmin = contrasenaAdmin;
        this.codAdmin = codAdmin;
    }

    public String getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public void setUsuarioAdmin(String usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    public String getContrasenaAdmin() {
        return contrasenaAdmin;
    }

    public void setContrasenaAdmin(String contrasenaAdmin) {
        this.contrasenaAdmin = contrasenaAdmin;
    }

    public int getCodAdmin() {
        return codAdmin;
    }

    public void setCodAdmin(int codAdmin) {
        this.codAdmin = codAdmin;
    }

    public List<Administrador> getAdminList() {
        return adminList;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getEmail_Usuario() {
        return email_Usuario;
    }

    public void setEmail_Usuario(String email_Usuario) {
        this.email_Usuario = email_Usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    //metodos
    @Override
    public void crearUsuario(){
        setUsuarioAdmin(null);
        setContrasenaAdmin(null);
        super.crearUsuario(administrador,getVisitante());
        while(getUsuarioAdmin() == null) {
            System.out.println("Usuario Administrador: ");
            String usuarioVisit = sc.nextLine();
            if (ValidacionPerfil.validaUsuario(usuarioVisit)){
                setUsuarioAdmin(usuarioVisit);
            }else{
                System.out.println("Ingrese un Usuario valido");
            }
        }
        while(getContrasenaAdmin() == null){
            System.out.println("Contraseña Administrador: ");
            String contrasenaAdmin = sc.nextLine();
            if (ValidacionPerfil.validaContrasena(contrasenaAdmin)){
                System.out.println("Confirme la contraseña");
                String contrasenaAdmin2 = sc.nextLine();
                if ((ValidacionPerfil.validaContrasena(contrasenaAdmin2))&& (contrasenaAdmin.equals(contrasenaAdmin2))) {
                    setContrasenaAdmin(contrasenaAdmin);
                }else{
                    System.out.println("Las contraseñas no coinciden");
                }
            }else{
                System.out.println("Ingrese una contraseña valida");
            }
        }
        setCodAdmin((getCodAdmin() + 1));
        System.out.println("Su codigo de Administrador es: "+getCodAdmin());
        adminList.add(new Administrador(getNombre(), getApellido(),getTipoDocumento(), getId(), getTelefono(), getCorreo(), getUsuarioAdmin(), getContrasenaAdmin(),getCodAdmin()));
    }
    @Override
    public void seleccionarUsuario(){
        if (!adminList.isEmpty()){
            setId(adminList.get(getA()).getId());
            setNombre(adminList.get(getA()).getNombre());
            setApellido(adminList.get(getA()).getApellido());
            setTipoDocumento(adminList.get(getA()).getTipoDocumento());
            setTelefono(adminList.get(getA()).getTelefono());
            setCorreo(adminList.get(getA()).getCorreo());
            setUsuarioAdmin(adminList.get(getA()).getUsuarioAdmin());
            setContrasenaAdmin(adminList.get(getA()).getContrasenaAdmin());
        }
        super.seleccionarUsuario();
        if (getId() != null) {
            System.out.println("Usuario Administrador: " + getUsuarioAdmin());
            System.out.println("Contraseña Administrador: ******");
        }else{
            System.out.println("No hay Administrador registrado");
        }
    }
    @Override
    public void modificarUsuario(){
        if (!adminList.isEmpty()){
            setId(adminList.get(getA()).getId());
            setNombre(adminList.get(getA()).getNombre());
            setApellido(adminList.get(getA()).getApellido());
            setTipoDocumento(adminList.get(getA()).getTipoDocumento());
            setTelefono(adminList.get(getA()).getTelefono());
            setCorreo(adminList.get(getA()).getCorreo());
            setUsuarioAdmin(adminList.get(getA()).getUsuarioAdmin());
            setContrasenaAdmin(adminList.get(getA()).getContrasenaAdmin());
        }
        String salir = "no";
        while(salir.equals("no")) {
            super.modificarUsuario();
            setUsuarioAdmin(null);
            setContrasenaAdmin(null);
            if (!adminList.isEmpty()) {
                String rpt = sc.next();
                if (ValidacionOpciones.validaopc7(rpt)) {
                    switch (rpt) {
                        case "1":
                            System.out.println("Nombre anterior: " + adminList.get(getA()).getNombre());
                            while (getNombre() == null) {
                                System.out.println("Ingrese el nuevo nombre: ");
                                String nombre = sc.nextLine();
                                if (ValidacionUsuario.validarNombre(nombre)) {
                                    System.out.println("Nombre anterior : " + adminList.get(getA()).getNombre());
                                    System.out.println("Nombre nuevo: " + nombre);
                                    System.out.println("Guardar (si/no)");
                                    String rpt1 = sc.next();
                                    if (Objects.equals(rpt1, "si")) {
                                        adminList.get(getA()).setNombre(nombre);
                                        setNombre(nombre);
                                        AdministradorDAO.modificarAdmin(adminList.get(getA()).getId(),"nom_admin",nombre);
                                        System.out.println("Se ha actualizado el nombre!");
                                    } else {
                                        System.out.println("Se a cancelado la modificacion");
                                    }
                                } else {
                                    System.out.println("Ingrese un nombre valido");
                                }
                            }
                            break;
                        case "2":
                            System.out.println("Apellido anterior: " + adminList.get(getA()).getApellido());
                            while (getApellido() == null) {
                                System.out.println("Ingrese el nuevo apellido: ");
                                String apellido = sc.nextLine();
                                if (ValidacionUsuario.validarApellido(apellido)) {
                                    System.out.println("Apellido anterior : " + adminList.get(getA()).getApellido());
                                    System.out.println("Apellido nuevo: " + apellido);
                                    System.out.println("Guardar (si/no)");
                                    String rpt1 = sc.next();
                                    if (Objects.equals(rpt1, "si")) {
                                        adminList.get(getA()).setApellido(apellido);
                                        setApellido(apellido);
                                        AdministradorDAO.modificarAdmin(adminList.get(getA()).getId(),"ape_admin",apellido);
                                        System.out.println("Se ha actualizado el apellido!");
                                    } else {
                                        System.out.println("Se a cancelado la modificacion");
                                    }
                                } else {
                                    System.out.println("Ingrese un apellido valido");
                                }
                            }
                            break;
                        case "3":
                            System.out.println("Telefono anterior: " + adminList.get(getA()).getTelefono());
                            while (getTelefono() == null) {
                                System.out.println("Ingrese el nuevo telefono: ");
                                String telefono = sc.nextLine();
                                if (ValidacionUsuario.validarTel(telefono)) {
                                    System.out.println("Nombre anterior : " + adminList.get(getA()).getTelefono());
                                    System.out.println("Nombre nuevo: " + telefono);
                                    System.out.println("Guardar:\n1.-si\n2.-no");
                                    String rpt1 = sc.next();

                                    if (Objects.equals(Confirmacion.values()[Integer.parseInt(rpt1)-1], "SI")&& ValidacionOpciones.validaopc2(rpt1)) {
                                        adminList.get(getA()).setTelefono(telefono);
                                        setTelefono(telefono);
                                        AdministradorDAO.modificarAdmin(adminList.get(getA()).getId(),"tel_admin",telefono);
                                        System.out.println("Se ha actualizado el Telefono!");
                                    } else {
                                        System.out.println("Se a cancelado la modificacion");
                                    }
                                } else {
                                    System.out.println("Numero de telefono invalido");
                                }
                            }
                            break;
                        case "4":
                            System.out.println("Correo anterior: " + adminList.get(getA()).getCorreo());
                            while (getCorreo() == null) {
                                System.out.println("Ingrese el nuevo correo: ");
                                String correo = sc.nextLine();
                                if (ValidacionUsuario.validarEmail(correo)) {
                                    System.out.println("Nombre anterior : " + adminList.get(getA()).getCorreo());
                                    System.out.println("Nombre nuevo: " + correo);
                                    System.out.println("Guardar (si/no)");
                                    String rpt1 = sc.next();
                                    if (Objects.equals(rpt1, "si")) {
                                        adminList.get(getA()).setCorreo(correo);
                                        setCorreo(correo);
                                        AdministradorDAO.modificarAdmin(adminList.get(getA()).getId(),"correo_admin",correo);
                                        System.out.println("Se ha actualizado el correo!");
                                    } else {
                                        System.out.println("Se a cancelado la modificacion");
                                    }
                                } else {
                                    System.out.println("Direccion de correo no valida");
                                }
                            }
                            break;
                        case "5":
                            System.out.println("Usuario anterior: " + adminList.get(getA()).getUsuarioAdmin());
                            while (getUsuarioAdmin() == null) {
                                System.out.println("Ingrese el nuevo Usuario: ");
                                String Usuario = sc.nextLine();
                                if (ValidacionPerfil.validaUsuario(Usuario)) {
                                    System.out.println("Usuario anterior : " + adminList.get(getA()).getUsuarioAdmin());
                                    System.out.println("Usuario nuevo: " + Usuario);
                                    System.out.println("Guardar (si/no)");
                                    String rpt1 = sc.next();
                                    if (Objects.equals(rpt1, "si")) {
                                        adminList.get(getA()).setUsuarioAdmin(Usuario);
                                        setUsuarioAdmin(Usuario);
                                        AdministradorDAO.modificarAdmin(adminList.get(getA()).getId(),"usuario_admin",Usuario);
                                        System.out.println("Se ha actualizado el Usuario!");
                                    } else {
                                        System.out.println("Se a cancelado la modificacion");
                                    }
                                } else {
                                    System.out.println("Ingrese un Usuario valido");
                                }
                            }
                            break;
                        case "6":
                            while (getContrasenaAdmin() == null) {
                                System.out.println("Ingresa la contraseña anterior");
                                String cont1 = sc.next();
                                if (Objects.equals(cont1, adminList.get(getA()).getContrasenaAdmin())) {
                                    System.out.println("Ingrese la nueva contraseña: ");
                                    String cont2 = sc.nextLine();
                                    if (ValidacionPerfil.validaContrasena(cont2)) {
                                        System.out.println("confirme la contraseña");
                                        String cont3 = sc.next();
                                        if (ValidacionPerfil.validaContrasena(cont3) && cont2.equals(cont3)) {
                                            adminList.get(getA()).setContrasenaAdmin(cont3);
                                            setContrasenaAdmin(cont3);
                                            AdministradorDAO.modificarAdmin(adminList.get(getA()).getId(),"cont_admin",cont3);
                                        } else {
                                            System.out.println("Las contraseñas no coinciden");
                                        }
                                    } else {
                                        System.out.println("Contraseña invalida");
                                    }
                                } else {
                                    System.out.println("Contraseña incorrecta");
                                }
                            }
                            break;
                        case "7":
                            salir = "si";
                            break;
                    }
                } else {
                    System.out.println("Digite una opcion valida");
                }

            } else {
                System.out.println("No hay Admin's registrados:(");
            }
        }
    }
    @Override
    public void eliminarUsuario(){
        if (!adminList.isEmpty()){
            setId(adminList.get(getA()).getId());
            setNombre(adminList.get(getA()).getNombre());
            setApellido(adminList.get(getA()).getApellido());
            setTipoDocumento(adminList.get(getA()).getTipoDocumento());
            setTelefono(adminList.get(getA()).getTelefono());
            setCorreo(adminList.get(getA()).getCorreo());
            setUsuarioAdmin(adminList.get(getA()).getUsuarioAdmin());
            setContrasenaAdmin(adminList.get(getA()).getContrasenaAdmin());
        }
        super.eliminarUsuario();
        if (!adminList.isEmpty()){
            if (isRpta()) {
                AdministradorDAO.eliminarAdmin(adminList.get(getA()).getId());
                adminList.remove(getA());

                System.out.println("Administrador eliminado con exito!");
            }else {
                System.out.println("Eliminancion de Administrador cancelado");
            }
        }else {
            System.out.println("No hay Administrador registrado");
        }
    }
    public void loginAdmin(){
        System.out.println("******************* Inicio de sesion *******************");
        boolean validC = false;
        boolean validP = false;
        setValid(!isValid());
        while (!validC && !adminList.isEmpty()) {
            System.out.println("numero visitantes registrados: "+getCodAdmin());
            System.out.println("Ingrese su Usuario: ");
            setEmail_Usuario(sc.next());
            setA(0);
            while (getA()< getAdminList().size() && !validC ) {
                if (getEmail_Usuario().equals(getAdminList().get(getA()).getUsuarioAdmin())) {
                    System.out.println("Usuario Encontrado : "+getAdminList().get(getA()).getUsuarioAdmin());
                    validC = true;
                } else {
                    System.out.println("Buscando...");
                    setA(getA()+1);
                }
            }
            if (!validC) {
                System.out.println("lo sentimos no se encontro el usuario");
            }else {
                while (!validP) {
                    for (int i = 3; i > 0; i--) {
                        System.out.println("Le quedan : " + i + " intentos");
                        System.out.println("Digite su contraseña: ");
                        setContrasena(sc.next());
                        if (getContrasena().equals(getAdminList().get(getA()).getContrasenaAdmin())) {
                            System.out.println("Iniciando...");
                            i = 0;
                            validP = true;
                        }
                    }
                    if(!validP){
                        System.out.println("lo sentimos alcanzaste el maximo de intentos");
                        break;

                    }
                }
            }
        }
        setValid(validP);

    }
}
