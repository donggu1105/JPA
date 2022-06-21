package jpabook.jpashop;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
        try{
        // code
//            Member member = new Member();
//            member.setName("kim1");
//
//            em.persist(member);
//
//            String qlString = "select m from Member m where m.name like '%kim%'";
//            List<Member> findMembers = em.createQuery(
//                    qlString, Member.class).getResultList();
//
//            for (Member m : findMembers) {
//                System.out.println(m.getName());
//            }

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"), "kim"));
            List<Member> resultList = em.createQuery(cq).getResultList();



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
