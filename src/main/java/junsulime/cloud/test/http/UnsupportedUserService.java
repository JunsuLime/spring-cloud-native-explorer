package junsulime.cloud.test.http;

import junsulime.cloud.test.UserService;

public class UnsupportedUserService implements UserService {
    @Override
    public String getSameThing(String data) {
        throw new UnsupportedOperationException();
    }
}
