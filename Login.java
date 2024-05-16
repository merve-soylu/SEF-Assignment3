import java.util.ArrayList;

class User {
    private String username;
    private String password;
    private UserStatus status = UserStatus.NORMAL;
    protected boolean initiateResetProcess = false;

    // enum for status of user {BANNED, FROZEN, NORMAL}
    public enum UserStatus {
        BANNED, FROZEN, NORMAL
    }

    // set status of user
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    // get status of user
    public UserStatus getStatus() {
        return status;
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

class LoginService {
    // method to authenticate user
    public boolean authenticateUser(String username, String password) {

        // Check if user is Banned or Frozen
        for(User user: Database.users) {
            if(user.getUsername().equals(username)) {
                if(user.getStatus() != User.UserStatus.NORMAL) {
                    return false;
                }
            }
        }

        // check if user exists
        if(userExists(username)) {
            // check if password is correct
            if(isPasswordCorrect(username, password)) {
                return true;
            }
        }
        
        return false;
    }

    // method to log user out
    public void logoutUser(User user) {
        // logic to log user out
    }

    public boolean userExists(String username) {
        // if the user exists in database
        for(User user: Database.users) {
            if(user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    // check if password is correcrt for the username
    public boolean isPasswordCorrect(String username, String password) {
        for(User user: Database.users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    // reset password
    public void resetPassword(User user, String newPassword) {
        user.resetPassword(newPassword);
    }


}

class WebsiteUI {

    // method to initiate user login
    public void initiateLogin(String username, String password) {
        LoginService login = new LoginService();
        if(login.authenticateUser(username, password)) {
            System.out.println("User logged in successfully");
        } else {
            System.out.println("Invalid username or password");
        }
    }

    // method to initiate user logout
    public void initiateLogout(User user) {
        LoginService login = new LoginService();
        login.logoutUser(user);
        System.out.println("User logged out successfully");
    }

    // method to initiate password reset
    public void initiatePasswordReset(User user) {
        user.initiatePasswordReset();
        EmailService emailService = new EmailService();
        emailService.sendEmail(user.getUsername(), "Reset password instructions");
        System.out.println("Password reset instructions sent to email");
    }

    // method to reset password in database 
    public void resetPassword(User user, String newPassword) {
        LoginService login = new LoginService();
        
        for(User u: Database.users){
            if(u.getUsername().equals(u.getUsername())){
                login.resetPassword(u, newPassword);
            }
        }
        System.out.println("Password reset successfully");
    }

    

}

class Database {

    // array of users
    static ArrayList<User> users = new ArrayList<>();

}

class EmailService {
    private String email;
    private String resetPasswordInstructions;

    // method to send email
    public void sendEmail(String email, String resetPasswordInstructions) {
        this.email = email;
        this.resetPasswordInstructions = resetPasswordInstructions;
        System.out.println("Email sent to " + email + " with instructions: " + resetPasswordInstructions);
    }

}
