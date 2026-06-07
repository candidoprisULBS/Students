package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public record Student(
        String numarMatricol,
        String prenume,
        String nume,
        String formatieDeStudiu)

        implements Comparable<Student> {

    @Override
    public String toString() {
        return String.format("%s %10s %10s %10s", numarMatricol, prenume, nume, formatieDeStudiu);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(prenume, student.prenume) && Objects.equals(nume, student.nume) && Objects.equals(formatieDeStudiu, student.formatieDeStudiu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenume, nume, formatieDeStudiu);
    }

    @Override
    public int compareTo(Student s) {
        if (this.formatieDeStudiu.equals(s.formatieDeStudiu)) {
            if (this.nume.equals(s.nume)) {
                return this.prenume.compareTo(s.prenume);
            }
            return this.nume.compareTo(s.nume);
        }
        return this.formatieDeStudiu.compareTo(s.formatieDeStudiu);
    }

    ;
}
