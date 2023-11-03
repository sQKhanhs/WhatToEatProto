package User;

import Dish.Dish;
import Restaurant.Restaurant;
import Search.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserService {
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
        if (User.getInstance().isAccess()) {
            mainMenu(choice, input);
        }
    }

    private static void mainMenu(int choice, Scanner input) {
        while (choice != 0) {
            System.out.println("1. Dish lookup");
            System.out.println("2. What to eat?");
            System.out.println("3. Log out");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    String name = dishLookUp();
                    boolean dishFound = false;
                    if (!name.equalsIgnoreCase("blank")) {
                        dishFound = true;
                    }
                    while (!dishFound) {
                        mainMenu(choice, input);
                    }
                    choice = searchMenu(choice, input, name);
                    break;
                case 2:
                    name = User.getInstance().whatToEat();
                    choice = searchMenu(choice, input, name);
                    break;
                case 3:
                    logOut(choice, input);
                    mainMenuAccess(choice, input);
                    break;
                default:
                    System.out.println("Please choose again!");
            }
        }
    }

    private static int searchMenu(int choice, Scanner input, String name) {
        while (choice != 0) {
            System.out.println("1. Find restaurant by location");
            System.out.println("2. Find restaurant by price");
            System.out.println("3. Find restaurant by rating");
            System.out.println("4. Main menu");
            System.out.println("5. Log out");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    choice = searchLocation(input, name);
                    choice = restaurantInteract(choice, input);
                    break;
                case 2:
                    Search search = SearchFactory.getSearch("price");
                    search.search(name);
                    choice = restaurantInteract(choice, input);
                    break;
                case 3:
                    search = SearchFactory.getSearch("rating");
                    search.search(name);
                    choice = restaurantInteract(choice, input);
                    break;
                case 4:
                    mainMenuAccess(choice, input);
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

    private static int searchLocation(Scanner input, String name) {
        int choice;
        System.out.println("1. Ho Chi Minh City");
        System.out.println("2. Ha Noi City");
        choice = input.nextInt();
        switch (choice) {
            case 1:
                Search search = SearchFactory.getSearch("HCM");
                search.search(name);
                break;
            case 2:
                search = SearchFactory.getSearch("HN");
                search.search(name);
            default:
                System.out.println("Please choose again!");
                break;
        }
        return choice;
    }

    private static int restaurantInteract(int choice, Scanner input) {
        while (choice != 0) {
            System.out.println("1. Restaurant order");
            System.out.println("2. View review and rating");
            System.out.println("3, View menu");
            System.out.println("4. Review restaurant");
            System.out.println("5, Main menu");
            System.out.println("6. Log out");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    getOrderLink();
                    break;
                case 2:
                    viewReviewRating();
                    break;
                case 3:
                    viewMenu();
                    break;
                case 4:
                    updateReview();
                    break;
                case 5:
                    mainMenuAccess(choice, input);
                    break;
                case 6:
                    logOut(choice, input);
                    mainMenuAccess(choice, input);
                    break;
                default:
                    System.out.println("Please choose again!");
            }
        }
        return choice;
    }

    private static void viewMenu() {
        try {
            Scanner text = new Scanner(System.in);
            System.out.println("Enter restaurant name to view:");
            String restaurantName = text.nextLine();
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant");
            String[] fileList = file.list();
            boolean found = false;
            for (String restaurantUser : fileList) {
                file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + restaurantUser + "\\" + "information.txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase(restaurantName)) {
                        found = true;
                        File menuFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                                + restaurantUser + "\\" + "menu.txt");
                        if (!menuFile.exists()) {
                            System.out.println("No menu found!");
                        } else {
                            reader = new BufferedReader(new FileReader(menuFile));
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                            break;
                        }
                    }
                }
                if (found) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static void viewReviewRating() {
        try {
            Scanner text = new Scanner(System.in);
            System.out.println("Enter restaurant name to view:");
            String restaurantName = text.nextLine();

            File file = new File("C:\\Users\\Public\\Documents\\Restaurant");
            String[] fileList = file.list();
            for (String restaurantUser : fileList) {
                file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + restaurantUser + "\\" + "information.txt");

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase(restaurantName)) {
                        File ratingFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                                + restaurantUser + "\\" + "rating.txt");
                        File reviewFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                                + restaurantUser + "\\" + "review.txt");
                        reader = new BufferedReader(new FileReader(ratingFile));
                        reader.readLine();
                        reader.readLine();
                        System.out.println("Restaurant rating: " + reader.readLine());
                        reader = new BufferedReader(new FileReader(reviewFile));
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static void updateReview() {
        Scanner text = new Scanner(System.in);
        System.out.println("Enter restaurant name to review:");
        String restaurantName = text.nextLine();
        System.out.println("Enter your review(maximum chars 500):");
        String review = text.nextLine();
        review = reviewCheck(text, review);
        System.out.println("Enter your rating(0 to 5):");
        String rating = text.nextLine();
        if (Integer.parseInt(rating) > 5) {
            rating = "5";
        } else if (Integer.parseInt(rating) < 0) {
            rating = "0";
        }

        File file = new File("C:\\Users\\Public\\Documents\\Restaurant");
        String[] fileList = file.list();
        String restaurantUserFound = "";
        boolean restaurantFound = false;
        boolean oldReviewFound = false;
        int oldRating = 0;
        for (String restaurantUser : fileList) {
            try {
                file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + restaurantUser + "\\" + "information.txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase(restaurantName)) {
                        restaurantFound = true;
                        break;
                    }
                }
                if (restaurantFound) {
                    restaurantUserFound = restaurantUser;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error found! System restart....");
            }
        }
        try {
            String line;
            BufferedReader reader;
            if (restaurantFound) {
                file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + restaurantUserFound + "\\" + "review.txt");
            }
            if (!file.exists()) {
                file.createNewFile();
            } else {
                reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("User: " + User.getInstance().getUserName())) {
                        oldReviewFound = true;
                        reader.readLine();
                        line = reader.readLine();
                        String[] ratingLine = line.split("");
                        oldRating = Integer.parseInt(ratingLine[8]);
                        reader.close();
                        break;
                    }
                }
            }
            if (oldReviewFound) {
                System.out.println("You already had a review for this restaurant!");
                System.out.println("Update your review?(yes/no)");
                String answer = text.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    try {
                        File tempFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                                + "review.txt");
                        file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                                + restaurantUserFound + "\\" + "review.txt");
                        tempFile.createNewFile();
                        reader = new BufferedReader(new FileReader(file));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                        while ((line = reader.readLine()) != null) {
                            if (!line.equalsIgnoreCase("User: " + User.getInstance().getUserName())) {
                                writer.write(line + "\n");
                            } else {
                                reader.readLine();
                                reader.readLine();
                                writer.write("User: " + User.getInstance().getUserName() + "\n");
                                writer.write(review + "\n");
                                writer.write("Rating: " + rating + "/5" + "\n");
                            }
                        }
                        writer.close();
                        reader.close();
                        Path source = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                                + "review.txt");
                        Path newDirectory = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                                + restaurantUserFound);
                        Files.move(source, newDirectory.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        System.out.println("Error found! System restart....");
                    }
                }
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write("User: " + User.getInstance().getUserName() + "\n");
                writer.write(review + "\n");
                writer.write("Rating: " + rating + "/5" + "\n");
                writer.close();
            }
            updateRating(rating, restaurantUserFound, oldReviewFound, oldRating);
        } catch (Exception e) {
            System.out.println("Error found! System restart....");
        }
    }

    private static void updateRating(String rating, String restaurantUserFound, boolean oldReviewFound, int oldRating) throws IOException {
        BufferedReader reader;
        String line;
        File ratingFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                + restaurantUserFound + "\\" + "rating.txt");
        File tempFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                + "rating.txt");
        tempFile.createNewFile();
        if (!ratingFile.exists()) {
            ratingFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        reader = new BufferedReader(new FileReader(ratingFile));
        if ((line = reader.readLine()) == null) {
            writer.write(rating + "\n");
            writer.write("1" + "\n");
            int totalRatingScore = Integer.parseInt(rating);
            String starRating = "";
            int blackStar = 5 - totalRatingScore;
            for (int whiteStar = 1; whiteStar <= totalRatingScore; whiteStar++) {
                starRating += "★";
            }
            for (int starDraw = 1; starDraw <= blackStar; starDraw++) {
                starRating += "☆";
            }
            writer.write(starRating);
            writer.close();
            reader.close();
            Path source = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + "rating.txt");
            Path newDirectory = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + restaurantUserFound);
            Files.move(source, newDirectory.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } else {
            if (oldReviewFound) {
                int totalRatingScore = Integer.parseInt(line);
                int totalReviewCount = Integer.parseInt(reader.readLine());
                int updateRatingScore = totalRatingScore + Integer.parseInt(rating) - oldRating;
                int updateReviewCount = totalReviewCount;
                writer.write(updateRatingScore + "\n");
                writer.write(updateReviewCount + "\n");
                int finalRatingScore = updateRatingScore / updateReviewCount;
                String starRating = "";
                int blackStar = 5 - finalRatingScore;
                for (int whiteStar = 1; whiteStar <= finalRatingScore; whiteStar++) {
                    starRating += "★";
                }
                for (int starDraw = 1; starDraw <= blackStar; starDraw++) {
                    starRating += "☆";
                }
                writer.write(starRating);
                writer.close();
                reader.close();
                Path source = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + "rating.txt");
                Path newDirectory = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + restaurantUserFound);
                Files.move(source, newDirectory.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            } else {
                int totalRatingScore = Integer.parseInt(line);
                int totalReviewCount = Integer.parseInt(reader.readLine());
                int updateRatingScore = totalRatingScore + Integer.parseInt(rating);
                int updateReviewCount = totalReviewCount + 1;
                writer.write(updateRatingScore + "\n");
                writer.write(updateReviewCount + "\n");
                int finalRatingScore = updateRatingScore / updateReviewCount;
                String starRating = "";
                int blackStar = 5 - finalRatingScore;
                for (int whiteStar = 1; whiteStar <= finalRatingScore; whiteStar++) {
                    starRating += "★";
                }
                for (int starDraw = 1; starDraw <= blackStar; starDraw++) {
                    starRating += "☆";
                }
                writer.write(starRating);
                writer.close();
                reader.close();
                Path source = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + "rating.txt");
                Path newDirectory = Paths.get("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + restaurantUserFound);
                Files.move(source, newDirectory.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private static String reviewCheck(Scanner text, String review) {
        String[] reviewCheck = review.split("");
        while (reviewCheck.length > 500) {
            System.out.println("Max chars limit exceed!" + "(" + reviewCheck.length + " chars" + ")");
            System.out.println("Your review:");
            System.out.println(review);
            System.out.println("Please enter review again: ");
            review = text.nextLine();
            reviewCheck = review.split("");
        }
        return review;
    }

    private static void getOrderLink() {
        Scanner text = new Scanner(System.in);
        System.out.println("Enter restaurant name to order: ");
        String restaurantName = text.nextLine();
        File file = new File("C:\\Users\\Public\\Documents\\Restaurant");
        String[] fileList = file.list();
        for (String restaurantUser : fileList) {
            try {
                file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + restaurantUser + "\\" + "information.txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase(restaurantName)) {
                        file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                                + restaurantUser + "\\" + "order.txt");
                        reader = new BufferedReader(new FileReader(file));
                        System.out.println("Please access this link to order:");
                        System.out.println(reader.readLine());
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error found! System restart....");
            }
        }
    }

    private static String dishLookUp() {
        Scanner text = new Scanner(System.in);
        System.out.println("Enter dish name: ");
        String name = text.nextLine();
        boolean dishFound = false;
        User.getInstance().dishAddToList();
        for (int index = 0; index < User.getInstance().getDishes().size(); index++) {
            Dish dish = User.getInstance().getDishes().get(index);
            for (int index2 = 0; index2 < dish.getName().size(); index2++) {
                if (name.equalsIgnoreCase(dish.getName().get(index2))) {
                    System.out.println(dish.getDescription());
                    System.out.println(dish.getValue());
                    name = dish.getName().get(0);
                    dishFound = true;
                    break;
                }
            }
            if (dishFound) {
                break;
            }
        }
        if (!dishFound) {
            System.out.println("No dish found!");
            name = "blank";
        }
        return name;
    }


    private static int logOut(int choice, Scanner input) {
        User.getInstance().setAccess(false);
        while (choice != 0 && choice != 1) {
            System.out.println("What To Eat");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    User.getInstance().login();
                    break;
                case 2:
                    User.getInstance().register();
                    break;
                default:
                    System.out.println("Please choose again!");
            }
        }
        return choice;
    }
}