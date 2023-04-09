import java.util.Scanner;

public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean UpperCaseIncluded, boolean LowerCaseIncluded, boolean NumIncluded, boolean SymbIncluded) {
        alphabet = new Alphabet(UpperCaseIncluded, LowerCaseIncluded, NumIncluded, SymbIncluded);
    }

    public void mainLoop() {
        System.out.println();
        System.out.println("Advance Password Generator & Encrypter");
        System.out.println("----------------------------------------");

        printMenu();

        String UsersChoice = "-1";

        while (!UsersChoice.equals("4")) {

            UsersChoice = keyboard.next();

            switch (UsersChoice) {
                case "1" -> {
                    requestPassword();
                    Scanner goBack=new Scanner(System.in);
                    System.out.println();
                    System.out.println("To go back to the menu, press Y, to exit the application press any key!!");
                    char choice = goBack.next().charAt(0);
                    if(choice=='Y' || choice=='y') printMenu();
                    else{
                        System.out.println();
                        System.out.println("Exiting from the application...");
                        System.exit(0);
                    }
                }
                case "2" -> {
                    checkPassword();
                    Scanner goBack=new Scanner(System.in);
                    System.out.println();
                    System.out.println("To go back to the menu, press Y, to exit the application press any key!!");
                    char choice = goBack.next().charAt(0);
                    if(choice=='Y' || choice=='y') printMenu();
                    else{
                        System.out.println();
                        System.out.println("Exiting from the application...");
                        System.exit(0);
                    }
                }
                case "3" -> {
                    AES aes=new AES();
                    AES.performEncyption();
                    Scanner goBack=new Scanner(System.in);
                    System.out.println();
                    System.out.println("To go back to the menu, press Y, to exit the application press any key!!");
                    char choice = goBack.next().charAt(0);
                    if(choice=='Y' || choice=='y') printMenu();
                    else{
                        System.out.println();
                        System.out.println("Exiting from the application...");
                        System.exit(0);
                    }
                }
                case "4" -> {
                 passwordDB init=new passwordDB(); 
                 Scanner sc=new Scanner(System.in);
                 System.out.println();
                 System.out.println("Select an operation");
                 System.out.println("-------------------");
                 System.out.println("1 - For adding a text to the database.");
                 System.out.println("2 - For retriveing the complete database.");
                 System.out.println();
               
                 System.out.println("Enter your Choice: ");
                 int choice = sc.nextInt();  
                 if(choice==1)
                    init.addItemtoDatabase();
                 else if(choice==2)
                    init.printDatabase();
                 else System.out.println("Invalid Choice!!");
                 System.out.println();
                }
                case "5" -> printQuitMessage();
                default -> {
                    System.out.println();
                    System.out.println("Please Select an operation");
                    Scanner goBack=new Scanner(System.in);
                    System.out.println();
                    System.out.println("To go back to the menu, press Y, to exit the application press any key!!");
                    char choice = goBack.next().charAt(0);
                    if(choice=='Y' || choice=='y') printMenu();
                    else{
                        System.out.println();
                        System.out.println("Exiting from the application...");
                        System.exit(0);
                    }
                }
            }
        }
    }

    private Password GeneratePassword(int length) {
        final StringBuilder pass = new StringBuilder("");

        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(pass.toString());
    }


    private void requestPassword() {
        boolean UpperCaseIncluded = false;
        boolean LowerCaseIncluded = false;
        boolean NumIncluded = false;
        boolean SymbIncluded = false;

        boolean correctParams;

        System.out.println();
        System.out.println("Hello, welcome to the Password Generator :) answer"
                + " the following questions by Yes or No \n");

        do {
            String input;
            correctParams = false;

            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) LowerCaseIncluded = true;

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) UpperCaseIncluded = true;

            do {
            System.out.println("Do you want Numbers \"1234...\" to be used? ");
            input = keyboard.next();
            PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) NumIncluded = true;

            do {
            System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
            input = keyboard.next();
            PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) SymbIncluded = true;

            //No Pool Selected
            if (!UpperCaseIncluded && !LowerCaseIncluded && !NumIncluded && !SymbIncluded) {
                System.out.println("Weak Password! Tip:-Password should contain a Lowecase Character,an Uppercase Character, a number and a symbol. ");
                correctParams = true;
            }

        } while (correctParams);

        System.out.println("Great! Now enter the length of the password");
        int length = keyboard.nextInt();

        final Generator generator = new Generator(UpperCaseIncluded, LowerCaseIncluded, NumIncluded, SymbIncluded);
        final Password password = generator.GeneratePassword(length);

        System.err.println("Your generated password : " + password);
    }

    private boolean isInclude(String Input) {
        if (Input.equalsIgnoreCase("yes")) {
            return true;
        } 
        else {
            return false;
        }
    }

    private void PasswordRequestError(String i) {
        if (!i.equalsIgnoreCase("yes") && !i.equalsIgnoreCase("no")) {
            System.out.println("Invalid Input! Please Try Again \n");
        }
    }

    private void checkPassword() {
        String input;

        System.out.print("\nPlease enter your password:");
        input = keyboard.next();

        final Password pass = new Password(input);

        System.out.println(pass.calculateScore());
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1 - Password Generator");
        System.out.println("2 - Password Strength Check");
        System.out.println("3 - Apply AES 256 Encryption");
        System.out.println("4 - Password Database");
        System.out.println("5 - Quit");
        System.out.println();
        System.out.println();
        System.out.print("Please enter your choice : ");
        System.out.println();
    }

    private void printQuitMessage() {
        System.out.println("Exiting from the application...");
        System.exit(0);
    }
}
