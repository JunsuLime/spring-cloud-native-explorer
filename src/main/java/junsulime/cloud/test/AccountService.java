package junsulime.cloud.test;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final UserService userService;

    public AccountService(UserService userService) {
        this.userService = userService;
    }

    public String foo() {
        return userService.getSameThing("foo");
    }
}
