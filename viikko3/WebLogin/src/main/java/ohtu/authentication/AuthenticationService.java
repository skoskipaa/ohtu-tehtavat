package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (username.length()<3 ) {
            status.addError("username should have at least 3 characters");
        }
        
        if (!username.matches("[a-z]+")) {
            status.addError("username should consist only of letters a-z");
        }
        
        if (password.length() < 8) {
            status.addError("password should have at least 8 characters");
        }
        
        if (checkIfPasswordContainsOnlyLetters(password)) {
            status.addError("password can not contain only letters");
        }
        
        if (!password.equals(passwordConfirmation)) {
            status.addError("password and password confirmation do not match");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }
    
     private boolean checkIfPasswordContainsOnlyLetters(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i)) == false) {
                return false;
            }
        }
        
        return true;
    }

}
