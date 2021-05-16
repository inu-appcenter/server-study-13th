package inu.appcenter.study3_1.repository;

import inu.appcenter.study3_1.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Commit
    @Test
    void MemberTest(){
        Member member = Member.createMember("rjeowornjs@naver.com", "1234", "박재권");
        Member savedMember = memberRepository.save(member);

        assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());
    }
}