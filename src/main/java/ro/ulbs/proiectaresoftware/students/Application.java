package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.List;

public class Application {
    static void main() {
        Student s1 = new Student(112, "Ioan","Popa","TI21/1");
        Student s2 = new Student(112, "Maria","Oprea","TI21/1");
        Student s3 = new Student(120, "Alis","Popa","TI21/2");
        Student s4 = new Student(122, "Mihai","Vecerdea","TI22/1");
        Student s5 = new Student(122, "Eugen","Uritescu","TI22/2");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);

        List<Student> studenti = new ArrayList();
        System.out.println("Nr. studenti este " + studenti.size());
        studenti.add(s1);
        studenti.add(s2);
        studenti.add(s3);
        studenti.add(s4);
        studenti.add(s5);
        System.out.println("Nr. studenti este " + studenti.size());

        for (Student s : studenti){
            System.out.println(s);
        }
    }
}
