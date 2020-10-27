package me.doyun.springwebsecurity.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;  //가짜 유저를 만들기 위해 엮어줌

    @Test   //로그인을 하지않고 인덱스 페이지에 접근했을 경우
//    @WithAnonymousUser
    public void index_annonymous() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
//    @WithMockUser(username = "doyun", roles = "USER") //with()메소드 대신 어노테이션을 써도 테스트 가능
    public void index_user() throws Exception {
        //이미 데이터베이스에 유저가 있는 상태가 아니라, 이런 유저가 있을 것이라는 가정 하에 테스트 진행
        mockMvc.perform(MockMvcRequestBuilders.get("/").with(user("doyun").roles("USER")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void admin_user() throws Exception {
        //어드민 페이지에 일반 유저가 접근할 경우 테스트
        mockMvc.perform(MockMvcRequestBuilders.get("/admin").with(user("doyun").roles("USER")))
                .andDo(print())
                .andExpect(status().isForbidden()); //접근이 금지되었을것이라고 예상하고 테스트 작성
    }

    @Test
    public void admin_admin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin").with(user("doyun").roles("ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {

        String username = "doyun";
        String password = "123";
        //가짜유저 만들어서 받아오기
        Account user = this.createUser(username, password);

        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user(username).password(password))
                .andExpect(authenticated());
    }

    //테스트용 가짜 계정 만들기
    private Account createUser(String username, String password) {

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole("USER");
        return accountService.createNew(account);
    }

}
