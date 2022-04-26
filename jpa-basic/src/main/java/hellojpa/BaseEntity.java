package hellojpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {


    @Column(name = "INSERT_MEMBER")
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifedBy;
    private LocalDateTime lasttModifiedDate;

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifedBy(String lastModifedBy) {
        this.lastModifedBy = lastModifedBy;
    }

    public void setLasttModifiedDate(LocalDateTime lasttModifiedDate) {
        this.lasttModifiedDate = lasttModifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getLastModifedBy() {
        return lastModifedBy;
    }

    public LocalDateTime getLasttModifiedDate() {
        return lasttModifiedDate;
    }
}
