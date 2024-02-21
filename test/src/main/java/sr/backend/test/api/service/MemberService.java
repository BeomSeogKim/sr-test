package sr.backend.test.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sr.backend.test.api.service.request.MemberSignupServiceRequest;
import sr.backend.test.common.password.PasswordEncoder;
import sr.backend.test.domain.member.Member;
import sr.backend.test.domain.member.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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
}
