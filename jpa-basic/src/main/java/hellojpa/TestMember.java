package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//@Entity
//@Table(uniqueConstraints = ) // 유니크 조건은 이걸로 줄것 그래야 오류났을때 바로 알아봄
public class TestMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", updatable = false) // DB 쿼리 나갈떄 변경할거야 말거야 -> 안해
    private String name;

//    @Column(nullable = false) // not null 조건
    private Integer age;


    // 순서저장
    @Enumerated(EnumType.ORDINAL)
    // string을 저장
//    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    private LocalDate createDate2;
    private LocalDateTime createDate3;
//    @Transient
//    private int test;


    public TestMember() {

    }

    public TestMember(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateDate2(LocalDate createDate2) {
        this.createDate2 = createDate2;
    }

    public void setCreateDate3(LocalDateTime createDate3) {
        this.createDate3 = createDate3;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreateDate2() {
        return createDate2;
    }

    public LocalDateTime getCreateDate3() {
        return createDate3;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
