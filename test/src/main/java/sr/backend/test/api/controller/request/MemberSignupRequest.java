package sr.backend.test.api.controller.request;

import jakarta.validation.constraints.NotEmpty;
import sr.backend.test.api.service.request.MemberSignupServiceRequest;

public record MemberSignupRequest(
    @NotEmpty(message = "memberId must not be null")
    String memberId,
    @NotEmpty(message = "password must not be null")
    String password
) {
    public MemberSignupServiceRequest toService() {
        return new MemberSignupServiceRequest(memberId, password);
    }
}
