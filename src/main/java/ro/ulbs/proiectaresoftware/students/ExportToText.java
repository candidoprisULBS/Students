package ro.ulbs.proiectaresoftware.students;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportToText implements Exporter{
    final private String filename;

    public ExportToText(String filename) {
        this.filename = filename;
    }

    @Override
    public void export_studenti(List<Student> studenti) {
        try {
            FileWriter fw = new FileWriter(filename);
            for(int i=0;i<studenti.size();i++){
                fw.write(studenti.get(i).toString()+"\n");
            }
            fw.close();
            System.out.println("Fisierul text a fost creat: "+filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
