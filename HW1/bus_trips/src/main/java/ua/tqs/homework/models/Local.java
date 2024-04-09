package ua.tqs.homework.models;

public class Local {
    private String zipcode;
    private double score;
    private Country country;
    private String address;
    private City city;
    private String name;
    // private int legacyId;
    private Location location;
    // private String id;
    // private int importanceOrder;
    private String slug;
    private boolean isTrain;

    public Local(String zipcode, double score, Country country, String address, City city, String name, Location location, String slug, boolean isTrain) {
        this.zipcode = zipcode;
        this.score = score;
        this.country = country;
        this.address = address;
        this.city = city;
        this.name = name;
        // this.legacyId = legacyId;
        this.location = location;
        // this.id = id;
        // this.importanceOrder = importanceOrder;
        this.slug = slug;
        this.isTrain = isTrain;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public int getLegacyId() {
    //     return this.legacyId;
    // }

    // public void setLegacyId(int legacyId) {
    //     this.legacyId = legacyId;
    // }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // public String getId() {
    //     return this.id;
    // }

    // public void setId(String id) {
    //     this.id = id;
    // }

    // public int getImportanceOrder() {
    //     return this.importanceOrder;
    // }

    // public void setImportanceOrder(int importanceOrder) {
    //     this.importanceOrder = importanceOrder;
    // }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isIsTrain() {
        return this.isTrain;
    }

    public boolean getIsTrain() {
        return this.isTrain;
    }

    public void setIsTrain(boolean isTrain) {
        this.isTrain = isTrain;
    }


    @Override
    public String toString() {
        return "{" +
            " zipcode='" + getZipcode() + "'" +
            ", score='" + getScore() + "'" +
            ", country='" + getCountry() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", name='" + getName() + "'" +
            ", location='" + getLocation() + "'" +
            ", slug='" + getSlug() + "'" +
            ", isTrain='" + isIsTrain() + "'" +
            " }";
    }

}
