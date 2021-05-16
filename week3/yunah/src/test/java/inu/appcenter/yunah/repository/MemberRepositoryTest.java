package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Test
    void MemberTest() {
        Member member = Member.createMember("jyajoo1020@gmail.com", "정윤아", "1111");
        Member savedMember = memberRepository.save(member);

        assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());
    }

    @Test
    void memberSearch() {
        Member member = memberRepository.findById(1L).get();

        assertThat(member.getStatus()).isEqualTo(Status.ACTIVE);
    }

    @Test
    void search() {
        memberRepository.findWithOrderAll();
    }

}