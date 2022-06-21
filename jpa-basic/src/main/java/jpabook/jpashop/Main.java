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

public class Main {


    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // jpa에서는 꼭 트랜잭션 안에서 작업해줘야함 위에가 안되는 이유
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
        // code
            Member member = new Member();
            member.setName("member1");
            member.setAddress(new Address("1", "2", " 3"));

            member.getFavoriteFoods().add("프로틴");
            member.getFavoriteFoods().add("닭가슴살");
            member.getFavoriteFoods().add("김");

            member.getAddressHistory().add(new Address("4", "5", " 6"));
            member.getAddressHistory().add(new Address("7", "8", " 9"));
            em.persist(member);

            em.flush();
            em.clear();


            System.out.println("=========start=========");
            Member findMember = em.find(Member.class, member.getId());

            findMember.setAddress(new Address("new1", "new2", "new3"));

            findMember.getFavoriteFoods().remove("프로틴");
            findMember.getFavoriteFoods().add("한식");


            findMember.getAddressHistory().remove(new Address("new1", "new2", "new3"));
            findMember.getAddressHistory().add(new Address("add1", "add2", "add3"));

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
