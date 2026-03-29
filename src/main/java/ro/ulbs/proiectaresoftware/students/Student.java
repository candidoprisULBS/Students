package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;public class Student {
    String numarMatricol;
    String prenume;
    String nume;
    String formatieDeStudiu;
    Integer nota;

    public Student(String numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
    }

    public Student(String numarMatricol, String prenume, String nume, String formatieDeStudiu, Integer nota) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
        this.nota = nota;
    }

    public String getNumarMatricol() {
        return numarMatricol;
    }

    public String getFormatieDeStudiu() {
        return formatieDeStudiu;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public Integer getNota() {return nota;}

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
}
