package org.example.order_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.order_service.dto.OrderDto;
import org.example.order_service.facade.OrderFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderFacade orderFacade;

    @GetMapping
    public String greetings() {
        return "order service greetings";
    }

    @GetMapping("/{id:\\d+}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderFacade.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) throws JsonProcessingException {
        return orderFacade.create(orderDto);
    }



}
