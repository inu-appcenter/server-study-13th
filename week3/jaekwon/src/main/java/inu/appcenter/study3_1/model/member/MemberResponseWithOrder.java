package inu.appcenter.study3_1.model.member;

import inu.appcenter.study3_1.domain.Member;
import inu.appcenter.study3_1.domain.MemberStatus;
import inu.appcenter.study3_1.model.order.OrderDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponseWithOrder {

    private Long id;
    private String email;
    private String name;
    private MemberStatus status;
    private List<OrderDTO> orders;

    public MemberResponseWithOrder(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.status = member.getStatus();
        this.orders = member.getOrderList().stream()
                .map(order -> new OrderDTO(order))
                .collect(Collectors.toList());
    }
}
