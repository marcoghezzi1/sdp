package REST;

public class Order {
    private String id;
    private int[] ritiro;
    private int[] consegna;

    public Order(String id, int[] ritiro, int[] consegna) {
        this.id = id;
        this.ritiro = ritiro;
        this.consegna = consegna;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getRitiro() {
        return ritiro;
    }

    public void setRitiro(int[] ritiro) {
        this.ritiro = ritiro;
    }

    public int[] getConsegna() {
        return consegna;
    }

    public void setConsegna(int[] consegna) {
        this.consegna = consegna;
    }
}
