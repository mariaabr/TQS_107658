package ua.tqs.homework.models;

public class City {
    private String name;
    private int legacyId;
    private String id; // id for search a trip
    private String slug;

    public City(String name, int legacyId, String id, String slug) {
        this.name = name;
        this.legacyId = legacyId;
        this.id = id;
        this.slug = slug;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLegacyId() {
        return this.legacyId;
    }

    public void setLegacyId(int legacyId) {
        this.legacyId = legacyId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", legacyId='" + getLegacyId() + "'" +
            ", id='" + getId() + "'" +
            ", slug='" + getSlug() + "'" +
            "}";
    }

}
