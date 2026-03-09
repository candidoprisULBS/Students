package ro.ulbs.proiectaresoftware.students;

public class Student {
    int numarMatricol;
    String prenume;
    String nume;
    String formațieDeStudiu;

    public Student(int numarMatricol, String prenume, String nume, String formațieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formațieDeStudiu = formațieDeStudiu;
    }

    public int getNumarMatricol() {
        return numarMatricol;
    }

    public String getFormațieDeStudiu() {
        return formațieDeStudiu;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    @Override
    public String toString() {
        return String.format("%d %10s %10s %10s",numarMatricol,prenume,nume,formațieDeStudiu);
    }
}
