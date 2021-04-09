public class Reservations {
    int reserved_seat = 0;
    private int posti_totali = 2;

    public int check_seats() {
        synchronized (this) {
            if (posti_totali-reserved_seat > 0){
                return posti_totali-reserved_seat;
            }
            else {
                return -1;
            }

        }
    }

    public void prenota_biglietto() throws InterruptedException {
        synchronized (this) {
            Thread.sleep(20000);
            reserved_seat += 1;
        }
    }
}
