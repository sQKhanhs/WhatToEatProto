package Restaurant;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RestaurantService {
    public static void main(String[] args) {
        try {
            int choice = -1;
            Scanner input = new Scanner(System.in);
            choice = logOut(choice, input);
            mainMenuAccess(choice, input);
        } catch (InputMismatchException e) {
            System.out.println("Please enter the correct input to proceed!");
        }
    }

    private static void mainMenuAccess(int choice, Scanner input) {
        if (Restaurant.getInstance().isAccess()) {
            mainMenu(choice, input);
        }
    }

    private static void mainMenu(int choice, Scanner input) {
        while (choice != 0) {
            System.out.println("1. Restaurant information");
            System.out.println("2. Menu manager");
            System.out.println("3. Order link");
            System.out.println("4. Restaurant review");
            System.out.println("5. Log out");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    choice = information(choice, input);
                    break;
                case 2:
                    choice = menuManager(choice, input);
                    break;
                case 3:
                    choice = orderLink(choice, input);
                    break;
                case 4:
                    choice = restaurantReview(choice, input);
                    break;
                case 5:
                    logOut(choice, input);
                    mainMenuAccess(choice, input);
                    break;
                default:
                    System.out.println("Please choose again!");
            }
        }
    }

    private static int restaurantReview(int choice, Scanner input) {
        while (choice != 0) {
            System.out.println("1. View review and rating");
            System.out.println("2. Reply user review");
            System.out.println("3. Update reply");
            System.out.println("4. Main menu");
            System.out.println("5. Log out");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    viewReview();
                    break;
                case 2:
                    replyUser();
                    break;
                case 3:
                    updateReply();
                    break;
                case 4:
                    mainMenu(choice, input);
                    break;
                case 5:
                    logOut(choice, input);
                    mainMenuAccess(choice, input);
                    break;
                default:
                    System.out.println("Please choose again");
            }
        }
        return choice;
    }

    private static void updateReply() {
        Scanner text = new Scanner(System.in);
        System.out.println("Enter user's name's reply to update:");
        String userName = text.nextLine();
        String line;
        userName = checkUser(text, userName);
        System.out.println("Enter your reply(maximum chars 250):");
        String reply = text.nextLine();
        reply = replyCheck(text, reply);
        try {
            File informationFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\" + "information.txt");
            BufferedReader nameReader = new BufferedReader(new FileReader(informationFile));
            nameReader.readLine();
            nameReader.readLine();
            String restaurantName = nameReader.readLine();
            File reviewFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\" + "review.txt");
            File tempFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + "review.txt");
            tempFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(reviewFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            while ((line = reader.readLine()) != null) {
                if (!(line.endsWith(userName + ":") && line.startsWith(" " + restaurantName))) {
                    writer.write(line + "\n");
                } else {
                    reader.readLine();
                    writer.write(" " + restaurantName + " reply to " + userName + ":" + "\n");
                    writer.write(" " + reply + "\n");
                }
            }
            reader.close();
            writer.close();
            Path source = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + "review.txt");
            Path newDirectory = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName());
            Files.move(source, newDirectory.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static void replyUser() {
        Scanner text = new Scanner(System.in);
        System.out.println("Enter user's name to reply to:");
        String userName = text.nextLine();
        String line;
        userName = checkUser(text, userName);
        System.out.println("Enter your reply(maximum chars 250):");
        String reply = text.nextLine();
        reply = replyCheck(text, reply);
        try {
            File informationFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\" + "information.txt");
            BufferedReader nameReader = new BufferedReader(new FileReader(informationFile));
            nameReader.readLine();
            nameReader.readLine();
            String restaurantName = nameReader.readLine();
            File reviewFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\" + "review.txt");
            File tempFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + "review.txt");
            tempFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(reviewFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            while ((line = reader.readLine()) != null) {
                if (!line.endsWith(userName)) {
                    writer.write(line + "\n");
                } else {
                    writer.write(line + "\n");
                    writer.write(reader.readLine() + "\n");
                    writer.write(reader.readLine() + "\n");
                    writer.write(" " + restaurantName + " reply to " + userName + ":" + "\n");
                    writer.write(" " + reply + "\n");
                }
            }
            reader.close();
            writer.close();
            Path source = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + "review.txt");
            Path newDirectory = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName());
            Files.move(source, newDirectory.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static String replyCheck(Scanner text, String reply) {
        String[] replyCheck = reply.split("");
        while (replyCheck.length > 250) {
            System.out.println("Max chars limit exceed!" + "(" + replyCheck.length + " chars" + ")");
            System.out.println("Your reply:");
            System.out.println(reply);
            System.out.println("Please enter reply again: ");
            reply = text.nextLine();
            replyCheck = reply.split("");
        }
        return reply;
    }

    private static String checkUser(Scanner text, String userName) {
        String line;
        try {
            File reviewFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\" + "review.txt");
            BufferedReader checkUserReader = new BufferedReader(new FileReader(reviewFile));
            boolean userFound = false;
            while (!userFound) {
                while ((line = checkUserReader.readLine()) != null) {
                    if (line.endsWith(userName)) {
                        userFound = true;
                        break;
                    }
                }
                if (!userFound) {
                    System.out.println("No user found! Please enter again:");
                    userName = text.nextLine();
                    checkUserReader = new BufferedReader(new FileReader(reviewFile));
                } else {
                    checkUserReader.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
        return userName;
    }

    private static void viewReview() {
        try {
            File reviewFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\" + "review.txt");
            File ratingFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\" + "rating.txt");
            BufferedReader reader = new BufferedReader(new FileReader(ratingFile));
            reader.readLine();
            reader.readLine();
            System.out.println("Rating: " + reader.readLine());
            reader = new BufferedReader(new FileReader(reviewFile));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static int orderLink(int choice, Scanner input) {
        while (choice != 0) {
            System.out.println("1. View order link");
            System.out.println("2. Update order link");
            System.out.println("3. Main menu");
            System.out.println("4. Log out");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    viewLink();
                    break;
                case 2:
                    updateLink();
                    break;
                case 3:
                    mainMenu(choice, input);
                    break;
                case 4:
                    logOut(choice, input);
                    mainMenuAccess(choice, input);
                    break;
                default:
                    System.out.println("Please choose again");
            }
        }
        return choice;
    }

    private static void updateLink() {
        Scanner text = new Scanner(System.in);
        System.out.println("Enter order link:");
        String link = text.nextLine();
        try {
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\"
                    + "order.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(link);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static void viewLink() {
        try {
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\"
                    + "order.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            System.out.println("Order link: ");
            System.out.println(bufferedReader.readLine());
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static int information(int choice, Scanner input) {
        while (choice != 0) {
            System.out.println("1. View information");
            System.out.println("2. Update information");
            System.out.println("3. Main menu");
            System.out.println("4. Log out");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    viewInformation();
                    break;
                case 2:
                    updateInformation();
                    break;
                case 3:
                    mainMenu(choice, input);
                    break;
                case 4:
                    logOut(choice, input);
                    mainMenuAccess(choice, input);
                    break;
                default:
                    System.out.println("Please choose again!");
            }
        }
        return choice;
    }

    private static void viewInformation() {
        try {
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\" + "information.txt");
            if (!file.exists()) {
                System.out.println("No information found!");
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static int menuManager(int choice, Scanner input) {
        while (choice != 0) {
            System.out.println("1. View menu");
            System.out.println("2. Add dish to menu");
            System.out.println("3. Remove dish from menu");
            System.out.println("4. Main menu");
            System.out.println("5. Log out");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    viewMenu();
                    break;
                case 2:
                    addToMenu();
                    break;
                case 3:
                    removeFromMenu();
                    break;
                case 4:
                    mainMenu(choice, input);
                    break;
                case 5:
                    logOut(choice, input);
                    mainMenuAccess(choice, input);
                    break;
                default:
                    System.out.println("Please choose again!");
            }
        }
        return choice;
    }

    private static void removeFromMenu() {
        Scanner text = new Scanner(System.in);
        System.out.println("Enter dish name to remove:");
        String dishName = text.nextLine();
        try {
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\"
                    + "menu.txt");
            File tempFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + "menu.txt");
            tempFile.createNewFile();
            if (!file.exists()) {
                System.out.println("No menu had been created!");
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (!line.equalsIgnoreCase(dishName)) {
                        bufferedWriter.write(line + "\n");
                    } else {
                        line = bufferedReader.readLine();
                    }
                }
                bufferedWriter.close();
                bufferedReader.close();
                Path source = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + "menu.txt");
                Path newDirectory = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + Restaurant.getInstance().getUserName());
                Files.move(source, newDirectory.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static void viewMenu() {
        try {
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\"
                    + "menu.txt");
            if (!file.exists()) {
                System.out.println("No menu had been created!");
                ;
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static void addToMenu() {
        Scanner text = new Scanner(System.in);
        System.out.println("Enter dish name:");
        String dishName = text.nextLine();
        System.out.println("Enter dish price:");
        int price = text.nextInt();
        try {
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\"
                    + "menu.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(dishName + "\n");
            bufferedWriter.write(price + " VND" + "\n");
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static void updateInformation() {
        Scanner text = new Scanner(System.in);
        System.out.println("Enter restaurant name: ");
        String name = text.nextLine();
        System.out.println("Enter restaurant location(HCM or HN): ");
        String location = text.nextLine();
        System.out.println("Enter restaurant address: ");
        String address = text.nextLine();
        System.out.println("Enter restaurant main dish: ");
        String dishType = text.nextLine();
        try {
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\" + "information.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("Main dish: " + dishType + "\n");
            bufferedWriter.write(location + "\n");
            bufferedWriter.write(name + "\n");
            bufferedWriter.write("Address: " + address + "\n");
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static int logOut(int choice, Scanner input) {
        Restaurant.getInstance().setAccess(false);
        while (choice != 0 && choice != 1) {
            System.out.println("What To Eat(for Restaurant)");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    Restaurant.getInstance().login();
                    break;
                case 2:
                    Restaurant.getInstance().register();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Please choose again!");
            }
        }
        return choice;
    }
}

