package jaas;

import javax.security.auth.callback.*;
import java.io.IOException;

public class SimpleCallbackHandler implements CallbackHandler {

    private String username;
    private  char[] password;

    public SimpleCallbackHandler(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback c) c.setName(username);
            else if (callback instanceof PasswordCallback p) p.setPassword(password);
        }
    }
}
