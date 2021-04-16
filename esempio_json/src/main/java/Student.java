
import com.google.gson.Gson;

import java.util.ArrayList;
public class Student {
    private String name;
    private String surname;
    private String place_of_residence;
    private String year_of_birth;
    private ArrayList<Exam> esami_passati;
    public Student (String nome, String cognome, String nascita, String residenza, ArrayList<Exam> esami) {
        name = nome;
        surname = cognome;
        year_of_birth = nascita;
        place_of_residence = residenza;
        esami_passati = esami;
    }

    public static void main(String[] args) {
        Exam a = new Exam(28, "reti", "2020");
        Exam b = new Exam(30, "sicurezza", "2019");
        ArrayList<Exam> esami = new ArrayList<Exam>();
        esami.add(a);
        esami.add(b);
        Student marco = new Student("marco", "ghezzi", "1996", "godiasco", esami);
        Gson g = new Gson();
        String json = g.toJson(marco);
        System.out.println(json);
    }

}
