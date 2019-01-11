import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FileOpsTestCase {
    private static final String READ_XLS_FILE = "resources/read_xls_test.xls";
    private static final String XLS_FILE = "src/test/temp_files/test_file.xls";
    private static final String[] ROW_1 = {"One", "Two", "Three"};
    private static final String[] ROW_2 = {"1.0", "2.0", "3.0"};
    private final static Logger log = LogManager.getLogger( FileOpsTestCase.class );

    @Test
    public void testReadFromXls() {
        log.info( "TEST_06: testReadFromXls" );
        List<Object[]> objects = FileOperations.readXLS(READ_XLS_FILE);
        int recordsAmount = ROW_1.length;
        for ( int i = 0; i < recordsAmount; i++ ) {
            assertEquals( ROW_1[i], objects.get( 0 )[i] );
            assertEquals( ROW_2[i], objects.get( 1 )[i] );
        }
    }

    @Test
    public void testAppendToXls() {
        log.info( "TEST_03: testAppendToXls" );
        if ( !new File(XLS_FILE).isFile() ) {
            FileOperations.writeXLS(new String[]{}, XLS_FILE);
        }
        String[] xlsRecords = new String[]{ "ABCDEFGH" };
        FileOperations.appendXLS( xlsRecords, XLS_FILE );
        List<Object[]> objects = FileOperations.readXLS( XLS_FILE );
        assertEquals( xlsRecords[xlsRecords.length - 1], objects.get( objects.size() - 1 )[0] );
    }

    @Test
    public void testWriteToXls() {
        log.info( "TEST_04: testWriteToXls" );
        String[] xlsRecords = { "Test1", "100", "" };
        FileOperations.writeXLS( xlsRecords, XLS_FILE );
        List<Object[]> objects = FileOperations.readXLS( XLS_FILE );
        int recordsAmount = xlsRecords.length;
        for ( int i = 0; i < recordsAmount; i++ ) {
            assertEquals( xlsRecords[i], objects.get( i )[0] );
        }
    }
}
