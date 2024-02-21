package sr.backend.test.api.service.request;

import jakarta.validation.constraints.NotEmpty;

public record MemberLoginServiceRequest(
    String memberId,
    String password
) {

}
