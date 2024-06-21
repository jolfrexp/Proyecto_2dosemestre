package Producto;

import Controladores.PublicacionDAO;
import Usuario.Administrador;
import Usuario.Visitante;
import helpers.ValidacionOpciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Publicacion {
    List<Publicacion> publicacionList = new ArrayList<>();
    List<Publicacion> ComentList = new ArrayList<>();
    private Administrador administrador = new Administrador();
    private Visitante visitante = new Visitante();
    Scanner sc = new Scanner(System.in);
    private int idPublicacion = 0,likePublic=0, contadorComent=0,p;
    private String tituloPublic,descripPublic,comentPublic,usuario = administrador.getUsuarioAdmin();
    private boolean rpta;
    public Publicacion(){

    }

    public Publicacion(int idPublicacion,String usuario,String tituloPublic,String descripPublic,int likePublic, int contadorComent){
        this.idPublicacion = idPublicacion;
        this.usuario = usuario;
        this.tituloPublic = tituloPublic;
        this.descripPublic = descripPublic;
        this.likePublic = likePublic;
        this.contadorComent = contadorComent;
    }
    public Publicacion(int idPublicacion, String idVisitante, String comentPublic){
        this.idPublicacion = idPublicacion;
        this.visitante.setId(idVisitante);
        this.comentPublic = comentPublic;
    }
    public String getTituloPublic() {
        return tituloPublic;
    }
    public void setTituloPublic(String tituloPublic) {
        this.tituloPublic = tituloPublic;
    }
    public String getDescripPublic() {
        return descripPublic;
    }
    public void setDescripPublic(String descripPublic) {
        this.descripPublic = descripPublic;
    }
    public int getIdPublicacion() {
        return idPublicacion;
    }
    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
    public int getLikePublic() {
        return likePublic;
    }
    public void setLikePublic(int likePublic) {
        this.likePublic = likePublic;
    }
    public String getComentPublic() {
        return comentPublic;
    }
    public void setComentPublic(String comentPublic) {
        this.comentPublic = comentPublic;
    }
    public int getContadorComent() {
        return contadorComent;
    }
    public void setContadorComent(int contadorComent) {
        this.contadorComent = contadorComent;
    }
    public List<Publicacion> getPublicacionList() {
        return publicacionList;
    }
    public int getP() {
        return p;
    }
    public void setP(int p) {
        this.p = p;
    }
    public boolean isRpta() {
        return rpta;
    }
    public void setRpta(boolean rpta) {
        this.rpta = rpta;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }


    public void crearPublicacion(){
        setIdPublicacion(publicacionList.size()+1);
        System.out.println("Id de la Publicacion: "+getIdPublicacion());
        System.out.println("Usuario: "+ getAdministrador().getAdminList().get(administrador.getA()).getUsuarioAdmin());
        setUsuario(getAdministrador().getAdminList().get(administrador.getA()).getId());
        System.out.println("Titulo: ");
        setTituloPublic(sc.nextLine());
        System.out.println("Foto/video (Coming soon!)");
        System.out.println("Descripcion: ");
        setDescripPublic(sc.nextLine());
        publicacionList.add(new Publicacion(getIdPublicacion(),getUsuario(),getTituloPublic(),getDescripPublic(),getLikePublic(), getContadorComent()));
    }
    public void seleccionarPublicacion(){
        setP(0);
        if (!publicacionList.isEmpty()) {
            while(getP()<publicacionList.size()){
                System.out.println("Id de la Publicacion: "+publicacionList.get(getP()).getIdPublicacion());
                System.out.println("Usuario: " + publicacionList.get(getP()).getUsuario());
                System.out.println("Titulo: " + publicacionList.get(getP()).getTituloPublic());
                System.out.println("Foto/video (Coming soon!)");
                System.out.println("Descripcion: " + publicacionList.get(getP()).getDescripPublic());
                System.out.println("A " + publicacionList.get(getP()).getLikePublic() + " Persona's le a gustado esta Publicacion");
                System.out.println(publicacionList.get(getP()).getContadorComent() + "Persona's han Comentado");
                likePublicacion();
                comentarPublicacion();
                if (publicacionList.get(getP()).getComentPublic() == null) {
                    System.out.println("No hay comentarios que mostrar");
                } else {
                    System.out.println("Ver comentarios? (si/no)");
                    String rpt = sc.nextLine();

                    setRpta(Objects.equals(rpt, "si"));

                    if (rpta) {
                        System.out.println("******Comentarios*******");
                        PublicacionDAO.obtComent(publicacionList.get(getP()).getIdPublicacion()+"");
                    }
                }
                setP(getP()+1);
            }
        }else{
            System.out.println("No se encontraron publicaciones");
        }
    }
    public void modificarPublicacion(){
        if(!publicacionList.isEmpty()){
        for (int i = 0; i < publicacionList.size(); i++){
            System.out.println((i+1)+".-"+publicacionList.get(i).getTituloPublic());
        }
        setRpta(false);
        while(!isRpta()){
            System.out.println("Ingresa cual quieres modificar");
            String rpta = sc.nextLine();
            setRpta(ValidacionOpciones.validaopcinf(rpta));
            if (isRpta()){
                if (Integer.parseInt(rpta)> publicacionList.size()){
                    System.out.println("digite una opcion valida");
                }else{
                    int rpt = Integer.parseInt(rpta);
                    System.out.println("Publicacion selecionada: "+publicacionList.get(rpt-1).getTituloPublic());
                    System.out.println("Que desea modificar:\n1.-Titulo\n2.-Media\n3.-Descripcion\n4.-Cancelar");
                    switch (sc.nextLine()){
                        case "1":
                            System.out.println("Ingrese el nuevo titulo: ");
                            String tituloPublic = sc.nextLine();
                            System.out.println("Titulo anterior: "+publicacionList.get((rpt-1)).getTituloPublic());
                            System.out.println("Titulo nuevo: "+tituloPublic);
                            System.out.println("Guardar? (si/no)");
                            String rpt1 = sc.nextLine();
                            if (Objects.equals(rpt1, "si")){
                                publicacionList.get((rpt-1)).setTituloPublic(tituloPublic);
                                PublicacionDAO.modificarPublic(Integer.toString(publicacionList.get(rpt-1).getIdPublicacion()),"titulo",tituloPublic);
                                System.out.println("Se ha actualizado la publicacion!");
                            }else{
                                System.out.println("Se a cancelado la modificacion");
                            }
                            break;
                        case"2":
                            System.out.println("Coming soon!");
                            break;
                        case"3":
                            System.out.println("Descripcion anterior: "+ publicacionList.get((rpt-1)).getDescripPublic());
                            System.out.println("Ingrese la nueva descripcion: ");
                            String descripPublic = sc.nextLine();
                            System.out.println("Titulo anterior: "+publicacionList.get((rpt-1)).getDescripPublic());
                            System.out.println("Titulo nuevo: "+descripPublic);
                            System.out.println("Guardar? (si/no)");
                            String rpt2 = sc.nextLine();
                            if (Objects.equals(rpt2, "si")){
                                publicacionList.get((rpt-1)).setDescripPublic(descripPublic);
                                PublicacionDAO.modificarPublic(Integer.toString(publicacionList.get(rpt-1).getIdPublicacion()),"descripcion",descripPublic);
                            }else{
                                System.out.println("Se ha cancelado la modificacion");
                            }
                                break;
                            case "4":
                                System.out.println("Se ha cancelado la modificacion...");
                                break;
                        }
                    }
                }else{
                System.out.println("Digite una opcion valida");
                }
            }
        }else{
            System.out.println("No se encontraron publicaciones");
        }
    }
    public void eliminarPublicacion(){
        System.out.println("Desea eliminar la Publicacion: (si/no)");
        String rpt = sc.nextLine();
        setRpta(Objects.equals(rpt, "si"));
        if (isRpta()){
            PublicacionDAO.eliminarPublic(publicacionList.get(getP()).getIdPublicacion()+"");
            publicacionList.remove(getIdPublicacion());
            System.out.println("Publicacion eliminada con exito");
        } else{
            System.out.println("Eliminancion de publicacion cancelada");
        }
    }
    public void likePublicacion(){
        System.out.println("Te gusto la publicacion?: (si/no)");
        String rpt = sc.nextLine();
        setRpta(Objects.equals(rpt, "si"));
        if (isRpta()){
            if (!visitante.getVisitList().isEmpty()){
            publicacionList.get(getP()).setLikePublic((publicacionList.get(getP()).getLikePublic()+ 1));
            PublicacionDAO.likes((publicacionList.get(getP()).idPublicacion+""), getVisitante().getVisitList().get(visitante.getL()).getId());
            System.out.println((publicacionList.get(getP()).getLikePublic()+ 1));
            PublicacionDAO.modificarPublicN(publicacionList.get(getP()).getIdPublicacion()+"","likes",publicacionList.get(getP()).getLikePublic());
            } else if (!administrador.getAdminList().isEmpty()) {
                System.out.println("El admin no tiene permitido dar like");
            }
        } else{
            System.out.println("Ok, te apareceran menos publicaciones como esta!");//coming soon
        }
    }
    public void comentarPublicacion(){
        System.out.println("Deseas comentar la publicacion?: (si/no)");
        String rpt = sc.nextLine();
        setRpta(Objects.equals(rpt, "si"));
        if (isRpta()){
            publicacionList.get(getP()).setContadorComent(publicacionList.get(getP()).getContadorComent()+1);
            System.out.println("Ingresa tu comentario");
            String coment = sc.nextLine();
            publicacionList.get(getP()).setComentPublic(coment);
            ComentList.add(new Publicacion((publicacionList.get(getP()).getIdPublicacion()),(getVisitante().getVisitList().get(visitante.getL()).getId()),(publicacionList.get(getP()).getComentPublic())));
            PublicacionDAO.comentarios(ComentList);
        }
    }
}
