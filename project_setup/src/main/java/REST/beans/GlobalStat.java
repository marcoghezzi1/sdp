package REST.beans;

import javax.xml.bind.annotation.*;
import java.sql.Timestamp;

@XmlRootElement(name="global stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class GlobalStat {
    private double avgDelivery;
    private double avgKm;
    private double avgBatteryLife;

    private double avgPollution;
    @XmlTransient
    private Timestamp timestamp;

    public GlobalStat() {
    }

    public GlobalStat(double avgDelivery, double avgKm, double avgBatteryLife, double avgPollution, String timestamp) {
        this.avgDelivery = avgDelivery;
        this.avgKm = avgKm;
        this.avgBatteryLife = avgBatteryLife;
        this.avgPollution = avgPollution;
        this.timestamp = Timestamp.valueOf(timestamp);
    }

    public double getAvgDelivery() {
        return avgDelivery;
    }

    public void setAvgDelivery(double avgDelivery) {
        this.avgDelivery = avgDelivery;
    }

    public double getAvgKm() {
        return avgKm;
    }

    public void setAvgKm(double avgKm) {
        this.avgKm = avgKm;
    }

    public double getAvgBatteryLife() {
        return avgBatteryLife;
    }

    public void setAvgBatteryLife(double avgBatteryLife) {
        this.avgBatteryLife = avgBatteryLife;
    }

    public double getAvgPollution() {
        return avgPollution;
    }

    public void setAvgPollution(double avgPollution) {
        this.avgPollution = avgPollution;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
