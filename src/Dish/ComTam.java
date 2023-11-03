package Dish;

import java.util.Arrays;
import java.util.List;

public class ComTam implements Dish{
    @Override
    public List<String> getName() {
        String name = "Com Tam";
        String name2 = "Com Suon";
        String name3 = "Com Suon Nuong";
        return Arrays.asList(name, name2, name3);
    }

    @Override
    public String getDescription() {
        return "A dish consist of broken rice and numerous toppings" +
                " such as fried eggs, shredded pork skin, grilled pork chops";
    }

    @Override
    public String getValue() {
        return "Calories: 527-761 kcal";
    }
}
