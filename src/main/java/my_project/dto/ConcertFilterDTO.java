package my_project.dto;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConcertFilterDTO {
    private Date startDate;
    private Date endDate;
    private Date date;

    
    public ConcertFilterDTO() {
    }

    
}
