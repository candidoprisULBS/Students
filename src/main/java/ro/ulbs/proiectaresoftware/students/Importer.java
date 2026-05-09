package ro.ulbs.proiectaresoftware.students;

import java.util.*;

/*
design pattern: factory
new ImportCSV("fisnote","fisstudenti")
importXlsx("fisXlsx") - luat in considerare sheet-ul foii de calcul.O posibilitate de utilizare: un sheet studenti, un sheet note
getImporter(String... values) -String...
Values[0]
 */

public interface Importer {
    void import_studenti(List<Student> studenti);
    void import_note(Map<String,Integer> note);
}
