package my_project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my_project.model.Musician;
import my_project.repository.MusicianRepository;

/* 
@RestController
@RequestMapping("/api/musicians")
public class MusicianController {
    
    private final MusicianRepository musicianRepository;

    public MusicianController(MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    @GetMapping
    public List<Musician> getAllMusicians() {
        return musicianRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musician> getMusicianById(@PathVariable Long id) {
        return musicianRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Musician createMusician(@RequestBody Musician musician) {
        return musicianRepository.save(musician);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musician> updateMusician(@PathVariable Long id, @RequestBody Musician musician) {
        return musicianRepository.findById(id)
                .map(existingMusician -> {
                    existingMusician.setFirstName(musician.getFirstName());
                    existingMusician.setLastName(musician.getLastName());
                    existingMusician.setBio(musician.getBio());
                    existingMusician.setMusicStyle(musician.getMusicStyle());
                    existingMusician.setConcerts(musician.getConcerts());
                    return ResponseEntity.ok(musicianRepository.save(existingMusician));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Musician> deleteMusician(@PathVariable Long id) {
        return musicianRepository.findById(id)
                .map(existingMusician -> {
                    musicianRepository.delete(existingMusician);
                    return ResponseEntity.ok(existingMusician);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
*/
