import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;


public class UtilsTestCase {
    private static final Level mediumFail = Level.forName( "50/50FAIL", 25);
    private final static Logger log = LogManager.getLogger( UtilsTestCase.class );

    @Test
    public void testLogger() {
        log.info("Test log levels");
        log.trace("This is a trace message");
        log.debug("This is a debug message");
        log.info("This is an info message");
        log.log( mediumFail, "This is a medium message" );
        log.warn("This is a warn message");
        log.error("This is an error message");
        log.fatal("This is a fatal message");
    }

    @Test
    public void testSumStrings() {
        log.error( "TEST_01: testSumStrings" );
        assertEquals( "150", Utils.sumOfTwoStrings( "100", "50") );
    }

    @Test
    public void testSumStringsNeg() {
        log.error( "TEST_02: testSumStringsNeg" );
        assertEquals( "0", Utils.sumOfTwoStrings( "seven", "50") );
    }

    @Test
    public void testOOBException() {
        log.error( "TEST_05: testOOBException" );
        String[] arr = new String[]{ "uhb", "tfc"};
        int index = 4;
        try {
            Utils.getElementByIndex( arr, index );
        } catch ( ArrayIndexOutOfBoundsException e ) {
            assertEquals( "java.lang.ArrayIndexOutOfBoundsException: " + index, e.toString());
        }
    }

    @Test
    public void testCustomException(){
        try {
            StrUtilsExcept.checkString( "iiiaooo");
        } catch ( IllegalCharacter e ) {
            assertEquals( "IllegalCharacter: 'a' is not allowed.", e.toString() );
        }

        try {
            StrUtilsExcept.checkString( "tttbuuu");
        } catch ( IllegalCharacter e ) {
            assertEquals( "IllegalCharacter: 'b' is not allowed.", e.toString() );
        }
    }
}
