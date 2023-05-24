package my_project.dto;

import java.sql.Date;

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

    private Double minPrice;
    private Double maxPrice;
    private Double Price;

    
    public ConcertFilterDTO() {
    }

    
}
