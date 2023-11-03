package Dish;

import java.util.Arrays;
import java.util.List;

public class Pho implements Dish{
    @Override
    public List<String> getName() {
        String name = "Pho";
        String name2 = "Pho bo";
        return Arrays.asList(name, name2);
    }

    @Override
    public String getDescription() {
        return "A Vietnamese soup consisting of bone broth, rice noodles, and thinly sliced meat(usually beef)." +
                " It may also be served with bean sprouts, fresh herbs, limes and other garnishes.";
    }

    @Override
    public String getValue() {
        return "Calories: 276 - 352 kcal";
    }
}
