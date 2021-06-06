package inu.appcenter.study3_1.repository;

import inu.appcenter.study3_1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("select distinct m from Member m left join fetch m.roles where m.id =:memberId")
    Optional<Member> findWithRolesById(@Param("memberId") Long memberId);

    //  회원 조회할 때 회원 주문목록까지 같이 조회
    @Query("select distinct m from Member m left join fetch m.orderList o left join fetch o.product where m.id =:memberId")
    Optional<Member> findWithOrderListById(@Param("memberId") Long memberId);

    //  회원 리스트 조회할 때 모든 회원의 주문 목록까지 같이 조회
    //  결과가 여러개이기 때문에 Optional이 아닌 List로 선언해야 함
//    @Query("select distinct m from Member m left join fetch m.orderList")
//    List<Member> findWithOrderListAll();


}
