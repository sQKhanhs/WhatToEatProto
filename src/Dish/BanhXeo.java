package Dish;

import java.util.Arrays;
import java.util.List;

public class BanhXeo implements Dish{

    @Override
    public List<String> getName() {
        String name = "Banh Xeo";
        return Arrays.asList(name);
    }

    @Override
    public String getDescription() {
        return "A Vietnamese style crepe with whole shrimp, thinly sliced pork and bean sprouts";
    }

    @Override
    public String getValue() {
        return "Calories: 250 - 350 kcal";
    }
}
