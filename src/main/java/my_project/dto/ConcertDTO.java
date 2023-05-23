package my_project.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
public class ConcertDTO {
    private Long id;
    private String name;
    private String location;
    private double ticketPriceS;
    private double ticketPriceV;
    private Instant date;
    private Set<MusicianDTO> musicians;

    public ConcertDTO() {
    }
}
