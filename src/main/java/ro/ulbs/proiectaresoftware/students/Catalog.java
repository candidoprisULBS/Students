package ro.ulbs.proiectaresoftware.students;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Catalog {
    private static Catalog instance = null;
    private Catalog() {
    }

    public static Catalog getInstance() {
        if(instance == null) {
            instance = new Catalog();
        }
        return instance;
    }

    public static void exportList(List<Student> list, Exporter exporter) {
        exporter.export_studenti(list);
    }

    public static Map<Student, Integer> noteStudentiFaraMatricol(List<Student> list, Map<String, Integer> n) {
        Map<Student, Integer> notaFinal = new HashMap<>();
        for (Student s : list) {
            Integer nota = n.get(s.numarMatricol());
            if (nota != null) {
                notaFinal.put(s, nota);
            }
        }
        return notaFinal;
    }

    public static boolean prezenta(Collection<Student> c, Student s) {
        return c.contains(s);
    }

    public static Integer nota(Map<String, Integer> note, Student student) {
        return note.get(student.numarMatricol());
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

    public static void sortareDupaNumeSiFormatiune(List<Student> list) {
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.formatieDeStudiu().equals(o2.formatieDeStudiu())) {
                    return o1.nume().compareTo(o2.nume());
                }
                return o1.formatieDeStudiu().compareTo(o2.formatieDeStudiu());
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });
    }
}
