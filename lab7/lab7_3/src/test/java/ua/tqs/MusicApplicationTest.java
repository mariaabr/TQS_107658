package ua.tqs;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.tqs.entity.Music;
import ua.tqs.repository.MusicRepository;

@Testcontainers
@SpringBootTest
public class MusicApplicationTest {
    
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer()
        .withUsername("admin")
        .withPassword("adminpass")
        .withDatabaseName("testmusics");

        @Autowired
        private MusicRepository musicRepository;

        @DynamicPropertySource
        static void properties(DynamicPropertyRegistry registry) {
          registry.add("spring.datasource.url", container::getJdbcUrl);
          registry.add("spring.datasource.password", container::getPassword);
          registry.add("spring.datasource.username", container::getUsername);
        }
      
    @Order(1)
	@Test
	public void savingMusics() {

		Music music = new Music("Senti Tanto","Van Zee", "do mar");
		musicRepository.save(music);
		Music music1 = new Music("Cuba","Van Zee", "do mar");
		musicRepository.save(music1);
		Music music2 = new Music("Verão Azul","DZR'T", "morangos com açucar");
		musicRepository.save(music2);

		System.out.println("3 musics saved");

	}

	@Order(2)
	@Test
	public void getAllMusics(){
		System.out.println("Getting all musics");
		musicRepository.findAll().stream().forEach(System.out::println);
	}

	@Order(3)
	@Test
	public void getMusicsByArtist(){
		System.out.println("Getting musics by artist Van Zee");
		musicRepository.findByArtist("Van Zee").stream().forEach(System.out::println);
	}

}
