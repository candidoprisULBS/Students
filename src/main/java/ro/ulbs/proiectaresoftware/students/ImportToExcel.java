package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ImportToExcel implements Importer {
    public ImportToExcel(String filename) {
        this.filename = filename;
    }

    String filename;

    @Override
    public void import_studenti(List<Student> studenti) {
        List<Student> students = new ArrayList<>();
        try(FileInputStream file = new FileInputStream(filename);
            XSSFWorkbook workbook = new XSSFWorkbook(file)){
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.println(cell.getStringCellValue()+"\t");
                            break;
                        case NUMERIC:
                            System.out.println(cell.getNumericCellValue()+"\t");
                            break;
                        default:
                            break;
                    }
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void import_note(Map<String, Integer> note) {

    }
}
