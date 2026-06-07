package ro.ulbs.proiectaresoftware.students;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ImportFromCSV implements Importer{
    final private String filenameStudent;
    final private String filenameNote;
    private List<Student> studenti;
    private Map<String, Integer> note;

    public ImportFromCSV(String filenameStudent, String filenameNote) {
        this.filenameStudent = filenameStudent;
        this.filenameNote = filenameNote;
    }

    @Override
    public void import_studenti(List<Student> studenti) {
        List<Student> stud = new ArrayList<>();
        try {
            File file = new File(filenameStudent);
            Scanner scn = new Scanner(file);
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] date = line.split(",");
                if (date.length == 4) {
                    String nrMatricol = date[0].trim();
                    String prenume = date[1].trim();
                    String nume = date[2].trim();
                    String formatie = date[3].trim();
                    Student student1 = new Student(
                            nrMatricol,
                            prenume,
                            nume,
                            formatie
                    );
                    stud.add(student1);
                }
            }
            scn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nu s-a gasit fisierul!");
            e.printStackTrace();
        }
    }

    @Override
    public void import_note(Map<String, Integer> note) {
        try {
            File file = new File(filenameNote);
            Scanner scn = new Scanner(file);
            while (scn.hasNextLine()) {
                String linie = scn.nextLine();
                String[] date = linie.split(",");
                if (date.length == 2) {
                    String nrMatricol = date[0].trim();
                    String nota = date[1].trim();
                    try {
                        int valoareNota = Integer.parseInt(nota);
                        note.put(nrMatricol, valoareNota);
                    } catch (NumberFormatException e) {
                        System.err.println("Nota invalida pentru studentul " + nrMatricol);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului CSV de note: " + e.getMessage());
        }
    }
}
