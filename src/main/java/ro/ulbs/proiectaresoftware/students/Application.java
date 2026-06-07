package ro.ulbs.proiectaresoftware.students;


import java.io.*;
import java.util.*;

public class Application {
    static void main() {
        Student s1 = new Student("112", "Ioan", "Popa", "TI21/1");
        Student s2 = new Student("112", "Maria", "Oprea", "TI21/1");
        Student s3 = new Student("120", "Alis", "Popa", "TI21/2");
        Student s4 = new Student("122", "Mihai", "Vecerdea", "TI22/1");
        Student s5 = new Student("122", "Eugen", "Uritescu", "TI22/2");
//
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
//        System.out.println(s4);
//        System.out.println(s5);
//
        List<Student> listaStudenti = new ArrayList();
//        System.out.println("Nr. listaStudenti este " + listaStudenti.size());
        listaStudenti.add(s1);
        listaStudenti.add(s2);
        listaStudenti.add(s3);
        listaStudenti.add(s4);
        listaStudenti.add(s5);
//        System.out.println("Nr. listaStudenti este " + listaStudenti.size());

//        for (Student s : listaStudenti){
//            System.out.println(s);
//        }

        List<Student> studentiFisier = citireFisier("studenti.csv");

        Catalog catalog = Catalog.getInstance();


        afisareListaNesortata(studentiFisier);

        catalog.sortareDupaNumeSiFormatiune(studentiFisier);
        //sortareDupaNumeSiFormatiune(studentiFisier);

        afisareListaSortata(studentiFisier);


        Map<String, Integer> citireNote = catalog.citireNote("notestudenti.csv");

        System.out.println();
        for (Map.Entry<String, Integer> intrare : citireNote.entrySet()) {
            System.out.println("Student: " + intrare.getKey() + " Nota:  " + intrare.getValue());
        }

        Set<Student> setStudenti = new HashSet<>(listaStudenti);
        Student studentCautat = new Student(
                "112",
                "Ioan",
                "Popa",
                "TI21/1"
        );

        System.out.println();
        if (catalog.prezenta(setStudenti, studentCautat)) {
            System.out.println("Studentul este prezent!");
        } else {
            System.out.println("Studentul ne este prezent!");
        }

        Integer notaStudent = catalog.nota(
                citireNote,
                studentCautat
        );
        //Integer notaStudent = nota(citireNote, studentCautat);
        if (notaStudent != null) {
            System.out.println(
                    "Nota studentului " +
                    studentCautat.nume() +
                    " este " +
                    notaStudent
            );
        } else {
            System.out.println("Nu exista nota pentru acest student! " + studentCautat.nume() + " este " + studentCautat.numarMatricol());
        }

        Map<Student, Integer> mapNoteStudenti = catalog.noteStudentiFaraMatricol(
                listaStudenti,
                citireNote
        );
        Student s = new Student(
                null,
                "Alex",
                "Doro",
                "C22/2"
        );
        listaStudenti.add(s);

        printNota(mapNoteStudenti, s);

        catalog.exportList(studentiFisier,getExporterToFile("DataStudent.xlsx"));
        catalog.exportList(studentiFisier,getExporterToFile("DataStudent.csv"));
        catalog.exportList(studentiFisier,getExporterToFile("DataStudent.txt"));


        //Importer importer = getImporterFromFile("studenti.csv", "notestudenti.csv");
        Importer importer = getImporterFromFile("ImportFile.xlsx");



//        for(Student student : ImportFromExcel) {
//            System.out.println(student);
//        }

        System.out.println();
        List<Student> listaFiltreStudenti = new ArrayList<>() ;
        importer.import_studenti(listaFiltreStudenti);

//        for (Student student : listaFiltreStudenti) {
//            System.out.println(student);
//        }

        System.out.println("===GATA===");
    }


    private static void afisareListaSortata(List<Student> list) {
        System.out.println();
        System.out.println("Studentii sortati alfabetic: ");
        for (Student student : list) {
            System.out.println(student.nume() + " " + student.prenume() + " " + student.numarMatricol() + " " + student.formatieDeStudiu());
        }
    }

    private static void afisareListaNesortata(List<Student> list) {
        System.out.println("Studentii cititi din fisier: ");
        if (list.isEmpty()) {
            System.out.println("Nu sunt studenti in lista!");
        } else {
            for (Student student : list) {
                System.out.println(student);
            }
        }
    }

//    public static boolean prezenta(List<Student> lista, Student student) {
//        for (Student stud : lista) {
//            if(stud.equals(student)){
//                return true;
//            }
//        }
//        return false;
//    }

    public static List<Student> citireFisier(String csv) {
        List<Student> stud = new ArrayList<>();
        try {
            File file = new File(csv);
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
        return stud;
    }

    private static void printNota(Map<Student, Integer> afisareNotaStudentiFaraMatricol, Student studentFaraMatricol) {
        if (afisareNotaStudentiFaraMatricol.containsKey(studentFaraMatricol)) {
            System.out.println("Nota lui " + studentFaraMatricol.nume() + " este " + afisareNotaStudentiFaraMatricol.get(studentFaraMatricol));
        } else {
            System.out.println("Studentul nu exista!");
        }
    }

    private static Exporter getExporterToFile(String filename) {
        String fileExtension = filename.substring(filename.lastIndexOf('.'));
        return switch (fileExtension) {
            case ".xlsx" -> new ExportToExcel(filename);
            case ".csv" -> new ExportToCSV(filename);
            case ".txt" -> new ExportToText(filename);
            default -> throw new IllegalArgumentException("Extensie nesuportata " + fileExtension);
        };
    }

    private static void importList(List<Student> list, Importer importer) {
        importer.import_studenti(list);
    }

    private static void importNote(Map<String, Integer> map, Importer importer) {
        importer.import_note(map);
    }

    private static Importer getImporterFromFile(String... filename) {
        if (filename.length == 1) {
            String fileExtension = filename[0].substring(filename[0].lastIndexOf('.'));
            if (fileExtension.equalsIgnoreCase(".xlsx")) {
                return new ImportFromExcel(filename[0]);
            }
            throw new IllegalArgumentException("Pentru un singur fisier formatul trebuie sa fie de tip '.xlsx'");
        }
        if (filename.length == 2) {
           return new ImportFromCSV(filename[0], filename[1]);
        }
        throw new IllegalArgumentException("Specificati 1 fisier '.xlsx' sau 2 fisiere '.csv'");
    }
}
