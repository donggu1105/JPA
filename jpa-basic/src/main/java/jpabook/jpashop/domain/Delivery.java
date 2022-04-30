package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    private String city;
    private String street;
    private String zipCode;
    private DeliveryStatus status;

}
