package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ImportFromExcel implements Importer {
    final private String filename;
    private List<Student> studenti;
    private Map<String, Integer> note;

    public ImportFromExcel(String filename) {
        this.filename = filename;
    }

    @Override
    public void import_studenti(List<Student> studenti) {
        try (FileInputStream file = new FileInputStream(filename);
            XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                    continue;
                }
                Cell cell0 = row.getCell(0);
                Cell cell1 = row.getCell(1);
                Cell cell2 = row.getCell(2);
                Cell cell3 = row.getCell(3);

                if (cell0 != null || cell1 != null || cell2 != null || cell3 != null) {
                    String nrMatricol= (cell0.getCellType() == CellType.NUMERIC) ? String.valueOf((int) cell0.getNumericCellValue()) : cell0.getStringCellValue();
                    String nume = (cell1.getCellType() == CellType.NUMERIC) ? String.valueOf((int) cell1.getNumericCellValue()) : cell1.getStringCellValue();
                    String prenume = (cell2.getCellType() == CellType.NUMERIC) ? String.valueOf((int) cell2.getNumericCellValue()) : cell2.getStringCellValue();
                    String specializare = (cell3.getCellType() == CellType.NUMERIC) ? String.valueOf((int) cell3.getNumericCellValue()) : cell3.getStringCellValue();
                    Student s = new Student(
                            nrMatricol,
                            nume,
                            prenume,
                            specializare);
                    studenti.add(s);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void import_note(Map<String, Integer> note) {
        try (FileInputStream file = new FileInputStream(filename);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheetAt(1);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell = cellIterator.next();
                String nrMatricol = cell.getStringCellValue();
                cellIterator.next();
                String notaStudent = cell.getStringCellValue();
                note.put(nrMatricol, Integer.parseInt(notaStudent));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public Map<String, Integer> getNote() {
        return note;
    }
}
