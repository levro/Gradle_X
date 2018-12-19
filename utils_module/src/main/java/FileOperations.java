import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class FileOperations {
    private final static String PROPERTIES_FILE = "custom.properties";
    private final static Logger log = LogManager.getLogger( FileOperations.class );

    public static void writeXLS( String[] records, String fileName ) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet( "Records" );

        for ( int i = 0; i < records.length; i++ ) {
            Row row = sheet.createRow( i );
            Cell name = row.createCell( 0 );
            name.setCellValue( records[i] );
            Cell time = row.createCell( 1 );
            DataFormat format = book.createDataFormat();
            CellStyle dateStyle = book.createCellStyle();
            dateStyle.setDataFormat( format.getFormat( "dd.mm.yyyy HH:MM:SS" ) );
            time.setCellStyle( dateStyle );
            time.setCellValue( new Date() );
            sheet.autoSizeColumn( 0 );
            sheet.autoSizeColumn( 1 );
        }
        try {
            book.write( new FileOutputStream( fileName ) );
            book.close();
        } catch ( IOException e ) {
            log.error( "IOException: " + e );
        }
    }

    public static List<Object[]> readXLS( String fileName ) {
        List<Object[]> result = new ArrayList<>();
        Workbook workbook = null;
        FileInputStream excelInputStream = null;
        try {
            excelInputStream = new FileInputStream( new File( fileName ) );
            workbook = new HSSFWorkbook( excelInputStream );
        } catch ( FileNotFoundException e ) {
            log.error( "FNFException: " + e );
        } catch ( IOException e ) {
            log.error( "IOException: " + e );
        }
        Sheet sheet = workbook.getSheetAt( 0 );
        Iterator<Row> rowItr = sheet.iterator();
        while ( rowItr.hasNext() ) {
            Row row = rowItr.next();
            List<String> currentRow = new ArrayList<>();
            Iterator<Cell> cellItr = row.iterator();
            while ( cellItr.hasNext() ) {
                Cell cell = cellItr.next();
                if ( cell.getCellTypeEnum() == CellType.STRING ) {
                    currentRow.add( cell.getStringCellValue() );
                } else if ( HSSFDateUtil.isCellDateFormatted( cell ) ) {
                    currentRow.add( cell.getDateCellValue().toString() );
                } else if ( cell.getCellTypeEnum() == CellType.NUMERIC ) {
                    currentRow.add( String.valueOf( cell.getNumericCellValue() ) );
                }
            }
            result.add( currentRow.toArray() );
        }
        try {
            workbook.close();
            excelInputStream.close();
        } catch ( IOException e ) {
            log.error( "IOException: " + e );
        }
        return result;
    }

    public static void appendXLS( String[] xlsRecords, String fileName ) {
        FileInputStream excelInputStream;
        Workbook book = null;
        try {
            excelInputStream = new FileInputStream( new File( fileName ) );
            book = new HSSFWorkbook( excelInputStream );
        } catch ( FileNotFoundException e ) {
            log.error( "FNFException: " + e );
        } catch ( IOException e ) {
            log.error( "IOException: " + e );
        }
        Sheet sheet = book.getSheetAt( 0 );
        int startRow = sheet.getLastRowNum() + 1;
        for ( int i = 0; i < xlsRecords.length; i++ ) {
            Row row = sheet.createRow( i + startRow );
            Cell name = row.createCell( 0 );
            name.setCellValue( xlsRecords[i] );
            Cell time = row.createCell( 1 );
            DataFormat format = book.createDataFormat();
            CellStyle dateStyle = book.createCellStyle();
            dateStyle.setDataFormat( format.getFormat( "dd.mm.yyyy HH:MM:SS" ) );
            time.setCellStyle( dateStyle );
            time.setCellValue( new Date() );
        }
        try {
            book.write( new FileOutputStream( fileName ) );
            book.close();
        } catch ( IOException e ) {
            log.error( "IOException: " + e );
        }
    }

    public static void addPropertyToFile( String str ) {

        try {
            BufferedWriter writer = new BufferedWriter( new FileWriter( PROPERTIES_FILE, true ) );
            writer.append( "\n" );
            writer.append( str );
            writer.close();
        } catch ( IOException e ) {
            log.error( "IOException!!!!!" + e );
        }
    }

    static void modifyProperty( String key, String value ) {
        File fileToBeModified = new File( PROPERTIES_FILE );
        String newContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader( new FileReader( fileToBeModified ) );
            String line = reader.readLine();
            while ( line != null ) {
                if ( line.contains( key ) ) {
                    line = key + "=" + value;
                }
                newContent = newContent + line + System.lineSeparator();
                line = reader.readLine();
            }

            writer = new FileWriter( fileToBeModified );
            writer.write( newContent.substring( 0, newContent.length() - 2 ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }

    public static String readPropertyFromFile( String key ) {
        String result = null;
        try {
            Scanner scanner = new Scanner( new File( PROPERTIES_FILE ) );
            scanner.useDelimiter( "\n" );
            while ( scanner.hasNext() ) {
                String row = scanner.next();
                if ( row.startsWith( key ) ) {
                    result = row;
                }
            }
        } catch ( IOException e ) {
            log.error( "IOException!!!!!" + e );
            result = e.toString();
        }

        return result.replaceAll( "\r", "" );
    }
}
