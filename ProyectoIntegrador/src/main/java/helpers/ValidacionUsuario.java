package helpers;

import java.util.regex.Pattern;

public class ValidacionUsuario {
    private static final String ID_REGEX = "[0-9]{1,20}";
    private static final String NAME_REGEX = "[a-zA-Záéíóú]+";
    private static final String TYPE_REGEX = "[1-4]";
    private static final String TEL_REGEX = "[0-9]{10}";
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9._%-]+)@([a-zA-z0-9.-]+).([a-zA-Z]{2,6})$";
    private static final Pattern namePattern = Pattern.compile(NAME_REGEX);
    private static final Pattern idPattern = Pattern.compile(ID_REGEX);
    private static final Pattern typePattern = Pattern.compile(TYPE_REGEX);
    private static final Pattern telPattern = Pattern.compile(TEL_REGEX);
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    public static boolean validarId(String id) {
    return id.matches(ID_REGEX);
    }
    public static boolean validarNombre(String nombre) {
        return nombre.matches(NAME_REGEX);
    }
    public static boolean validarApellido(String apellido){
        return apellido.matches(NAME_REGEX);
    }
    public static boolean validarTipoDoc(String tipoDocumento){
        return tipoDocumento.matches(TYPE_REGEX);
    }
    public static boolean validarTel(String tel){
        return tel.matches(TEL_REGEX);
    }
    public static boolean validarEmail(String mail){
        return mail.matches(EMAIL_REGEX);
    }
}
