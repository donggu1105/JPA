package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.SimpleOrderDto;
import jpabook.jpashop.repository.simplequery.OrderSimpleQueryRepository;
import jpabook.jpashop.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
// xToOne (ManyToOne, OneToOne)
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        List<SimpleOrderDto> result   = orders.stream()
                .map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());

        return result;
    }


    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> orderV3() {

        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result   = orders.stream()
                .map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v4/simple-orders")
    public List<SimpleOrderDto> orderV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }



}
