import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

    private static final Level epicFail = Level.forName("EPIC.FAIL!", 50);
    private final static Logger log = LogManager.getLogger( Utils.class);

    public static String sumOfTwoStrings (String str1, String str2) {
        long result = 0;
        try {
            result = Long.parseLong( str1 ) + Long.parseLong( str2 );
        } catch ( NumberFormatException e ) {
            log.log(epicFail, "This is not numbers!");
        }
        return String.valueOf( result );
    }

    public static String getElementByIndex( String[] strs, int index) {
        return strs[index];

    }
}
