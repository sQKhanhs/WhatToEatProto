package Test;

import java.io.File;
import java.util.Scanner;
class CreateFolderHierarchy {
    //main() method start
    public static void main(String args[]) {;
        String path = "C:\\Users\\Public\\Documents\\User";
        File f1 = new File(path);
        if(!f1.exists()) {
            f1.mkdirs();
        } else{
            System.out.println("Folder had already been created");
        }
    }
}
