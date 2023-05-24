package my_project.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import my_project.dto.ConcertDTO;
import my_project.dto.MusicianDTO;
import my_project.model.Concert;
import my_project.model.Musician;
import my_project.repository.ConcertRepository;
import my_project.repository.MusicianRepository; 

@RestController
@RequestMapping("/api/concerts")
@Slf4j
public class ConcertController {
    @Autowired
    private ConcertRepository concertRepository;
    @Autowired
    private MusicianRepository musicianRepository;


    @GetMapping
    @Transactional
    public List<ConcertDTO> getAllConcerts() {
        return concertRepository.findAll()
        .stream()
        .map(this::convertToConcertDTO)
        .collect(Collectors.toList());
    }
    private ConcertDTO convertToConcertDTO(Concert concert) {
        ConcertDTO concertDTO = new ConcertDTO();
        concertDTO.setId(concert.getId());
        concertDTO.setName(concert.getName());
        concertDTO.setLocation(concert.getLocation());
        concertDTO.setTicketPriceS(concert.getTicketPriceS());
        concertDTO.setTicketPriceV(concert.getTicketPriceV());
        concertDTO.setDate(concert.getDate());
        log.info("reklama1");
        concertDTO.setTime(concert.getTime());
        concertDTO.setMusicians(
            concert.getMusicians()
                .stream()
                .map(this::convertToMusicianDTO)
                .collect(Collectors.toSet()));
       
        return concertDTO;
    }

    private MusicianDTO convertToMusicianDTO (Musician musician) {
        MusicianDTO musicianDTO = new MusicianDTO();
        musicianDTO.setId(musician.getId());
        musicianDTO.setBio(musician.getBio());
        musicianDTO.setFirstName(musician.getFirstName());
        musicianDTO.setLastName(musician.getLastName());
        musicianDTO.setMusicStyle(musician.getMusicStyle());

        return musicianDTO;
    }
     
    @GetMapping("/{id}")
    @Transactional
    public ConcertDTO getConcertById(@PathVariable Long id) {
        Concert concert = concertRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Concert not found with id: " + id));
        return convertToConcertDTO(concert);
    }
    @PostMapping
    @Transactional
    public ConcertDTO createConcert(@RequestBody ConcertDTO concertDTO) {
        log.info("reklama_createConcert");
        Concert concert = new Concert();
        concert.setName(concertDTO.getName());
        concert.setLocation(concertDTO.getLocation());
        concert.setTicketPriceS(concertDTO.getTicketPriceS());
        concert.setTicketPriceV(concertDTO.getTicketPriceV());
        concert.setDate(concertDTO.getDate());
        concert.setTime(concertDTO.getTime());

        // Преобразуем список музыкантов из DTO в сущности Musician
        Set<Musician> musicians = concertDTO.getMusicians().stream()
                .map(this::convertToMusicianEntity)
                .collect(Collectors.toSet());
        concert.setMusicians(musicians);

        Concert createdConcert = concertRepository.save(concert);

        return convertToConcertDTO(createdConcert);
    }

    private Musician convertToMusicianEntity(MusicianDTO musicianDTO) {
        Musician musician = new Musician();
        musician.setId(musicianDTO.getId());
        musician.setBio(musicianDTO.getBio());
        musician.setFirstName(musicianDTO.getFirstName());
        musician.setLastName(musicianDTO.getLastName());
        musician.setMusicStyle(musicianDTO.getMusicStyle());

        return musician;
    }

    @PostMapping("/{id}")
    @Transactional
    public ConcertDTO updateConcert(@PathVariable Long id, @RequestBody ConcertDTO concertDTO) {
        log.info("reklama_upadateConcert");
        Concert existingConcert = concertRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Concert not found with id: " + id));

        existingConcert.setName(concertDTO.getName());
        existingConcert.setLocation(concertDTO.getLocation());
        existingConcert.setTicketPriceS(concertDTO.getTicketPriceS());
        existingConcert.setTicketPriceV(concertDTO.getTicketPriceV());
        existingConcert.setDate(concertDTO.getDate());
        existingConcert.setTime(concertDTO.getTime());

        // Преобразуем список музыкантов из DTO в сущности Musician
        Set<Musician> musicians = concertDTO.getMusicians().stream()
                .map(this::convertToMusicianEntity)
                .collect(Collectors.toSet());
        existingConcert.setMusicians(musicians);

        Concert updatedConcert = concertRepository.save(existingConcert);

        return convertToConcertDTO(updatedConcert);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteConcert(@PathVariable Long id) {
        if (concertRepository.existsById(id)) {
            concertRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }





/* 
    @PostMapping
    public Concert createConcert(@RequestBody Concert concert) {
        return concertRepository.save(concert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Concert> updateConcert(@PathVariable Long id, @RequestBody Concert concert) {
        return concertRepository.findById(id)
                .map(existingConcert -> {
                    existingConcert.setName(concert.getName());
                    existingConcert.setDate(concert.getDate());
                    existingConcert.setLocation(concert.getLocation());
                    existingConcert.setTicketPriceS(concert.getTicketPriceS());
                    existingConcert.setTicketPriceV(concert.getTicketPriceV());
                    existingConcert.setMusicians(concert.getMusicians());
                    return ResponseEntity.ok(concertRepository.save(existingConcert));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Concert> deleteConcert(@PathVariable Long id) {
        return concertRepository.findById(id)
                .map(existingConcert -> {
                    concertRepository.delete(existingConcert);
                    return ResponseEntity.ok(existingConcert);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{concertId}/musicians/{musicianId}")
    public ResponseEntity<Concert> addMusicianToConcert(@PathVariable Long concertId,
                                                        @PathVariable Long musicianId) {
        Optional<Concert> optionalConcert = concertRepository.findById(concertId);
        Optional<Musician> optionalMusician = musicianRepository.findById(musicianId);
        if (optionalConcert.isPresent() && optionalMusician.isPresent()) {
            Concert concert = optionalConcert.get();
            Musician musician = optionalMusician.get();
            concert.getMusicians().add(musician);
            return ResponseEntity.ok(concertRepository.save(concert));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{concertId}/musicians/{musicianId}")
    public ResponseEntity<Concert> removeMusicianFromConcert(@PathVariable Long concertId,
                                                             @PathVariable Long musicianId) {
        Optional<Concert> optionalConcert = concertRepository.findById(concertId);
        Optional<Musician> optionalMusician = musicianRepository.findById(musicianId);
        if (optionalConcert.isPresent() && optionalMusician.isPresent()) {
            Concert concert = optionalConcert.get();
            Musician musician = optionalMusician.get();
            concert.getMusicians().remove(musician);
            return ResponseEntity.ok(concertRepository.save(concert));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    */
}
