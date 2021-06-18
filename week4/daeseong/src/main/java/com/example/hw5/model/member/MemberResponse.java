package com.example.hw5.model.member;

import com.example.hw5.domain.Member;
import com.example.hw5.model.order.OrderDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponse {

    private String email;
    private String name;
    private String password;
    private List<OrderDTO> orderList;

    public MemberResponse(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.password = member.getPassword();

        this.orderList = member.getOrderList().stream().map(OrderDTO::new).collect(Collectors.toList());
    }
}
