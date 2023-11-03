package User;

import Dish.*;
import Register.RegisterForm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class User {
    private static User user;
    private String userName;
    private String password;
    private boolean access = false;
    private ArrayList<Dish> dishes = new ArrayList<>();
    private User(){
    }
    public static User getInstance() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void dishAddToList() {
        dishes.add(new ComTam());
        dishes.add(new BunBo());
        dishes.add(new Pho());
        dishes.add(new BanhXeo());
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public boolean isAccess() {
        return access;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void register() {
        RegisterForm registerForm = new RegisterForm();
    }
    public void login() {
        Scanner login = new Scanner(System.in);
        System.out.println("Enter username: ");
        String userName = login.nextLine();
        User.getInstance().setUserName(userName);
        String line = "";
        try {
            File file = new File("C:\\Users\\Public\\Documents\\User\\"
                    + User.getInstance().getUserName() + ".txt");
            while (!file.exists()) {
                System.out.println("Invalid Username! Please enter again: ");
                userName = login.nextLine();
                User.getInstance().setUserName(userName);
                file = new File("C:\\Users\\Public\\Documents\\User\\"
                        + User.getInstance().getUserName() + ".txt");
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (!(line = bufferedReader.readLine()).equalsIgnoreCase(User.getInstance().getUserName())) {
            }
            line = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Enter password: ");

        while (!User.getInstance().isAccess()) {
            String password = login.nextLine();
            if (line.equalsIgnoreCase(password)) {
                User.getInstance().setAccess(true);
            }
            if (!User.getInstance().isAccess()) {
                System.out.println("Wrong password! Please enter again: ");
            }
        }
    }

    public String whatToEat() {
        dishAddToList();
        Random random = new Random();
        int randomNumber = random.nextInt(dishes.size());
        Dish dish = dishes.get(randomNumber);
        System.out.println(dish.getName().get(0));
        System.out.println(dish.getDescription());
        System.out.println(dish.getValue());
        return dish.getName().get(0);
    }
}
