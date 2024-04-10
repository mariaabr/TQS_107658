package ua.tqs.entity;

import javax.persistence.*;

import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "musics")
public class Music {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="music")
    private String music;

    @Column(name="artist")
    private String artist;

    @Column(name="label")
    private String label;

    public Music() {}
    
    public Music(String music, String artist, String label){
        this.music = music;
        this.artist = artist;
        this.label = label;
    }

    // public Long getMusicId() {
    //     return this.id;
    // }

    // public void setMusicId(Long id) {
    //     this.id = id;
    // }

    // public String getMusic() {
    //     return this.music;
    // }

    // public void setName(String music) {
    //     this.music = music;
    // }

    // public String getArtist() {
    //     return this.artist;
    // }

    // public void setArtist(String artist) {
    //     this.artist = artist;
    // }

    // public String getLabel() {
    //     return this.label;
    // }

    // public void setLabel(String label) {
    //     this.label = label;
    // }

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     Music music = (Music) o;
    //     return id.equals(music.id) && Objects.equals(music, music.music) && Objects.equals(artist, music.artist) && Objects.equals(label, music.label);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(id, music, artist, label);
    // }
    
    // @Override
    // public String toString() {
    //     return "{" +
    //         " musicId='" + getMusicId() + "'" +
    //         ", music='" + getMusic() + "'" +
    //         ", artist='" + getArtist() + "'" +
    //         ", label='" + getLabel() + "'" +
    //         "}";
    // }
}
