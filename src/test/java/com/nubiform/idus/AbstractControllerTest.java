package com.nubiform.idus;

import com.nubiform.idus.api.auth.model.Auth;
import com.nubiform.idus.api.auth.service.AuthService;
import com.nubiform.idus.config.error.ErrorControllerAdvice;
import com.nubiform.idus.config.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.security.config.BeanIds;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletException;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
public abstract class AbstractControllerTest {

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mockMvc;

    abstract protected Object controller();

    @MockBean
    protected AuthService authService;

    @Autowired
    protected JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    private void setup() throws ServletException {
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.init(new MockFilterConfig(context.getServletContext(), BeanIds.SPRING_SECURITY_FILTER_CHAIN));

        mockMvc = MockMvcBuilders.standaloneSetup(controller())
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .setControllerAdvice(new ErrorControllerAdvice())
                .addFilter(delegatingFilterProxy)
                .alwaysDo(print())
                .build();

        Auth auth = new Auth();
        auth.setMemberId("user");
        auth.setRoles("ROLE_USER");
        when(authService.getAuth("user")).thenReturn(auth);
        auth = new Auth();
        auth.setMemberId("admin");
        auth.setRoles("ROLE_ADMIN");
        when(authService.getAuth("admin")).thenReturn(auth);
        auth = new Auth();
        auth.setMemberId("useradmin");
        auth.setRoles("ROLE_USER,ROLE_ADMIN");
        when(authService.getAuth("useradmin")).thenReturn(auth);
    }
}
