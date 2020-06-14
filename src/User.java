import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User implements Serializable {
    private String firstName, lastName, dateOfBirth, userName, userPassword;
    private String securityPin;
    private double balance = 0;
    private static HashMap<String, User> userHashMap = new HashMap<>();
    private static Scanner reader = new Scanner(System.in);

    // displayMenu() method to print options to user
    public static void displayMenu() {
        int input = 0;
        boolean bError = true;

        System.out.println("Welcome to Frost Bank!\n");

        do {
            System.out.println("Please select from the following options: ");
            System.out.println("1. Sign In\n2. Register\n3. Exit\n");
            try {
                input = reader.nextInt();
                reader.nextLine();
                while (input < 1 || input > 3) {
                    System.out.println("Invalid Menu Option. Please choose a number from 1 through 3\n");
                    input = reader.nextInt();
                    reader.nextLine();
                }

            } catch (InputMismatchException e) {
                reader.next();

                do {
                    System.out.println("Invalid Menu Option(Numbers Only). Please choose a number from 1 through 3\n");
                    if (reader.hasNextInt()) {
                        input = reader.nextInt();
                        reader.nextLine();
                        bError = false;
                    } else
                        reader.next();

                } while (bError);

            }

            switch (input) {
                case 1:
                    User currentUser = getUser();
                    if (currentUser != null)
                        currentUser.signIn();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    return;
            }

        } while (true);
    }

    // registerUser() method to allow new users to register
    public static void registerUser() {
        User user = new User();

        System.out.println("Please Register\n");
        System.out.print("Username: ");
        user.userName = reader.nextLine();
        System.out.print("Password: ");
        user.userPassword = reader.nextLine();
        System.out.print("Security Pin: ");
        user.securityPin = reader.nextLine();

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
        String inputUserPass, inputUserPin;

        System.out.println("Please sign in\n");
        System.out.println("Password: ");
        inputUserPass = reader.nextLine();
        System.out.println("Security Pin: ");
        inputUserPin = reader.nextLine();

        checkCredentials(inputUserPass, inputUserPin);
    }

    // signInPage() method to display second menu after user has successfully logged in
    public void signInPage() {
        int input = 0;
        char again;
        boolean bError = true;

        System.out.print("Login Successful! ");
        System.out.println("Welcome, " + firstName + "!\n");

        // do-while loop displaying menu options
        do {
            System.out.println("Select from the following options: ");
            System.out.println("1. Deposit\n2. Withdrawal\n3. Check Balance\n4. Change Password\n" +
                    "5. Change Pin\n6. End Account\n7. Return to Main Menu\n");

            try {
                input = reader.nextInt();
                reader.nextLine();
                while (input < 1 || input > 7) {
                    System.out.println("Invalid Menu Option. Please choose a number from 1 through 7\n");
                    input = reader.nextInt();
                    reader.nextLine();
                }
            } catch (InputMismatchException e) {
                reader.next();
                do {
                    System.out.println("Invalid Menu Option(Numbers Only!). Please choose a number from 1 through 7\n");
                    if (reader.hasNextInt()) {
                        input = reader.nextInt();
                        reader.nextLine();
                        bError = false;
                    } else
                        reader.next();

                } while (bError);
            }

            // switch statement to call methods dependent on user needs
            switch (input) {
                case 1:
                    userDeposit();
                    break;
                case 2:
                    userWithdraw();
                    break;
                case 3:
                    showBalance();
                    break;
                case 4:
                    changePassword();
                    break;
                case 5:
                    changePin();
                    break;
                case 6:
                    endAccount();
                    break;
                case 7:
                    return;
            }

            System.out.print("Would you like to perform another task? Y/N: ");
            again = reader.next().toUpperCase().charAt(0);
            System.out.println();

        } while (again == 'Y');
        System.exit(0);
    }

    // checkCredentials() method to ensure user password and security pin maps with currentUser
    public void checkCredentials(String inputUserPass, String inputUserPin) {
        if (inputUserPass.equals(userPassword) &&
                inputUserPin.equals(securityPin)) {
            signInPage();
        } else
            System.out.println("Invalid Credentials!" + "\n");
    }

    // userDeposit() method to allow the user to deposit x amount
    public void userDeposit() {
        System.out.print("Amount to deposit: $");
        balance += reader.nextDouble();
    }

    // userWithdraw() method to allow the user to deposit x amount if user has enough funds
    public void userWithdraw() {

        if (balance > 0.01) {
            System.out.print("Amount to withdraw: $");
            balance -= reader.nextDouble();
        } else
            System.out.println("No funds to withdraw\n");
    }

    // showBalance() function to print the current user's balance
    public void showBalance() {
        System.out.println("Total Balance: $" + String.format("%.2f", balance) + "\n");
    }

    //endAccount() method to allow the user to discontinue their account
    public void endAccount() {
        userHashMap.remove(userName);
        System.out.println("\nAccount Successfully Removed!\n");
    }

    // getUser() method to retrieve currentUser from HashMap registry
    public static User getUser() {
        System.out.println("Username: ");
        String inputUserName = reader.nextLine();
        HashMap<String, User> getMap = new HashMap<>();

        if (userHashMap.containsKey(inputUserName)) {
            return userHashMap.get(inputUserName);
        } else {
            System.out.println(inputUserName + " is not registered\n");
        }

        return null;
    }

    public void changePassword() {
        int numTries = 0;
        while (numTries < 3) {
            System.out.print("Enter current password: ");
            if (userPassword.equals(reader.nextLine())) {
                System.out.print("Enter new password: ");
                userPassword = reader.nextLine();
                break;
            } else {
                numTries++;
                System.out.println("Wrong password!\nNumber of tries remaining: "
                        + (3 - numTries));
            }
        }
    }

    public void changePin() {
        int numTries = 0;

        while (numTries < 3) {
            System.out.print("Enter current pin: ");
            if (securityPin.equals(reader.nextLine())) {
                System.out.print("Enter new pin: ");
                securityPin = reader.nextLine();
                break;
            } else {
                numTries++;
                System.out.println("Wrong pin!\n Number of tries remaining: "
                        + (3 - numTries));
            }
        }
    }

    public static void readUsersFile() {
        try {
            FileInputStream fis = new FileInputStream("registeredUsers.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userHashMap = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.addSuppressed(new FileNotFoundException());
        }

    }

    public static void saveUsers() {
        try {
            FileOutputStream fos = new FileOutputStream("registeredUsers.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userHashMap);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}