package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.model.member.MemberResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    // 일 대 다 조회 ( 회원을 조회하면, 주문까지 조회된다.)
    @Query("select distinct m from Member m join fetch m.orderList where m.id = :memberId")
    Optional<Member> findWithOrderById(@Param("memberId") Long memberId);

    // 회원 전체 목록을 조회하면, 각 회원의 주문 목록까지 조회
    // @Query("select distinct m from Member m join fetch m.orderList")
    // Optional<Member> findWithOrderAll();
    // 오류 발생 -> List 타입으로 변경해주어 오류를 고쳐주었습니다.
    // List<Member> findWithOrderAll();

    @Query("select distinct m from Member m join fetch m.orderList")
    List<MemberResponse> findWithOrderAll();

}
