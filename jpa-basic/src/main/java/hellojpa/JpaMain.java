package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // jpa에서는 꼭 트랜잭션 안에서 작업해줘야함 위에가 안되는 이유
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // code
        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("bonggu");

//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("hellojpa");
//            System.out.println(findMember);

            // jqpl -> 엔티티 대상으로 쿼리 , 장점 데이터베이스 바꿔도됨
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(8).getResultList();

            for (Member member : result) {
                System.out.println(member);
            }

//            em.persist(findMember);
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
