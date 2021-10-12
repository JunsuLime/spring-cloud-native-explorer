package junsulime.cloud.test.http;

import junsulime.cloud.test.UserService;
import org.springframework.stereotype.Service;

@Service
public class UnsupportedUserService implements UserService {
    @Override
    public String getSameThing(String data) {
        throw new UnsupportedOperationException();
    }
}
