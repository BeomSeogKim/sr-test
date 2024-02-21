package sr.backend.test.api.service;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sr.backend.test.api.service.request.MemberLoginServiceRequest;
import sr.backend.test.api.service.request.MemberSignupServiceRequest;
import sr.backend.test.common.password.PasswordEncoder;
import sr.backend.test.common.password.TokenProvider;
import sr.backend.test.domain.member.Member;
import sr.backend.test.domain.member.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Long signup(MemberSignupServiceRequest request) {
        validateMemberId(request);

        Member savedMember = memberRepository.save(
            Member.of(request.memberId(), PasswordEncoder.encryptPassword(request.password()))
        );

        return savedMember.getId();
    }

    private void validateMemberId(MemberSignupServiceRequest request) {
        if (memberRepository.findByMemberId(request.memberId()).isPresent()) {
            throw new IllegalArgumentException("memberId already exist");
        }
    }

    public String login(MemberLoginServiceRequest request) {
        Member member = validateMemberId(request);
        validatePassword(request, member);
        return tokenProvider.createToken(member.getMemberId());
    }

    private Member validateMemberId(MemberLoginServiceRequest request) {
        return memberRepository.findByMemberId(request.memberId()).orElseThrow(
            () -> new IllegalArgumentException("invalid login id")
        );
    }

    private static void validatePassword(MemberLoginServiceRequest request, Member member) {
        if (!PasswordEncoder.checkPassword(request.password(), member.getPassword())) {
            throw new IllegalArgumentException("password does not match");
        }
    }
}
