package inu.appcenter.chanhee.repository;

import inu.appcenter.chanhee.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    // 회원 리스트를 조회할 때 모든 회원의 주문목록까지 같이 조회
    // MemberService 에서
    // List<Member> members = memberRepository.findWithOrderListAll();
    // 위 코드에서 List<Member>로 members를 생성했기 때문에 Optional<Member>이 아닌 List<Member>로 설정해줘야한다.
    @Query("select distinct m from Member m join fetch m.orderList")
    List<Member> findWithOrderListAll();

    // 회원을 조회할 때 회원의 주문목록까지 같이 조회
    // 일대다 조회 -> 일대다 조인이 발생
    @Query("select distinct m from Member m join fetch m.orderList where m.id=:memberId")
    Optional<Member> findWithOrderListById(@Param("memberId") Long memberId);
}
