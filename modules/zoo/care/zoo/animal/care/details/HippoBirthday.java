package zoo.animal.care.details;

import zoo.animal.feeding.*;

public class HippoBirthday {

    private Task task;

    public HippoBirthday(Task task) {
        System.out.println(HippoBirthday.class.getName());
        this.task = task;
    }
}
