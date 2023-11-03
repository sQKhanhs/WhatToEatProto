package Search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class SearchByRating implements Search {
    private ArrayList<String[]> ratingList = new ArrayList<>();

    @Override
    public void search(String dishName) {
        try {
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant\\");
            String[] fileList = file.list();
            for (String restaurantUser : fileList) {
                FileReader reader = new FileReader("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + restaurantUser + "\\" + "information.txt");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                bufferedReader.mark(0);
                String[] dishNameRead = line.split(":");
                bufferedReader.readLine();
                if (dishNameRead[1].equalsIgnoreCase(" " + dishName)) {
                    String[] restaurantRating = new String[4];
                    File ratingFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                            + restaurantUser + "\\" + "rating.txt");
                    if(!ratingFile.exists()){
                        continue;
                    }
                    String restaurantName = bufferedReader.readLine();
                    restaurantRating[1] = restaurantName;
                    String restaurantAddress = bufferedReader.readLine();
                    restaurantRating[2] = restaurantAddress;
                    bufferedReader = new BufferedReader(new FileReader(ratingFile));
                    int totalRating = Integer.parseInt(bufferedReader.readLine());
                    int reviewCount = Integer.parseInt(bufferedReader.readLine());
                    String ratingStar = bufferedReader.readLine();
                    int rating = totalRating / reviewCount;
                    restaurantRating[0] = Integer.toString(rating);
                    restaurantRating[3] = ratingStar;
                    ratingList.add(restaurantRating);
                }
            }
                Collections.sort(ratingList, new RatingComparator());
                for (String[] restaurant : ratingList) {
                    System.out.println(restaurant[3]);
                    System.out.println(restaurant[1]);
                    System.out.println(restaurant[2]);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
