package Repository;

import Model.User;

public interface IUserRepository {

    User findByUsername(String username);
    boolean save(User user);
    int getTokensByUsername(String username);
    void updateTokens(String username, int newTokens);

}
