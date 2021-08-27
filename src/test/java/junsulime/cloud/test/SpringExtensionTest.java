package junsulime.cloud.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class SpringExtensionTest {
    @MockBean
    private UserService userService;

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(userService);
    }

    @Test
    void getFoo() {
        given(userService.getSameThing("foo")).willReturn("foo");

        assertEquals(accountService.foo(), "foo");
    }
}
