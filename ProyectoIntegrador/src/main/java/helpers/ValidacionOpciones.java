package helpers;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ValidacionOpciones {
    private static final String OPC2_REGEX = "[1-2]";
    private static final String OPC5_REGEX = "[1-5]";
    private static final String OPC6_REGEX = "[1-6]";
    private static final String OPC7_REGEX = "[1-7]";
    private static final String OPC8_REGEX = "[1-8]";
    private static final String OPCINF_REGEX = "[0-9]+";
    private static final Pattern opc2Pattern = Pattern.compile(OPC2_REGEX);
    private static final Pattern opc5Pattern = Pattern.compile(OPC5_REGEX);
    private static final Pattern opc6Pattern = Pattern.compile(OPC6_REGEX);
    private static final Pattern opc7Pattern = Pattern.compile(OPC7_REGEX);
    private static final Pattern opc8Pattern = Pattern.compile(OPC8_REGEX);
    private static final Pattern opcinf = Pattern.compile(OPCINF_REGEX);
    public static boolean validaopc2(String opc){
        return opc.matches(OPC2_REGEX);
    }
    public static boolean validaopc5(String opc){
        return opc.matches(OPC5_REGEX);
    }
    public static boolean validaopc6(String opc){
        return opc.matches(OPC6_REGEX);
    }
    public static boolean validaopc7(String opc){return opc.matches(OPC7_REGEX);}
    public static boolean validaopc8(String opc){return opc.matches(OPC8_REGEX);}
    public static boolean validaopcinf(String opc){
        return opc.matches(OPCINF_REGEX);
    }
}
