import java.util.ArrayList;
import java.util.StringTokenizer;

public class Extension {
    // para saber si una cadena es double
    public static Boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException numberFormat){
            return false;
        }
    }

    // para convertir una cadena a double
    public static double toDouble(String str) {
        return Double.parseDouble(str);
    }

}