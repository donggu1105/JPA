package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TeamMember extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    //    @Column(name = "TEAM_ID")
//    private Long teamId;
    @ManyToOne(fetch = FetchType.EAGER) // MEMBER : TEAM == N : 1  => 멤버입장에서 팀은 MANY : ONE
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    public void setId(Long id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }


    public void changeTeam(Team team) {
        this.team = team;

        // 연관관계 세팅
        team.getMembers().add(this);
    }
    public void setTeam(Team team) {

        this.team = team;

    }

    public Team getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

}
