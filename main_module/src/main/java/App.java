import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {

    private final static Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        addProp();
        editProp();

        writeToExcel();
        addToExcel();
        readFromExcel();
    }

    private static void writeToExcel() {
        log.warn( "Creating Excel file with 3 records" );
        String[] xlsRecords = {"1One", "2Two", "9Nine"};
        FileOperations.writeXLS(xlsRecords, "testtt.xls" );
    }

    private static void addToExcel() {
        log.warn( "Adding to Excel file 2 records" );
        String[] xlsRecords = new String[] {"1j2h", "##%^"};
        FileOperations.appendXLS(xlsRecords, "testtt.xls");
    }

    private static void readFromExcel() {
        log.warn( "Reading from Excel file all records" );
        List<Object[]> readFromXLS = FileOperations.readXLS("testtt.xls");
        printArray(readFromXLS);
    }

    private static void addProp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String propertyName = String.valueOf(System.currentTimeMillis());
        log.warn("Add to file 'custom.properties' new property [" + propertyName + "] with current time");
        FileOperations.addPropertyToFile( propertyName + "=" + dateFormat.format(date) );
        log.fatal("New property: " + FileOperations.readPropertyFromFile( propertyName ));
    }

    private static void editProp() {
        String propertyToModiy = "mainProperty";
        log.warn("Change property [" + propertyToModiy + "] in 'custom.properties' file");
        FileOperations.modifyProperty(propertyToModiy, "air");
        log.fatal("Changed property: " + FileOperations.readPropertyFromFile( propertyToModiy ));
    }

    private static void printArray(List<Object[]> readFromXLS) {
        for (Object[] str : readFromXLS ) {
            for (Object s : str ) {
                System.out.print(s + "\t\t");
            }
            System.out.println();
        }
    }
}
