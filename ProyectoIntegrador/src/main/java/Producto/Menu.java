package Producto;

import helpers.ValidacionOpciones;

import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    private String rpta1,rpta2,rpta3,rpta4;
    public Menu(){

    }

    public Menu(String rpta1, String rpta2, String rpta3,String rpta4) {
        this.rpta1 = rpta1;
        this.rpta2 = rpta2;
        this.rpta3 = rpta3;
        this.rpta4 = rpta4;
    }

    public String getRpta1() {
        return rpta1;
    }

    public void setRpta1(String rpta1) {
        this.rpta1 = rpta1;
    }

    public String getRpta2() {
        return rpta2;
    }

    public void setRpta2(String rpta2) {
        this.rpta2 = rpta2;
    }

    public String getRpta3() {
        return rpta3;
    }

    public void setRpta3(String rpta3) {
        this.rpta3 = rpta3;
    }

    public String getRpta4() {
        return rpta4;
    }

    public void setRpta4(String rpta4) {
        this.rpta4 = rpta4;
    }

    public void menuPrincipal() {
        setRpta1(null);
        while(getRpta1() == null) {
            System.out.println("*********Mustic*********");
            System.out.println("Menu de inicio");
            System.out.println("Por favor ingrese una opcion: ");
            System.out.println("1.-Registrar Usuario");
            System.out.println("2.-Iniciar sesion");
            System.out.println("3.-Ver artista's");
            System.out.println("4.-Salir");
            System.out.println("5.-Artista's");
            String rpta1 = sc.nextLine();
            if (ValidacionOpciones.validaopc5(rpta1)) {
                setRpta1(rpta1);
            } else {
                System.out.println("Digite una opcion valida");
            }
        }
    }
    public void menuUsuario(){
       setRpta2(null);
        while(getRpta2() == null) {
            System.out.println("*********Mustic*********");
            System.out.println("1.-Ver publicaciones");
            System.out.println("2.-Ver perfil del artista");
            System.out.println("3.-Ver mi perfil");
            System.out.println("4.-Editar mi perfil");
            System.out.println("5.-Eliminar perfil");
            System.out.println("6.-Salir");
            String rpta2 = sc.nextLine();
            if (ValidacionOpciones.validaopc6(rpta2)){
                setRpta2(rpta2);
            }else{
                System.out.println("Ingrese una opcion valida");
            }
        }
    }
    public void menuInicioArtista(){
        setRpta3(null);
        while (getRpta3() == null) {
            System.out.println("*********Mustic*********");
            System.out.println("1.-Crear perfil de artista");
            System.out.println("2.-Iniciar Sesion");
            String rpta3 = sc.nextLine();
            if (ValidacionOpciones.validaopc2(rpta3)){
                setRpta3(rpta3);
            }else{
                System.out.println("Ingrese una opcion valida");
            }
        }
    }

    public void menuArtista(){
        setRpta4(null);
        while(getRpta4() == null) {
            System.out.println("*********Mustic*********");
            System.out.println("1.-Crear Publicacion");
            System.out.println("2.-Modificar Publicacion");
            System.out.println("3.-Ver Publicacion");
            System.out.println("4.-Eliminar Publicacion");
            System.out.println("5.-Ver mi perfil");
            System.out.println("6.-Modificar mi perfil");
            System.out.println("7.-Eliminar Perfil");
            System.out.println("8.-salir");
            String rpta4 = sc.nextLine();
            if (ValidacionOpciones.validaopc8(rpta4)) {
                setRpta4(rpta4);
            } else {
                System.out.println("Digite una opcion valida");
            }
        }
    }

}
