package my_project.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import my_project.dto.ConcertDTO;
import my_project.model.Concert;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {

    //@Query("SELECT c FROM Concert c LEFT JOIN FETCH c.musicians WHERE c.id = :id")
    //Optional<Concert> findByIdWithMusicians(Long id);

    //@Query("SELECT c FROM Concert c LEFT JOIN FETCH c.musicians")
    //List<Concert> findAllWithMusicians();
}
