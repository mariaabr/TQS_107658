package ua.tqs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import ua.tqs.entity.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music,Long>{
    List<Music> findByArtist(String artist);
    List<Music> findAll();
}
