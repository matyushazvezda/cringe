package my_project.dto;

import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateConDTO {
    private Long id;
    private String name;
    private String location;
    private double ticketPriceS;
    private double ticketPriceV;
    private Date date;
    private String time;
    private Set<Long> musicianIds;

    public UpdateConDTO() {
    }

    
}


