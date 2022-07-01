package jpabook.jpashop.repository.simplequery;

import jpabook.jpashop.repository.SimpleOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<SimpleOrderDto> findOrderDtos() {

        return em.createQuery("select new jpabook.jpashop.repository.SimpleOrderDto(o) " +
                        "from Order o " +
                        "join fetch o.member m " +
                        "join fetch o.delivery d", SimpleOrderDto.class)
                .getResultList();
        }
}
