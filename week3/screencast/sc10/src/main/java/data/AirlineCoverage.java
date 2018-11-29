package data;

public class AirlineCoverage {
    long nbRoutes;
    String country;
    String name;

    public AirlineCoverage(long nbRoutes, String country, String name) {
        super();
        this.nbRoutes = nbRoutes;
        this.country = country;
        this.name = name;
    }

    public long getNbRoutes() {
        return nbRoutes;
    }

    public void setNbRoutes(long nbRoutes) {
        this.nbRoutes = nbRoutes;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
