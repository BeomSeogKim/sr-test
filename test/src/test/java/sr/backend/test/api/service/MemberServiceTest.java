package sr.backend.test.api.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sr.backend.test.api.controller.request.MemberSignupRequest;
import sr.backend.test.api.service.request.MemberSignupServiceRequest;
import sr.backend.test.common.password.PasswordEncoder;
import sr.backend.test.domain.member.Member;
import sr.backend.test.domain.member.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @DisplayName("signup을 할 경우 회원이 정상적으로 저장되어야 한다.")
    @Test
    void signup() {
        // given
        MemberSignupServiceRequest request = new MemberSignupServiceRequest("kbs4520", "password");

        // when
        Long id = memberService.signup(request);

        // then
        Member findMember = memberRepository.findById(id).orElseThrow();

        assertAll(
            () -> assertThat(findMember.getMemberId()).isEqualTo("kbs4520"),
            () -> assertThat(findMember.getPassword()).isNotEqualTo("password"),
            () -> assertThat(PasswordEncoder.checkPassword("password", findMember.getPassword())).isTrue()
        );
    }

    @DisplayName("회원 가입시 이미 회원 아이디가 존재 할 IllegalArgumentException이 발생해야 한다.")
    @Test
    void signup_duplicate_memberId() {
        // given
        memberRepository.save(Member.of("kbs4520", "password"));
        MemberSignupServiceRequest request = new MemberSignupServiceRequest("kbs4520", "password");

        // when && then
        assertThatThrownBy(() ->memberService.signup(request))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("memberId already exist");
    }
}