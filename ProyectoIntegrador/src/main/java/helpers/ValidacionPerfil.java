package helpers;

import java.util.regex.Pattern;

public class ValidacionPerfil {
    public static final String USUARIO_REGEX = "\\w+";
    public static final String CONTRASENA_REGEX = "\\w+";
    public static final Pattern usuarioPattern = Pattern.compile(USUARIO_REGEX);
    public static final Pattern contrasenaPattern = Pattern.compile(CONTRASENA_REGEX);

    public static boolean validaUsuario(String usuario){
        return usuario.matches(USUARIO_REGEX);
    }
    public static boolean validaContrasena(String contrasena){
        return contrasena.matches(CONTRASENA_REGEX);
    }
}
