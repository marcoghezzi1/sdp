package REST.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Statistiche {
    private double media;

    public Statistiche() {

    }

    public Statistiche(double avg) {
        this.media = avg;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
