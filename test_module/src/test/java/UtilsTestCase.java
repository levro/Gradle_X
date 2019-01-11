import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


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
        log.info( "TEST_01: testSumStrings" );
        assertEquals( "150", Utils.sumOfTwoStrings( "100", "50") );
    }

    @Test
    public void testSumStringsNeg() {
        log.info( "TEST_02: testSumStringsNeg" );
        assertEquals( "0", Utils.sumOfTwoStrings( "seven", "50") );
    }

    @Test
    public void testOOBException() {
        log.info( "TEST_05: testOOBException" );
        String[] arr = new String[]{ "uhb", "tfc"};
        int index = 4;
        try {
            Utils.getElementByIndex( arr, index );
        } catch ( ArrayIndexOutOfBoundsException e ) {
            assertEquals( "java.lang.ArrayIndexOutOfBoundsException: " + index, e.toString());
        }
    }

    @Test (expectedExceptions = IllegalCharacter.class)
    public void testCheckStringAException() throws IllegalCharacter {
        StrUtilsExcept.checkString( " .a" );
    }

    @Test (expectedExceptions = IllegalCharacter.class)
    public void testCheckStringBException() throws IllegalCharacter {
        StrUtilsExcept.checkString( "00b" );
    }

    @Test
    public void testCheckStringPassed() throws IllegalCharacter {
        assertEquals( 6, StrUtilsExcept.checkString( "Yrtgo.") );
        assertEquals( 20, StrUtilsExcept.checkString( "Hdjt hgr 5 ooet, ut!") );
    }
}
