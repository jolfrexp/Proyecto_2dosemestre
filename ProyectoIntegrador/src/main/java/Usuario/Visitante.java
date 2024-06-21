package Usuario;
import Controladores.VisitanteDAO;
import helpers.ValidacionOpciones;
import helpers.ValidacionPerfil;
import helpers.ValidacionUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Visitante extends Usuario{
    List<Visitante> visitList = new ArrayList<>();
    private Administrador administrador;
    private int l;
    private String email_Usuario, contrasena;
    private String usuarioVisit, contrasenaVisit;
    private boolean valid = false;
    private int codVist = 0;
    public Visitante(){

    }
    public Visitante(String nombre, String apellido,String tipoDocumento, String id, String telefono, String correo,String usuarioVisit, String contrasenaVisit,Integer codVist) {
        super( nombre, apellido, tipoDocumento, id, telefono, correo);
        this.usuarioVisit = usuarioVisit;
        this.contrasenaVisit = contrasenaVisit;
        this.codVist = codVist;
    }

    public String getUsuarioVisit() {
        return usuarioVisit;
    }

    public void setUsuarioVisit(String usuarioVisit) {
        this.usuarioVisit = usuarioVisit;
    }

    public String getContrasenaVisit() {
        return contrasenaVisit;
    }

    public void setContrasenaVisit(String contrasenaVisit) {
        this.contrasenaVisit = contrasenaVisit;
    }


    public int getCodVist() {
        return codVist;
    }

    public void setCodVist(int codVist) {
        this.codVist = codVist;
    }

    public List<Visitante> getVisitList() {
        return visitList;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
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

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public void crearUsuario(){
        setUsuarioVisit(null);
        setContrasenaVisit(null);
        super.crearUsuario(getAdministrador(),visitante);
        while(getUsuarioVisit() == null) {
            System.out.println("Usuario Visitante: ");
            String usuarioVisit = sc.nextLine();
            if (ValidacionPerfil.validaUsuario(usuarioVisit)){
                setUsuarioVisit(usuarioVisit);
            }else{
                System.out.println("Ingrese un Usuario valido");
            }
        }
        while(getContrasenaVisit() == null){
            System.out.println("Contraseña Visitante: ");
            String contrasenaVisit = sc.nextLine();
            if (ValidacionPerfil.validaContrasena(contrasenaVisit)){
                System.out.println("Confirme la contraseña");
                String contrasenaVisit2 = sc.nextLine();
                if ((ValidacionPerfil.validaContrasena(contrasenaVisit2))&& (contrasenaVisit.equals(contrasenaVisit2))) {
                    setContrasenaVisit(contrasenaVisit);
                }else{
                    System.out.println("Las contraseñas no coinciden");
                }
            }else{
                System.out.println("Ingrese una contraseña valida");
            }
        }
        System.out.println(getId());
        setCodVist((getCodVist() + 1));
        System.out.println("Su codigo de usuario es: "+getCodVist());
        visitList.add(new Visitante(getNombre(), getApellido(),getTipoDocumento(), getId(), getTelefono(), getCorreo(), getUsuarioVisit(), getContrasenaVisit(),getCodVist()));
    }
    @Override
    public void seleccionarUsuario(){
        if (!visitList.isEmpty()){
            setId(visitList.get(getL()).getId());
            setNombre(visitList.get(getL()).getNombre());
            setApellido(visitList.get(getL()).getApellido());
            setTipoDocumento(visitList.get(getL()).getTipoDocumento());
            setTelefono(visitList.get(getL()).getTelefono());
            setCorreo(visitList.get(getL()).getCorreo());
            setUsuarioVisit(visitList.get(getL()).getUsuarioVisit());
            setContrasenaVisit(visitList.get(getL()).getContrasenaVisit());
        }
        super.seleccionarUsuario();
        if (getId() != null) {
            System.out.println("Usuario Visit: " + getUsuarioVisit());
            System.out.println("Contraseña Visit: ******");
        }else{
            System.out.println("No hay usuarios registrados:(");
        }
    }
    @Override
    public void modificarUsuario(){
        if (!visitList.isEmpty()){
            setId(visitList.get(getL()).getId());
            setNombre(visitList.get(getL()).getNombre());
            setApellido(visitList.get(getL()).getApellido());
            setTipoDocumento(visitList.get(getL()).getTipoDocumento());
            setTelefono(visitList.get(getL()).getTelefono());
            setCorreo(visitList.get(getL()).getCorreo());
            setUsuarioVisit(visitList.get(getL()).getUsuarioVisit());
            setContrasenaVisit(visitList.get(getL()).getContrasenaVisit());
        }
        String salir = "no";
        while(salir.equals("no")) {
            super.modificarUsuario();
            setUsuarioVisit(null);
            setContrasenaVisit(null);
            if (!visitList.isEmpty()) {
                String rpt = sc.nextLine();
                if (ValidacionOpciones.validaopc7(rpt)) {
                    switch (rpt) {
                        case "1":
                            System.out.println("Nombre anterior: " + visitList.get(getL()).getNombre());
                            while (getNombre() == null) {
                                System.out.println("Ingrese el nuevo nombre: ");
                                String nombre = sc.nextLine();
                                if (ValidacionUsuario.validarNombre(nombre)) {
                                    System.out.println("Nombre anterior : " + visitList.get(getL()).getNombre());
                                    System.out.println("Nombre nuevo: " + nombre);
                                    System.out.println("Guardar (si/no)");
                                    String rpt1 = sc.nextLine();
                                    if (Objects.equals(rpt1, "si")) {
                                        visitList.get(getL()).setNombre(nombre);
                                        setNombre(nombre);
                                        VisitanteDAO.modificarVisit(visitList.get(getL()).getId(),"nom_visitante",nombre);
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
                            System.out.println("Apellido anterior: " + visitList.get(getL()).getApellido());
                            while (getApellido() == null) {
                                System.out.println("Ingrese el nuevo apellido: ");
                                String apellido = sc.nextLine();
                                if (ValidacionUsuario.validarApellido(apellido)) {
                                    System.out.println("Apellido anterior : " + visitList.get(getL()).getApellido());
                                    System.out.println("Apellido nuevo: " + apellido);
                                    System.out.println("Guardar (si/no)");
                                    String rpt1 = sc.nextLine();
                                    if (Objects.equals(rpt1, "si")) {
                                        visitList.get(getL()).setApellido(apellido);
                                        System.out.println(visitList.get(getL()).getId());
                                        setApellido(apellido);
                                        VisitanteDAO.modificarVisit((visitList.get(getL()).getId()),"ape_visitante",apellido);
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
                            System.out.println("Telefono anterior: " + visitList.get(getL()).getTelefono());
                            while (getTelefono() == null) {
                                System.out.println("Ingrese el nuevo telefono: ");
                                String telefono = sc.nextLine();
                                if (ValidacionUsuario.validarTel(telefono)) {
                                    System.out.println("Nombre anterior : " + visitList.get(getL()).getTelefono());
                                    System.out.println("Nombre nuevo: " + telefono);
                                    System.out.println("Guardar (si/no)");
                                    String rpt1 = sc.nextLine();
                                    if (Objects.equals(rpt1, "si")) {
                                        visitList.get(getL()).setTelefono(telefono);
                                        setTelefono(telefono);
                                        VisitanteDAO.modificarVisit(visitList.get(getL()).getId(),"tel_visit",telefono);
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
                            System.out.println("Correo anterior: " + visitList.get(getL()).getCorreo());
                            while (getCorreo() == null) {
                                System.out.println("Ingrese el nuevo correo: ");
                                String correo = sc.nextLine();
                                if (ValidacionUsuario.validarEmail(correo)) {
                                    System.out.println("Nombre anterior : " + visitList.get(getL()).getCorreo());
                                    System.out.println("Nombre nuevo: " + correo);
                                    System.out.println("Guardar (si/no)");
                                    String rpt1 = sc.nextLine();
                                    if (Objects.equals(rpt1, "si")) {
                                        visitList.get(getL()).setCorreo(correo);
                                        setCorreo(correo);
                                        VisitanteDAO.modificarVisit(visitList.get(getL()).getId(),"correo_visit",correo);
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
                            System.out.println("Usuario anterior: " + visitList.get(getL()).getUsuarioVisit());
                            while (getUsuarioVisit() == null) {
                                System.out.println("Ingrese el nuevo Usuario: ");
                                String Usuario = sc.nextLine();
                                if (ValidacionPerfil.validaUsuario(Usuario)) {
                                    System.out.println("Usuario anterior : " + visitList.get(getL()).getUsuarioVisit());
                                    System.out.println("Usuario nuevo: " + Usuario);
                                    System.out.println("Guardar (si/no)");
                                    String rpt1 = sc.nextLine();
                                    if (Objects.equals(rpt1, "si")) {
                                        visitList.get(getL()).setUsuarioVisit(Usuario);
                                        setUsuarioVisit(Usuario);
                                        VisitanteDAO.modificarVisit(visitList.get(getL()).getId(),"usuario_visit",Usuario);
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
                            while (getContrasenaVisit() == null) {
                                System.out.println("Ingresa la contraseña anterior");
                                String cont1 = sc.nextLine();
                                if (Objects.equals(cont1, visitList.get(getL()).getContrasenaVisit())) {
                                    System.out.println("Ingrese la nueva contraseña: ");
                                    String cont2 = sc.nextLine();
                                    if (ValidacionPerfil.validaContrasena(cont2)) {
                                        System.out.println("confirme la contraseña");
                                        String cont3 = sc.nextLine();
                                        if (ValidacionPerfil.validaContrasena(cont3) && cont2.equals(cont3)) {
                                            visitList.get(getL()).setContrasenaVisit(cont3);
                                            setContrasenaVisit(cont3);
                                            VisitanteDAO.modificarVisit(visitList.get(getL()).getId(),"cont_visit",cont3);
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
                System.out.println("No hay usuarios registrados:(");
            }
        }
    }
    @Override
    public void eliminarUsuario(){
        if (!visitList.isEmpty()){
            setId(visitList.get(getL()).getId());
            setNombre(visitList.get(getL()).getNombre());
            setApellido(visitList.get(getL()).getApellido());
            setTipoDocumento(visitList.get(getL()).getTipoDocumento());
            setTelefono(visitList.get(getL()).getTelefono());
            setCorreo(visitList.get(getL()).getCorreo());
            setUsuarioVisit(visitList.get(getL()).getUsuarioVisit());
            setContrasenaVisit(visitList.get(getL()).getContrasenaVisit());
        }
        super.eliminarUsuario();
        if (getId() != null){
            if (isRpta()) {
                VisitanteDAO.eliminarVisit(visitList.get(getL()).getId());
                visitList.remove(getL());
                System.out.println("Usuario eliminado con exito!");
            }else {
                System.out.println("Eliminancion de usuario cancelado");
            }
        }else {
            System.out.println("No hay Usuarios registrados:(");
        }
    }
    public void loginVisit(){
        System.out.println("******************* Inicio de sesion *******************");
        boolean validC = false;
        boolean validP = false;
        setValid(!isValid());
        while (!validC && !visitList.isEmpty()) {
            System.out.println("Ingrese su Usuario: ");
            setEmail_Usuario(sc.next());
            setL(0);
            while (getL() < getVisitList().size() && !validC ) {
                if (getEmail_Usuario().equals(getVisitList().get(getL()).getUsuarioVisit())) {
                    System.out.println("Usuario Encontrado : "+getVisitList().get(getL()).getUsuarioVisit());
                    validC = true;
                } else {
                    setL(getL()+1);
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
                        if (getContrasena().equals(getVisitList().get(getL()).getContrasenaVisit())) {
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
