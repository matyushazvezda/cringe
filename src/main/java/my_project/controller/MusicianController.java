package my_project.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import my_project.dto.ConcertDTO;
import my_project.dto.MusicianDTO;
import my_project.model.Concert;
import my_project.model.Musician;
import my_project.repository.ConcertRepository;
import my_project.repository.MusicianRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/musicians")
@Slf4j
public class MusicianController {
    @Autowired
    private ConcertRepository concertRepository;
    @Autowired
    private MusicianRepository musicianRepository;


    @GetMapping
    @Transactional
    public List<MusicianDTO> getAllConcerts() {
        return musicianRepository.findAll()
        .stream()
        .map(this::convertToMusicianDTO)
        .collect(Collectors.toList());
    }
    private MusicianDTO convertToMusicianDTO (Musician musician) {
        MusicianDTO musicianDTO = new MusicianDTO();
        log.info("reklama_music");
        musicianDTO.setId(musician.getId());
        musicianDTO.setBio(musician.getBio());
        musicianDTO.setFirstName(musician.getFirstName());
        musicianDTO.setLastName(musician.getLastName());
        musicianDTO.setMusicStyle(musician.getMusicStyle());

        return musicianDTO;
    }

    @GetMapping("/{id}")
    @Transactional
    public MusicianDTO getMusicianById(@PathVariable Long id) {
        log.info("reklama_music_id");
        Musician musician = musicianRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Musician not found with id: " + id));
        return convertToMusicianDTO(musician);
    }

    @PostMapping("/{id}")
    @Transactional
    public MusicianDTO updateMusician(@PathVariable Long id, @RequestBody MusicianDTO musicianDTO) {
        Musician musician = musicianRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Musician not found with id: " + id));
    
        // Обновляем данные музыканта
        musician.setBio(musicianDTO.getBio());
        musician.setFirstName(musicianDTO.getFirstName());
        musician.setLastName(musicianDTO.getLastName());
        musician.setMusicStyle(musicianDTO.getMusicStyle());
    
        // Сохраняем обновленного музыканта
        Musician updatedMusician = musicianRepository.save(musician);
    
        return convertToMusicianDTO(updatedMusician);
    }

    @PostMapping
    @Transactional
      public MusicianDTO createMusician(@RequestBody MusicianDTO musicianDTO) {
        log.info("reklama_music_creat");
      // Создаем новый объект Musician на основе данных из musicianDTO
      Musician musician = new Musician();
      musician.setBio(musicianDTO.getBio());
      musician.setFirstName(musicianDTO.getFirstName());
      musician.setLastName(musicianDTO.getLastName());
      musician.setMusicStyle(musicianDTO.getMusicStyle());

      // Сохраняем нового музыканта
      Musician createdMusician = musicianRepository.save(musician);

      // Возвращаем созданного музыканта в виде MusicianDTO
      return convertToMusicianDTO(createdMusician);
    } 

    @PostMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteMusician(@PathVariable Long id) {
        log.info("reklama_music_delete");
        // Проверяем, существует ли музыкант с заданным идентификатором
        if (musicianRepository.existsById(id)) {
          // Удаляем музыканта из репозитория
          musicianRepository.deleteById(id);
          return ResponseEntity.noContent().build();
        } else {
          return ResponseEntity.notFound().build();
        }
    }

}

