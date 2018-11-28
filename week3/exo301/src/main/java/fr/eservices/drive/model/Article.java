package fr.eservices.drive.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "ean" }))
public abstract class Article {

    @Id
    @GeneratedValue
    private int id;
    private String ean;
    private double price;
    private double vat;
    private String name;
    private String img;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategory(List<Category> categories) {
        this.categories = categories;
    }

}
