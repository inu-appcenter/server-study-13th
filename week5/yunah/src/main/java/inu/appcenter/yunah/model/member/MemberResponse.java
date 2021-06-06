package inu.appcenter.yunah.model.member;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.status.MemberStatus;
import inu.appcenter.yunah.model.order.OrderResponse;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponse {

    private Long memberId;
    private String email;
    private String name;
    private MemberStatus status;

    List<OrderResponse> orderList;

    public static MemberResponse from(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.memberId = member.getId();
        memberResponse.email = member.getEmail();
        memberResponse.name = member.getName();
        memberResponse.status = member.getStatus();
        memberResponse.orderList = member.getOrderList().stream().map(order -> OrderResponse.from(order)).collect(Collectors.toList());
        return memberResponse;
    }
}
