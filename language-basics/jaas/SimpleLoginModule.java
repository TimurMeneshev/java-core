package jaas;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SimpleLoginModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> options;

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) throw new LoginException("no handler");
        var nameCall =  new NameCallback("username: ");
        var passCall = new PasswordCallback("password: ", false);

        try {
            callbackHandler.handle(new Callback[] {nameCall, passCall});
        } catch (UnsupportedCallbackException e) {
            var e2 = new LoginException("Unsupported callback");
            e2.initCause(e);
            throw e2;
        } catch (IOException e) {
            var e2 = new LoginException("IO exception in callback");
            e2.initCause(e);
            throw e2;
        }

        try {
            return checkLogin(nameCall.getName(), passCall.getPassword());
        } catch (IOException e) {
            var e2 = new LoginException();
            e2.initCause(e);
            throw e2;
        }
    }

    private boolean checkLogin(String username, char[] password) throws IOException {
        try (var in = new Scanner(Path.of("" + options.get("pwfile")), StandardCharsets.UTF_8)) {
            while (in.hasNextLine()) {
                String[] inputs = in.nextLine().split("\\|");
                if (inputs[0].equals(username) && Arrays.equals(inputs[1].toCharArray(), password)) {
                    String role = inputs[2];
                    Set<Principal> principals = subject.getPrincipals();
                    principals.add(new SimplePrincipal("username", username));
                    principals.add(new SimplePrincipal("role", role));
                    return true;
                }
            }
            return false;
        }
    }
}
