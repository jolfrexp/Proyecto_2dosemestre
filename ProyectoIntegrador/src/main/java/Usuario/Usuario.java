package Usuario;

import enums.Confirmacion;
import enums.Identificacion;
import helpers.ValidacionUsuario;


import java.util.Scanner;

public abstract class Usuario implements UntilUsuario{
    Administrador administrador;
    Visitante visitante;
    Scanner sc = new Scanner(System.in);
    private String tipoDocumento,nombre, apellido, telefono, correo, id;
    private boolean rpta = false;
    //constructores
    public Usuario() {

    }

    public Usuario(String nombre, String apellido, String tipoDocumento, String id, String telefono, String correo) {
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.id = id;
    }

//Encapsulamiento

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public boolean isRpta() {
        return rpta;
    }

    public void setRpta(boolean rpta) {
        this.rpta = rpta;
    }
    //metodos
    public void crearUsuario(Administrador administrador,Visitante visitante){
        setNombre(null);
        setApellido(null);
        setTipoDocumento(null);//limpieza de datos para que funcionen los ciclos while
        setId(null);
        setTelefono(null);
        setCorreo(null);
        while(getNombre() == null){
            System.out.println("Nombre: ");
            String nombre = sc.nextLine();
            if (ValidacionUsuario.validarNombre(nombre)){
                setNombre(nombre);
            }else {
                System.out.println("Ingrese un nombre valido");
            }
        }
        while(getApellido() == null){
            System.out.println("Apellido: ");
            String apellido = sc.nextLine();
            if (ValidacionUsuario.validarApellido(apellido)){
                setApellido(apellido);
            }else {
                System.out.println("Ingrese un apellido valido");
            }
        }
        while(getTipoDocumento() == null){
            System.out.println("Tipo de documento:\n1.- Cedula de ciudadania\n2.-Tarjeta de identidad\n3.-Cedula de extranjeria\n4.-PPT");
            String rpt = sc.nextLine();
            if (ValidacionUsuario.validarTipoDoc(rpt)){
                setTipoDocumento(Identificacion.values()[Integer.parseInt(rpt)-1]+"");
            }else{
                System.out.println("Seleccione una opcion valida");
            }
        }while (getId() == null){
            System.out.println("Nro-Identificacion: ");
            String id = sc.nextLine();
            if (ValidacionUsuario.validarId(id)){
                if (administrador.getAdminList().isEmpty() &&  administrador.getVisitante().getVisitList().isEmpty()) {
                    setId(id);
                    System.out.println("Tu id es: " + getId());
                }else{
                    int i = 0;
                    int j = 0;
                    boolean valid = false;
                    while (i < administrador.getAdminList().size() && !valid) {
                        if (id.equals(administrador.getAdminList().get(i).getId())) {
                            System.out.println("Un usuario ya contiene el id: " + administrador.getAdminList().get(i).getUsuarioAdmin());
                            i = administrador.getAdminList().size();
                            valid = true;
                        } else {
                            i++;
                        }
                    }
                    while ((j < administrador.getVisitante().getVisitList().size()) && !valid) {
                        if (id.equals(administrador.getVisitante().getVisitList().get(j).getId())) {
                            System.out.println("Un usuario ya contiene el id: " + administrador.getVisitante().getVisitList().get(j).getUsuarioVisit());
                            j = administrador.getVisitante().getVisitList().size();
                            valid = true;
                        } else {
                            j++;
                        }

                    }
                    if (i == administrador.getAdminList().size() && j== administrador.getVisitante().getVisitList().size() && !valid) {
                        setId(id);
                        System.out.println("Tu id es: " + getId());
                    }
                }
            }else {
                System.out.println("Ingrese un Id valido");
            }

        }while(getTelefono() == null){
            System.out.println("Telefono: ");
            String tel = sc.nextLine();
            if (ValidacionUsuario.validarTel(tel)){
                setTelefono(tel);
                System.out.println("Telefono registrado: "+getTelefono());
            }else {
                System.out.println("Numero de telefono no valido");
            }
        }while(getCorreo() == null){
            System.out.println("Correo: ");
            String mail = sc.nextLine();
            if (ValidacionUsuario.validarEmail(mail)){
                setCorreo(mail);
                System.out.println("Tu correo es : "+getCorreo());
            }else {
                System.out.println("Direccion de correo no encontrada!");
            }
        }


    }
    public void seleccionarUsuario(){
        if (getId() != null) {
            System.out.println("Id: " + getId());
            System.out.println("Nombre: " + getNombre());
            System.out.println("Apellido: " + getApellido());
            System.out.println("Telefono: " + getTelefono());
            System.out.println("Correo: " + getCorreo());
        }
    }
    public void modificarUsuario(){
            seleccionarUsuario();
            System.out.println("Que desea modificar:\n1.-Nombre\n2.-Apellido\n3.-Telefono\n4.-Correo\n5.-Usuario\n6.-Contraseña\n7.-Cancelar");
            setId(null);
            setNombre(null);
            setApellido(null);
            setTipoDocumento(null);
            setTelefono(null);
            setCorreo(null);
    }
    public void eliminarUsuario(){
        if (getId() != null) {
            System.out.println("Desea eliminar el siguiente Usuario:\n1.-si\n2.-no");
            System.out.println(getNombre()+" "+getApellido());
            String rpt = sc.next();
            System.out.println(Confirmacion.values()[(Integer.parseInt(rpt) - 1)] + "");
            setRpta((Confirmacion.values()[(Integer.parseInt(rpt) - 1)] + "").equals("SI"));
        }
    }
}
