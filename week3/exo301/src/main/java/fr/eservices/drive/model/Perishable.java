package fr.eservices.drive.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Perishable extends Article {

    private String lot;
    private Date bestBefore;

    public Date getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(Date bestBefore) {
        this.bestBefore = bestBefore;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

}
