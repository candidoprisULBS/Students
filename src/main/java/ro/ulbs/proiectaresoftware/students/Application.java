package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

        afisareListaNesortata(studentiFisier);
        sortareDupaNumeSiFormatiune(studentiFisier);
        afisareListaSortata(studentiFisier);

        Map<String, Integer> citireNote = citireNote("notestudenti.csv");

        System.out.println();
        for (Map.Entry<String, Integer> intrare : citireNote.entrySet()) {
            System.out.println("Student: " + intrare.getKey() + " Nota:  " + intrare.getValue());
        }

        Set<Student> setStudenti = new HashSet<>(listaStudenti);
        Student studentCautat = new Student("112", "Ioan", "Popa", "TI21/1");

        System.out.println();
        if (prezenta(setStudenti, studentCautat)) {
            System.out.println("Studentul este prezent!");
        } else {
            System.out.println("Studentul ne este prezent!");
        }

        Integer notaStudent = nota(citireNote, studentCautat);
        if (notaStudent != null) {
            System.out.println("Nota studentului " + studentCautat.getNume() + " este " + notaStudent);
        } else {
            System.out.println("Nu exista nota pentru acest student! " + studentCautat.getNume() + " este " + studentCautat.getNumarMatricol());
        }

        Map<Student, Integer> mapNoteStudenti = noteStudentiFaraMatricol(listaStudenti, citireNote);
        Student s = new Student(null, "Alex", "Doro", "C22/2");
        listaStudenti.add(s);

        printNota(mapNoteStudenti, s);

        //printareEXCEL(listaStudenti, "Printare EXCEL");

        exportList(listaStudenti,getExporterToFile("DataStudent.xlsx"));
        exportList(listaStudenti,getExporterToFile("DataStudent.csv"));
        exportList(listaStudenti,getExporterToFile("DataStudent.txt"));

    }

    public static Integer nota(Map<String, Integer> note, Student student) {
        return note.get(student.getNumarMatricol());
    }

    private static void afisareListaSortata(List<Student> list) {
        System.out.println();
        System.out.println("Studentii sortati alfabetic: ");
        for (Student student : list) {
            System.out.println(student.getNume() + " " + student.getPrenume() + " " + student.getNumarMatricol() + " " + student.getFormatieDeStudiu());
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

    public static void sortareDupaNumeSiFormatiune(List<Student> list) {
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.formatieDeStudiu.equals(o2.formatieDeStudiu)) {
                    return o1.nume.compareTo(o2.nume);
                }
                return o1.formatieDeStudiu.compareTo(o2.formatieDeStudiu);
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });
    }

//    public static boolean prezenta(List<Student> lista, Student student) {
//        for (Student stud : lista) {
//            if(stud.equals(student)){
//                return true;
//            }
//        }
//        return false;
//    }

    public static boolean prezenta(Collection<Student> c, Student s) {
        return c.contains(s);
    }

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
                    Student student1 = new Student(nrMatricol, prenume, nume, formatie);
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

    public static Map<String, Integer> citireNote(String csv) {
        Map<String, Integer> mapCreat = new HashMap<>();
        try {
            File file = new File(csv);
            Scanner scn = new Scanner(file);
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] date = line.split(",");
                if (date.length == 2) {
                    String nume = date[0].trim();
                    Integer valoare = Integer.parseInt(date[1].trim());
                    mapCreat.put(nume, valoare);
                }
            }
            scn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nu s-a gasit fisierul!");
            e.printStackTrace();
        }
        return mapCreat;
    }

    public static Map<Student, Integer> noteStudentiFaraMatricol(List<Student> list, Map<String, Integer> n) {
        Map<Student, Integer> notaFinal = new HashMap<>();
        for (Student s : list) {
            Integer nota = n.get(s.getNumarMatricol());
            if (nota != null) {
                notaFinal.put(s, nota);
            }
        }
        return notaFinal;
    }

    private static void printNota(Map<Student, Integer> afisareNotaStudentiFaraMatricol, Student studentFaraMatricol) {
        if (afisareNotaStudentiFaraMatricol.containsKey(studentFaraMatricol)) {
            System.out.println("Nota lui " + studentFaraMatricol.getNume() + " este " + afisareNotaStudentiFaraMatricol.get(studentFaraMatricol));
        } else {
            System.out.println("Studentul nu exista!");
        }
    }

    public static void printareEXCEL(List<Student> listaStudenti, String numeEXCEL) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Studenti");
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"Nr Matricol", "Prenume", "Nume", "Formatie"});
        int i = 2;
        for (Student s : listaStudenti) {
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

        try (FileOutputStream out = new FileOutputStream("StudentData.xlsx")) {
            workbook.write(out);
            System.out.println("StudentData.xlsx scris cu succes!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void exportList(List<Student> list, Exporter exporter) {
        exporter.export_studenti(list);
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

//    private static Importer getImporterFromFile(String... filename) {
//        if (filename.length == 1) {
//            String fileExtension = filename[0].substring(filename[0].lastIndexOf('.'));
//            if (fileExtension.equals(".xlsx")) {
//                return new ImportFromExcel(filename[0]);
//            }
//        } else if (filename.length == 2) {
//            String fileExtensionStudenti = filename[0].substring(filename[0].lastIndexOf('.'));
//            String fileExtensionNote = filename[1].substring(filename[1].lastIndexOf('.'));
//            return switch(fileExtensionStudenti,fileExtensionNote)
//        }
//    }
}
