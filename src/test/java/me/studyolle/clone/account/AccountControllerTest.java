package me.studyolle.clone.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMVC;

    @DisplayName("회원 가입을 하면 해당 sign-up 페이지로 이동을 성공한다.")
    @Test
    void signUpForm() throws Exception {
        mockMVC.perform(get("/sign-up"))
               .andExpect(status().isOk())
               .andExpect(view().name("account/sign-up"))
               .andExpect(model().attributeExists("signUpForm"))
               .andDo(print()) // 타임리프이기 때문에 뷰 렌더링을 서블릿 컨테이너가 하지 않는다.
               ;
    }
}
