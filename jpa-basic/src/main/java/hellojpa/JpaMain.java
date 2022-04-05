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
            // 트랜잭션을 지원하는 쓰기 지연
//            Member member1 = new Member(160L, "A");
//            Member member2 = new Member(161L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("=========");


//             dirtch checking (변경감지)
//            Member member = em.find(Member.class, 150L);
//            member.setName("changed A");


//             flush - 1차캐시는 유지 , 쓰기지연SQL 저장소 쿼리들만 보내서 DB에 반영함
//            Member member = new Member(200L, "test");
//
//            em.persist(member);
//            em.flush();


            Member member = new Member();
            member.setId(1L);
            member.setName("test");
            member.setRoleType(RoleType.ADMIN);

            em.persist(member);
            System.out.println("========");
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
