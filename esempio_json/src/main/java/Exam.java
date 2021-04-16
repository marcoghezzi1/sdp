import java.util.Date;

public class Exam {
    private final int voto;
    private final String nome_esame;
    private final String data_verbalizzazione;

    public Exam(int v, String nome, String data) {
        voto = v;
        nome_esame = nome;
        data_verbalizzazione = data;
    }

    public String getData_verbalizzazione() {
        return data_verbalizzazione;
    }

    public int getVoto() {
        return voto;
    }

    public String getNome_esame() {
        return nome_esame;
    }
}
