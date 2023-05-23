package my_project.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my_project.dto.MusicianDTO;
import my_project.model.Musician;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {

}