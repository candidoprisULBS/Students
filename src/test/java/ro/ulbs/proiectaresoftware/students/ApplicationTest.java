package ro.ulbs.proiectaresoftware.students;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void prezenta() { //-3 cazuri
        //Arrange
        List<Student> listaStudenti = new ArrayList();
        Student s1 = new Student("112", "Ioan", "Popa", "TI21/1");
        Student s2 = new Student("112", "Maria", "Oprea", "TI21/1");
        Student s3 = new Student("120", "Alis", "Popa", "TI21/2");
        Student s4 = new Student("112", "Ioan", "Popa", "TI21/1");
        listaStudenti.add(s1);
        listaStudenti.add(s2);
        listaStudenti.add(s4);
        //Act
        Student prezent = s2;
        Student absent = s3;
        Student duplicat = s4;
        Catalog catalog=Catalog.getInstance();
        //Assert
        assertTrue(catalog.prezenta(listaStudenti, prezent));
        assertFalse(catalog.prezenta(listaStudenti, absent));
        assertTrue(catalog.prezenta(listaStudenti, duplicat));
    }

    @Test
    void nota() { //-3 cazuri
        //Arrange
        Student s1 = new Student("112", "Ioan", "Popa", "TI21/1");
        Student s2 = new Student("152", "Maria", "Oprea", "TI21/1");
        Student s3 = new Student("120", "Alis", "Popa", "TI21/2");
        Map<String,Integer> noteMap = new HashMap();
        noteMap.put("112", 10);
        noteMap.put("120", 7);
        noteMap.put("152", null);
        //Act
        Catalog catalog = Catalog.getInstance();
        Integer notaCorecta = catalog.nota(noteMap,s1);
        Integer notaGresita = catalog.nota(noteMap,s3);
        Integer notaInexsistenta = catalog.nota(noteMap,s2);
        //Assert
        assertEquals(10,notaCorecta);
        assertNotEquals(8,notaGresita);
        assertEquals(null,notaInexsistenta);
    }

    @Test
    void sortareDupaNumeSiFormatiune() { //-2 cazuri
        //Arrange
        List<Student> listaStudenti1 = new ArrayList(); //formatiuni unice
        Student s1 = new Student("112", "Ioan", "Popa", "TI21/1");
        Student s2 = new Student("112", "Maria", "Oprea", "TI21/1");
        Student s3 = new Student("120", "Alis", "Popa", "TI21/1");
        Student s4 = new Student("112", "Ioan", "Popa", "TI21/1");

        List<Student> listaStudenti2 = new ArrayList(); //formatiuni diferite
        Student s5 = new Student("112", "Ioan", "Popa", "TI21/1");
        Student s6 = new Student("112", "Maria", "Oprea", "ISM");
        Student s7 = new Student("120", "Alis", "Popa", "TI21/1");
        Student s8 = new Student("112", "Ioan", "Popa", "ISM");

        Catalog catalog = Catalog.getInstance();
        //Act - formatiuni unice
        catalog.sortareDupaNumeSiFormatiune(listaStudenti1);
        //Assert - formatiuni unice
        for(int i = 0; i < listaStudenti1.size() - 1; i++) {
            Student curent = listaStudenti1.get(i);
            Student urmator =  listaStudenti1.get(i+1);
            int rezultatComparare = curent.compareTo(urmator);

            assertEquals(rezultatComparare <=0,"Ordine gresita");
        }
        //Act - formatiuni diferite
        catalog.sortareDupaNumeSiFormatiune(listaStudenti2);
        //Assert - formatiuni diferite
        for(int i = 0; i < listaStudenti2.size() - 1; i++) {
            Student curent = listaStudenti2.get(i);
            Student urmator =  listaStudenti2.get(i+1);
            int rezultatComparare = curent.compareTo(urmator);

            assertEquals(rezultatComparare <=0,"Ordine gresita");
        }
    }
}