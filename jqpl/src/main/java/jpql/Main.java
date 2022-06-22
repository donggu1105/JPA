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

            Member member = new Member();
            member.setAge(10);
            member.setUsername("test");
            em.persist(member);

            TypedQuery<Member> query1 = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query1.setParameter("username", "test");
            Member singleResult = query1.getSingleResult();
            System.out.println("singleResult: "+ singleResult.getUsername());


            Query query2 = em.createQuery("select m.username, m.age from Member m", Member.class);

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
