package helpers;

import model.User;

public class UserCreator {

    public static final String TESTDATA_USER_NAME = "standard_user";
    public static final String TESTDATA_USER_PASSWORD = "secret_sauce";
    public static final String LOCKEDOUT_USER_NAME = "locked_out_user";

    public static User withStandardCredentials() {
        return new User(TESTDATA_USER_NAME, TESTDATA_USER_PASSWORD);
    }

    public static User withWrongCredentials() {
        return new User(TESTDATA_USER_NAME, "wrongPassword");
    }

    public static User lockedOut() { return new User(LOCKEDOUT_USER_NAME, TESTDATA_USER_PASSWORD);}
}
