package sr.backend.test.api.controller.request;

import jakarta.validation.constraints.NotEmpty;
import sr.backend.test.api.service.request.MemberLoginServiceRequest;

public record MemberLoginRequest(
    @NotEmpty(message = "memberId must not be null")
    String memberId,
    @NotEmpty(message = "password must not be null")
    String password
) {

    public MemberLoginServiceRequest toService() {
        return new MemberLoginServiceRequest(memberId, password);
    }
}
