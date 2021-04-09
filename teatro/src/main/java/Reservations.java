public class Reservations {
    int reserved_seat = 0;
    private int posti_totali = 2;
    private final Object x = reserved_seat;

    public int check_seats() {
        synchronized (x) {
            if (posti_totali-reserved_seat > 0){
                return posti_totali-reserved_seat;
            }
            else {
                return -1;
            }

        }
    }

    public void prenota_biglietto() throws InterruptedException {
        synchronized (x) {
            Thread.sleep(10000);
            reserved_seat += 1;
        }
    }
}
