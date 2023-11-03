package Search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SearchByLocationHCM implements Search {
    @Override
    public void search(String dishName) {
        try {
            File file = new File("C:\\Users\\Public\\Documents\\Restaurant\\");
            String[] fileList = file.list();
            for (String restaurantUser : fileList) {
                File informationFile = new File("C:\\Users\\Public\\Documents\\Restaurant\\"
                        + restaurantUser + "\\" + "information.txt");
                if(!informationFile.exists()){
                    continue;
                }
                FileReader reader = new FileReader(informationFile);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                bufferedReader.mark(0);
                String[] dishNameRead = line.split(":");
                if (dishNameRead[1].equalsIgnoreCase(" " + dishName)) {
                    bufferedReader.reset();
                    if (bufferedReader.readLine().equalsIgnoreCase("HCM")) {
                        System.out.println(bufferedReader.readLine());
                        System.out.println(bufferedReader.readLine());
                    }
                    bufferedReader.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




