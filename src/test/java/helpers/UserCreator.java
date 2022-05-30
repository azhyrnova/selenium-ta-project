package helpers;

import model.User;

public class UserCreator {

    public static final String TESTDATA_USER_NAME = ConfigurationManager.getProperty("test_data_user_name");
    public static final String TESTDATA_USER_PASSWORD = ConfigurationManager.getProperty("test_data_user_password");
    public static final String LOCKEDOUT_USER_NAME = ConfigurationManager.getProperty("lockedout_user_name");

    public static User withStandardCredentials() {
        return new User(TESTDATA_USER_NAME, TESTDATA_USER_PASSWORD);
    }

    public static User withWrongCredentials() {
        return new User(TESTDATA_USER_NAME, "wrongPassword");
    }

    public static User lockedOut() { return new User(LOCKEDOUT_USER_NAME, TESTDATA_USER_PASSWORD);}
}
