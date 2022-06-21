package jpabook.jpashop.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.time.LocalDateTime;


@Embeddable
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
