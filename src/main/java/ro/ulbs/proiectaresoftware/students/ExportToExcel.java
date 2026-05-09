package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExportToExcel implements Exporter{
    String filename;

    public ExportToExcel(String filename) {
        this.filename = filename;
    }

    @Override
    public void export_studenti(List<Student> studenti) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Studenti");
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"Nr Matricol", "Prenume", "Nume", "Formatie"});
        int i = 2;
        for (Student s : studenti) {
            data.put(String.valueOf(i), new Object[]{
                    s.getNumarMatricol(),
                    s.getPrenume(),
                    s.getNumarMatricol(),
                    s.getFormatieDeStudiu()
            });
            i++;
        }
        int rand = 0;

        for (String key : data.keySet()) {
            Row row = sheet.createRow(rand++);
            Object[] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }

        try (FileOutputStream out = new FileOutputStream(filename)) {
            workbook.write(out);
            System.out.println("Fisierul excel a fost creat: "+filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
