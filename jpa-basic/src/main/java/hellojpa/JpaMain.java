package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * 영속성 컨텍스트 - 엔티티를 영구 저장하는 환경
 * 장점 : 1차 캐시
 * 동일성 보장
 * 트랜잭션을 지원하는 쓰기 지연
 * 변경 감지 (더티 체킹)
 * 지연 로딩
 * */

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // jpa에서는 꼭 트랜잭션 안에서 작업해줘야함 위에가 안되는 이유
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // code
        try {

            Team team = new Team();
            team.setName("team1");
            em.persist(team);
            TeamMember teamMember = new TeamMember();
            teamMember.setName("test");
            teamMember.setTeam(team);
            em.persist(teamMember);


            em.flush();
            em.clear();

            TeamMember m = em.find(TeamMember.class, teamMember.getId());

            System.out.println("m = " + m.getTeam().getClass());

            List<TeamMember> members = em.createQuery("select m from Member m", TeamMember.class).getResultList();



            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
