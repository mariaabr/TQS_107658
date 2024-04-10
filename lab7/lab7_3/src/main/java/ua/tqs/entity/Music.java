package ua.tqs.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "musics")
public class Music {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicId;

    @Column(name="name")
    private String name;

    @Column(name="artist")
    private String artist;

    @Column(name="label")
    private String label;

    public Music() {}
    
    public Music(String name, String artist, String label){
        this.name = name;
        this.artist = artist;
        this.label = label;
    }

    public Long getMusicId() {
        return this.musicId;
    }

    public void setMusicId(Long musicId) {
        this.musicId = musicId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music music = (Music) o;
        return musicId.equals(music.musicId) && Objects.equals(name, music.name) && Objects.equals(artist, music.artist) && Objects.equals(label, music.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(musicId, name, artist, label);
    }
    
    @Override
    public String toString() {
        return "{" +
            " musicId='" + getMusicId() + "'" +
            ", name='" + getName() + "'" +
            ", artist='" + getArtist() + "'" +
            ", label='" + getLabel() + "'" +
            "}";
    }
}
