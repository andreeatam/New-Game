package Service;

import Model.User;
import Repository.UserRepository;

public class UserService implements IUserService{
    private final UserRepository userRepository;
    private String loggedInUsername;

    public UserService(){
        this.userRepository= new UserRepository();
        this.loggedInUsername=null;
    }

    @Override
    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUsername = username;
            return true;
        }
        return false;
    }

    @Override
    public boolean register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        return userRepository.save(user);
    }

    @Override
    public String getLoggedInUsername() {
        return loggedInUsername; // Returnăm numele de utilizator al utilizatorului autentificat
    }

    @Override
    public int getTokensForUser(String username) {
        return userRepository.getTokensByUsername(username);
    }

    @Override
    public void updateTokens(String username, int newTokens) {
            if (loggedInUsername != null && loggedInUsername.equals(username)) { // Verifica daca utilizatorul este autentificat
                User user = userRepository.findByUsername(username);
                if (user != null) {
                    userRepository.updateTokens(username, newTokens);
                } else {
                    System.out.println("Eroare: Utilizatorul nu există în baza de date.");
                }
            } else {
                System.out.println("Eroare: Utilizatorul nu este autentificat.");
            }
        }
}

