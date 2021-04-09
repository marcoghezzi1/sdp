public class Reservations {
    int reserved_seat = 0;
    int posti_totali = 50;

    public int check_seats() {
        synchronized (this) {
            if (posti_totali-reserved_seat > 0)

        }
    }
}
