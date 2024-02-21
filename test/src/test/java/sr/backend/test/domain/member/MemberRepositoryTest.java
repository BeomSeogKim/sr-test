package sr.backend.test.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sr.backend.test.api.controller.request.MemberSignupRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("findByMemberId는 회원의 아이디를 통해 회원을 조회할 수 있다.")
    @Test
    void findByMemberId() {
        // given
        Member member = Member.of("kbs4520", "password");
        memberRepository.save(member);

        // when
        Optional<Member> optionalMember = memberRepository.findByMemberId("kbs4520");

        // then
        assertThat(optionalMember).isNotEmpty();
    }


}