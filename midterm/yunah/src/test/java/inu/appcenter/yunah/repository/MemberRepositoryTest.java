package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Category;
import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.Post;
import inu.appcenter.yunah.repository.query.MemberQueryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("회원 - 게시글(삭제 상태 x) 같이 조회")
    void test() {
        Member member = Member.createMember("aaa@naver.com", "1111", "회원a", 20);
        memberRepository.save(member);

        Category category = Category.saveCategory("가입 인사");
        Post post = Post.savePost("안녕", "하세요", member, category);
        Post post2 = Post.savePost("안녕", "하세요", member, category);
        postRepository.save(post);
        postRepository.save(post2);

        em.flush();
        em.clear();

        Member memberById = memberQueryRepository.findMemberById(member.getId());
        assertThat(memberById.getPostList().size()).isEqualTo(2);

    }
}