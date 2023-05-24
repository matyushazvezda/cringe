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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

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
    public List<ConcertDTO> getAllConcerts(@RequestParam(required = false) String field) {
        if (field != null && !field.isEmpty()) {
            return concertRepository.findAll(Sort.by(Direction.ASC, field))
                .stream()
                .map(this::convertToConcertDTO)
                .collect(Collectors.toList());
        } else {
            return concertRepository.findAll()
                .stream()
                .map(this::convertToConcertDTO)
                .collect(Collectors.toList());
        }
    }

    /* 
    @GetMapping
    @Transactional
    public List<ConcertDTO> getAllConcerts() {
        return concertRepository.findAll()
        .stream()
        .map(this::convertToConcertDTO)
        .collect(Collectors.toList());
    }*/
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
}
