package Service;

import Model.User;

public interface IUserService {
    boolean login(String username, String password);
    boolean register(User user);
    int getTokensForUser(String username);
    void updateTokens(String username, int newTokens);
    String getLoggedInUsername();
}
