package my_project.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import my_project.dto.MusicianDTO;
import my_project.model.Concert;
import my_project.model.Musician;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {

    Optional<Concert> findAllById(MusicianDTO id);

}