package inu.appcenter.chanhee.model.member;

import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.domain.member.MemberStatus;
import inu.appcenter.chanhee.model.order.OrderDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponse {

    private Long id;

    private String email;

    private String password;

    private String name;

    private MemberStatus status;

    private List<OrderDto> orders;

    public MemberResponse (Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.status = member.getStatus();
        this.orders = member.getOrderList().stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
    }
}
