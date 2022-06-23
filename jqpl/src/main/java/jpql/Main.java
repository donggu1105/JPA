package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // jpa에서는 꼭 트랜잭션 안에서 작업해줘야함 위에가 안되는 이유
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);

            for (int i = 0; i < 100; i++) {

                Member member = new Member();
                member.setAge(i);
                member.setUsername(null);
    //            member.setTeam(team);
                em.persist(member);
            }

            em.flush();
            em.clear();

            String query = "select coalesce(m.username, '이름없는 회원') from Member m";
            List<String> members = em.createQuery(query).getResultList();
            for (String m : members) {
                System.out.println(m);
            }



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
