import java.util.HashMap;
import java.util.Scanner;

public class User {
    private String firstName, lastName, dateOfBirth, userName, userPassword;
    private int securityPin;
    private double balance = 0;
    private static HashMap<String, User> userHashMap = new HashMap<>();
    private static Scanner reader = new Scanner(System.in);

    // displayMenu() method to print options to user
    public void displayMenu() {
        System.out.println("Welcome to Frost Bank!\n");
        do {
            int input;
            System.out.println("Please select from the following options: ");
            System.out.println("1. Sign In\n2. Register\n3. Exit\n");
            input = reader.nextInt();
            reader.nextLine();

            switch (input) {
                case 1:
                    signIn();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    break;
            }

        } while (true);
    }

    // registerUser() method to allow new users to register
    public void registerUser() {
        User user = new User();

        System.out.println("Please Register\n");
        System.out.print("Username: ");
        user.userName = reader.nextLine();
        System.out.print("Password: ");
        user.userPassword = reader.nextLine();
        System.out.print("Security Pin: ");
        user.securityPin = reader.nextInt();
        reader.nextLine();

        System.out.print("First Name: ");
        user.firstName = reader.nextLine();
        System.out.print("Last Name: ");
        user.lastName = reader.nextLine();
        System.out.print("Date of Birth: ");
        user.dateOfBirth = reader.nextLine();

        System.out.println();

        userHashMap.put(user.userName, user);

        System.out.println("Registration Successful!\n");

    }

    // signIn() method to get user credentials
    public void signIn() {
        String inputUserPass, inputUserName;
        int inputUserPin;
        boolean loginSuccess, userNameCheck;

        System.out.println("Please sign in\n");
        System.out.println("Username: ");
        inputUserName = reader.nextLine();
        System.out.println("Password: ");
        inputUserPass = reader.nextLine();

        System.out.println("Security Pin: ");
        inputUserPin = reader.nextInt();

        reader.nextLine();

        // call userNameCheck() to validate user credentials
        userNameCheck(inputUserName, inputUserPass, inputUserPin);

    }

    // signInPage() method to display second menu after user has successfully logged in
    public void signInPage(String inputUserName) {
        int input = 0;
        char again;
        System.out.print("Login Successful! ");
        System.out.println("Welcome, " + userHashMap.get(inputUserName).firstName + "!\n");

        // do-while loop displaying menu options
        do {
            System.out.println("Select from the following options: ");
            System.out.println("1. Deposit\n2. Withdrawal\n3. Check Balance\n4. End Account\n5. Return to Main Menu\n");
            input = reader.nextInt();
            reader.nextLine();

            // switch statement to call methods dependent on user needs
            switch (input) {
                case 1:
                    userDeposit(inputUserName);
                    break;
                case 2:
                    userWithdraw(inputUserName);
                    break;
                case 3:
                    showBalance(inputUserName);
                    break;
                case 4:
                    endAccount(inputUserName);
                    return;
                case 5:
                    return;
            }

            System.out.print("Would you like to perform another task? Y/N: ");
            again = reader.next().charAt(0);
            System.out.println();

        } while (again == 'y' || again == 'Y');
        System.exit(0);
    }


    // userNameCheck() method to validate userName exists
    public void userNameCheck(String inputUserName, String inputUserPass, int inputUserPin) {
        boolean userNameCheck, loginSuccess;

        userNameCheck = userHashMap.containsKey(inputUserName);

        // if userNameCheck is true, call checkCredentials, assign value to loginSuccess
        // if loginSuccess is true, call signInPage() method
        if (userNameCheck) {
            loginSuccess = checkCredentials(inputUserName, inputUserPass, inputUserPin);
            if (loginSuccess) {
                signInPage(inputUserName);
            }
        } else
            System.out.println("User not found!\n");
    }

    // checkCredentials() method to ensure user password and security pin maps with username
    public boolean checkCredentials(String inputUserName, String inputUserPass, int inputUserPin) {
        if (inputUserPass.equals(userHashMap.get(inputUserName).userPassword) &&
                inputUserPin == userHashMap.get(inputUserName).securityPin) {
            return true;
        } else
            System.out.println("Invalid Credentials!" + "\n");

        return false;
    }

    // userDeposit() method to allow the user to deposit x amount
    public void userDeposit(String inputUserName) {
        double amount;
        System.out.print("Amount to deposit: $");
        amount = reader.nextDouble();
        userHashMap.get(inputUserName).balance += amount;
    }

    // userWithdraw() method to allow the user to deposit x amount if user has enough funds
    public void userWithdraw(String inputUserName) {
        double amount;

        System.out.print("Amount to withdraw: $");
        amount = reader.nextDouble();
        if (userHashMap.get(inputUserName).balance > 0.01)
            userHashMap.get(inputUserName).balance -= amount;
        else
            System.out.println("No funds to withdraw\n");
    }

    //showBalance() function to print the current user's balance
    public void showBalance(String inputUserName) {
        System.out.println("Total Balance: $" + String.format("%.2f", userHashMap.get(inputUserName).balance) + "\n");
    }

    //endAccount() method to allow the user to discontinue their account
    public void endAccount(String inputUserName) {
        userHashMap.remove(inputUserName);
        System.out.println("\nAccount Successfully Removed!\n");
    }

}