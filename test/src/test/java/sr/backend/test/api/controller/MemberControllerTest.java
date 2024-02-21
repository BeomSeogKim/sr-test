package sr.backend.test.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sr.backend.test.api.controller.request.MemberSignupRequest;
import sr.backend.test.api.service.MemberService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MemberControllerTest {

    @MockBean
    MemberService memberService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @DisplayName("회원가입 시 아이디는 null 이어서는 안된다.")
    @Test
    void signup_id_null() throws Exception {
        // given
        MemberSignupRequest request = new MemberSignupRequest(null, "password");

        when(memberService.signup(any())).thenReturn(1L);

        mockMvc.perform(
            post("/api/member/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @DisplayName("회원가입 시 아이디는 빈 문자열이어서는 안된다.")
    @Test
    void signup_id_empty() throws Exception {
        // given
        MemberSignupRequest request = new MemberSignupRequest("", "password");

        when(memberService.signup(any())).thenReturn(1L);

        mockMvc.perform(
            post("/api/member/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @DisplayName("회원가입 시 비밀번호는 null 이어서는 안된다.")
    @Test
    void signup_password_null() throws Exception {
        // given
        MemberSignupRequest request = new MemberSignupRequest("kbs4520", null);

        when(memberService.signup(any())).thenReturn(1L);

        mockMvc.perform(
            post("/api/member/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @DisplayName("회원가입 시 비밀번호는 빈 문자열이어서는 안된다.")
    @Test
    void signup_password_empty() throws Exception {
        // given
        MemberSignupRequest request = new MemberSignupRequest("kbs4520", "");

        when(memberService.signup(any())).thenReturn(1L);

        mockMvc.perform(
            post("/api/member/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }
}