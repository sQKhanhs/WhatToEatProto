package Dish;

import java.util.Arrays;
import java.util.List;

public class BunBo implements Dish {
    @Override
    public List<String> getName() {
        String name = "Bun Bo";
        String name2 = "Bun Bo Hue";
        return Arrays.asList(name, name2);
    }

    @Override
    public String getDescription() {
        return "A popular Vietnamese rice noodle dish" +
                " with sliced beef, chả lụa, and sometimes pork knuckles.";
    }

    @Override
    public String getValue() {
        return "Calories: 534 kcal";
    }
}
