package Restaurant;

import Dish.Dish;
import Register.RegisterForm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    private static Restaurant restaurant;
    private String userName;
    private String password;
    private String name;
    private String location;
    private String address;
    private boolean access = false;
    private ArrayList<Dish> menu = new ArrayList<>();
    public static ArrayList<Restaurant> restaurantList = new ArrayList<>();

    private Restaurant() {
    }

    public static Restaurant getInstance() {
        if (restaurant == null) {
            restaurant = new Restaurant();
        }
        return restaurant;
    }

    public static void addRestaurant(Restaurant restaurant) {
        Restaurant.restaurantList.add(restaurant);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
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

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public ArrayList<Dish> getMenu() {
        return menu;
    }

    public void login() {
        Scanner login = new Scanner(System.in);
        System.out.println("Enter username: ");
        String userName = login.nextLine();
        Restaurant.getInstance().setUserName(userName);
        String line = "";
        try {
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                    + Restaurant.getInstance().getUserName() + "\\"
                    + Restaurant.getInstance().getUserName() + ".txt");
            while (!file.exists()) {
                System.out.println("Invalid Username! Please enter again: ");
                userName = login.nextLine();
                Restaurant.getInstance().setUserName(userName);
                file = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + Restaurant.getInstance().getUserName() + "\\"
                        + Restaurant.getInstance().getUserName() + ".txt");
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (!(line = bufferedReader.readLine()).equalsIgnoreCase(Restaurant.getInstance().getUserName())) {
            }
            line = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Enter password: ");

        while (!Restaurant.getInstance().isAccess()) {
            String password = login.nextLine();
            if (line.equalsIgnoreCase(password)) {
                Restaurant.getInstance().setAccess(true);
            }
            if (!Restaurant.getInstance().isAccess()) {
                System.out.println("Wrong password! Please enter again: ");
            }
        }
    }
    public void register(){
        RegisterForm registerForm = new RegisterForm();
    }
}
