package sr.backend.test.api.service.request;

public record MemberSignupServiceRequest(
    String memberId,
    String password
) {

}
