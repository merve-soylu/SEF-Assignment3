import java.util.ArrayList;
// enum for status of user {LOGGEDIN, LOGGEDOUT}
    enum LoginStatus {
    LOGGEDIN,
    LOGGEDOUT
}

// User Class
public class User {
    private String username;
    private String password;
    private UserStatus status = UserStatus.NORMAL;
    private LoginStatus loginStatus = LoginStatus.LOGGEDOUT;
    protected boolean initiateResetProcess = false;

    // enum for status of user {BANNED, FROZEN, NORMAL}
    public enum UserStatus {
        BANNED, FROZEN, NORMAL
    }

    // enum for status of user {LOGGEDIN, LOGGEDOUT}
    public enum LoginStatus {
        LOGGEDIN, LOGGEDOUT
    }
    
    // set status of user
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    // get status of user
    public UserStatus getStatus() {
        return status;
    }

     // set status of user
    public void setLoginStatus(LoginStatus loginStatus) {
        // If user is attempting to login preform given logic
        this.loginStatus = loginStatus;
        if (loginStatus == LoginStatus.LOGGEDIN) {
            Website.login(username, password)
        }
        // If user is attempting to logout preform given logic
        else {
            Website.logout(User user)
        }
    }

    // get loginStatus of user
    public LoginStatus getLoginStatus() {
        return loginStatus;
    }
    
    // constructor for user
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getter for username
    public String getUsername() {
        return username;
    }

    // getter for password
    public String getPassword() {
        return password;
    }

    // reset password
    public void initiatePasswordReset() {
        this.initiateResetProcess = true;
    }

    // reset password
    public void resetPassword(String newPassword) {
        // check if reset password is initiated
        if(initiateResetProcess) {
            this.password = newPassword;
            this.initiateResetProcess = false;
        }
    }

}

// LoginService Class
public class LoginService {
    // method to authenticate user
    public boolean verification(String username, String password) {
        for(User user: Database.users) {
            // Check if the user is in database
            if(user.getUsername().equals(username)) {
                // Check if user is Banned or Frozen
                if(user.getStatus() == User.UserStatus.NORMAL) {
                        // Check if username and password are correct for User
                        if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                            // Return verified
                            return true;
                        }
                }
            }
        }
        // Return unverified
        return false;
    }

    // reset password
    public void resetPassword(User user, String newPassword) {
        user.password = newPassword;
    }

    // Login user
    public void preformLogin(String username, String password) {
        // preform logic to login user to website
    }

    // Logout user
    public void preformLogout(User user) {
        // preform logic to logout user from website
    }
}

public class WebsiteUI {
    LoginService loginService = new LoginService();
    
    // method to initiate user login
    public LoginStatus login(String username, String password) {
        if(loginService.verification(username, password)) {
            loginService.preformLogin(username, password);
            System.out.println("User logged in successfully");
             return LoginStatus.LOGGEDIN;
        } else {
            System.out.println("Invalid username or password");
            return LoginStatus.LOGGEDOUT;
        }
    }

    // method to initiate user logout
    public LoginStatus logout(User user) {
        loginService.preformLogout(user);
        System.out.println("User logged out successfully");
        return LoginStatus.LOGGEDOUT;
    }

    // method to initiate password reset
    public void initiatePasswordReset(User user) {
        EmailService.sendEmail(user, "Reset password instructions");
        System.out.println("Password reset instructions sent to email");
    }

    // method to reset password in database
    public void resetPassword(User user, String newPassword) {
        for(User user: Database.users){
            if(u.getUsername().equals(u.getUsername())){
                LoginService.resetPassword(u, newPassword);
            }
        }
        System.out.println("Password reset successfully");
    }
}

// Database Class
class Database {
    // array of users
    static ArrayList<User> users = new ArrayList<>(/*All the users*/);
}

// EmailService Class
class EmailService {
    // method to send email
    public void sendEmail(User user, String resetPasswordInstructions) {
        user.initiatePasswordReset();
        System.out.println("Email sent to " + user.getUsername() + " with instructions: " + resetPasswordInstructions);
    }
}
