import java.util.Scanner;
import java.util.ArrayList;
import java.io.Serializable;

public class User {
    private String firstName, lastName, dateOfBirth;
    private int age, securityPin;
    private double balance = 0;

    public static void registerUser(ArrayList<User> userArrayList) {
        Scanner reader = new Scanner(System.in);
        User newUser = new User();

        System.out.println("Please Register\n");
        System.out.print("First Name: ");
        newUser.firstName = reader.nextLine();
        System.out.print("Last Name:");
        newUser.lastName = reader.nextLine();
        System.out.print("Date of Birth: ");
        newUser.dateOfBirth = reader.nextLine();
        System.out.print("Security Pin: ");
        newUser.securityPin = reader.nextInt();
        System.out.println();

        userArrayList.add(newUser);

    }

    public int signIn(ArrayList<User> userArrayList) {
        Scanner reader = new Scanner(System.in);

        System.out.println("Please Sign In\n");
        System.out.print("First Name: ");
        this.firstName = reader.nextLine();
        System.out.print("Last Name:");
        this.lastName = reader.nextLine();
        System.out.print("Date of Birth: ");
        this.dateOfBirth = reader.nextLine();
        System.out.print("Security Pin: ");
        this.securityPin = reader.nextInt();
        reader.nextLine();
        System.out.println();

        if (userArrayList.isEmpty())
            return -1;

        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).firstName.equals(this.firstName) && userArrayList.get(i).lastName.equals(this.lastName)
            && userArrayList.get(i).dateOfBirth.equals(this.dateOfBirth) && userArrayList.get(i).securityPin == this.securityPin) {
                System.out.println("\nLogin Successful!\nWelcome " + userArrayList.get(i).firstName + "\n");
                return i;
            }
        }

        return -1;
    }

    public void signInPage(ArrayList<User> userArrayList, int position) {
        Scanner reader = new Scanner(System.in);
        int input;
        char again;

        do {
            System.out.println("Select from the following options: ");
            System.out.println("1. Deposit\n2. Withdrawal\n3. Check Balance\n4. End Account\n5. Return to Main Menu\n");
            input = reader.nextInt();
            reader.nextLine();

            switch (input) {
                case 1:
                    userDeposit(userArrayList, position);
                    break;
                case 2:
                    userWithdraw(userArrayList, position);
                    break;
                case 3:
                    showBalance(userArrayList, position);
                    break;
                case 4:
                    endAccount(userArrayList, position);
                    return;
                case 5:
                    return;
            }

            System.out.print("Would you like to perform another task? Y/N: ");
            again = reader.next().charAt(0);

        } while (again == 'y' || again == 'Y');

    }

    public void userDeposit(ArrayList<User> userArrayList, int position){
        Scanner reader = new Scanner(System.in);
        double amount;

        System.out.print("Amount to deposit: ");
        amount = reader.nextDouble();

        userArrayList.get(position).balance += amount;
    }

    public void userWithdraw(ArrayList<User> userArrayList, int position){
        Scanner reader = new Scanner(System.in);
        double amount;

        System.out.print("Amount to withdraw: ");
        amount = reader.nextDouble();

        userArrayList.get(position).balance -= amount;
    }

    public void showBalance(ArrayList<User> userArrayList, int position){
        System.out.println("Total Balance: " + userArrayList.get(position).balance);
    }

    public void endAccount(ArrayList<User> userArrayList, int position){
        userArrayList.remove(position);
        System.out.println("\nAccount Successfully Removed!\n");
    }
}



