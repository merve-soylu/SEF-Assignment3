import java.util.ArrayList;
// enum for status of user {LOGGEDIN, LOGGEDOUT}
    enum LoginStatus {
    LOGGEDIN,
    LOGGEDOUT
}

// User Class
class User {
    private String username;
    protected String password;
    User user = new User(username, password);
    private UserStatus status = UserStatus.NORMAL;
    private LoginStatus loginStatus = LoginStatus.LOGGEDIN;
    protected boolean initiateResetProcess = false;

    // enum for status of user {BANNED, FROZEN, NORMAL}
    public enum UserStatus {
        BANNED, FROZEN, NORMAL
    }

    // constructor for user
    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
            WebsiteUI websiteUI = new WebsiteUI();
            this.loginStatus = websiteUI.login(username, password);
        }
        // If user is attempting to logout preform given logic
        else {
            WebsiteUI websiteUI = new WebsiteUI();
            this.loginStatus = websiteUI.logout(user);
        }
    }

    // get loginStatus of user
    public LoginStatus getLoginStatus() {
        return loginStatus;
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
    public void changePassword(String newPassword) {
        // check if reset password is initiated
        if(initiateResetProcess) {
            WebsiteUI websiteUI = new WebsiteUI();
            this.password = newPassword;
            websiteUI.resetPassword(user, password)
            this.initiateResetProcess = false;
        }
    }

}

// LoginService Class
class LoginService {
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
    public void callUpdatePassword(User user, String newPassword) {
        Database database = new Database();
        database.updatePassword(user, newPassword);
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

class WebsiteUI {
    LoginService loginService = new LoginService();
    EmailService emailService = new EmailService();
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
        emailService.sendEmail(user, "Reset password instructions");
        System.out.println("Password reset instructions sent to email");
    }

    // method to reset password in database
    public void resetPassword(User user, String newPassword) {
        for(User user: Database.users){
            if(user.getUsername().equals(user.getUsername())){
                loginService.callUpdatePassword(user, newPassword);
            }
        }
        System.out.println("Password reset successfully");
    }
}

// Database Class
class Database {
    // array of users
    static ArrayList<User> users = new ArrayList<>(/*All the users*/);

    public void updatePassword(User user, String newPassword) {
        for(User user: Database.users){
                if(user.getUsername().equals(user.getUsername())){
                // implement logic for changing password in database
                }
        }
    }
}

// EmailService Class
class EmailService {
    // method to send email
    public void sendEmail(User user, String resetPasswordInstructions) {
        user.initiatePasswordReset();
        System.out.println("Email sent to " + user.getUsername() + " with instructions: " + resetPasswordInstructions);
    }
}
