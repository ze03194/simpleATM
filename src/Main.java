import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<User> UserArrayList = new ArrayList<User>();

        System.out.println("Welcome to Frost Bank!\n");
        do {
            int input;
            System.out.println("Please select from the following options: ");
            System.out.println("1. Sign In\n2. Register\n3. Exit\n");
            input = reader.nextInt();
            reader.nextLine();

            switch (input) {
                case 1:
                    int position;
                    User currentUser = new User();
                    position = currentUser.signIn(UserArrayList);

                    if (position >= 0) {
                        currentUser.signInPage(UserArrayList, position);
                    } else {
                        System.out.println("User not found!\n");

                    }
                    break;
                case 2:
                    User.registerUser(UserArrayList);
                    break;
                case 3:
                    break;
            }

        } while (true);

    }
}