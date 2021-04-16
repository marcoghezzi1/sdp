
import com.google.gson.Gson;

import java.util.ArrayList;
public class Student {
    private String name;
    private String surname;
    private String place_of_residence;
    private int year_of_birth;
    private ArrayList<Exam> esami_passati;

    public Student(String name, String surname, String place_of_residence, int year_of_birth, ArrayList<Exam> esami_passati) {
        this.name = name;
        this.surname = surname;
        this.place_of_residence = place_of_residence;
        this.year_of_birth = year_of_birth;
        this.esami_passati = esami_passati;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPlace_of_residence() {
        return place_of_residence;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public ArrayList<Exam> getEsami_passati() {
        return esami_passati;
    }
}
