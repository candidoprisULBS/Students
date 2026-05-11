package ro.ulbs.proiectaresoftware.students;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportToCSV implements Exporter {
    final private String filename;

    public ExportToCSV(String filename) {
        this.filename = filename;
    }

    public void export_studenti(List<Student> studenti) {
        try {
            FileWriter fw = new FileWriter(filename);
            for(int i=0;i<studenti.size();i++){
                fw.write(String.format("%s, %s, %s, %s\n",
                        stergereTextNull(studenti.get(i).getNumarMatricol()),
                        stergereTextNull(studenti.get(i).getPrenume()),
                        stergereTextNull(studenti.get(i).getNume()),
                        stergereTextNull(studenti.get(i).getFormatieDeStudiu())));
            }
            fw.close();
            System.out.println("Fisierul CSV a fost creat: "+filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String stergereTextNull(String text){
        if(text==null){
            return "";
        }
        return text;
    }
}
