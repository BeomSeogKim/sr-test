package sr.backend.test.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sr.backend.test.api.controller.request.MemberSignupRequest;
import sr.backend.test.api.service.MemberService;
import sr.backend.test.common.ApiResponse;

import java.net.URI;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @PostMapping(value = "/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody @Valid MemberSignupRequest memberSignupRequest) {
        Long id = memberService.signup(memberSignupRequest.toService());

        return ResponseEntity
            .created(URI.create("/api/member/signup" + id))
            .body(ApiResponse.created("signup request approved"));
    }
}
