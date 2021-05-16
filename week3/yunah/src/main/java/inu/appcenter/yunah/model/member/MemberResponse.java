package inu.appcenter.yunah.model.member;
import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.status.MemberStatus;
import inu.appcenter.yunah.model.order.OrderDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponse {

    private Long memberId;

    private String email;

    private String name;

    private String password;

    private MemberStatus status;

    private List<OrderDto> orders;

    public MemberResponse(Member member) {

        this.memberId = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.password = member.getPassword();
        this.status = member.getStatus();
        this.orders = member.getOrderList().stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
    }
}
