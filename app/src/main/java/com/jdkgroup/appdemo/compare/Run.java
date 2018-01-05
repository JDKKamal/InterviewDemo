package com.jdkgroup.appdemo.compare;

import java.util.ArrayList;
import java.util.List;

public class Run {

    public static void main(String args[])
    {
        List<ModelStudent> alStudent = new ArrayList<>();
        alStudent.add(new ModelStudent(1, "kamlesh", "lakhani", "kamal"));
        alStudent.add(new ModelStudent(2, "milan", "lakhani", "milu"));
        alStudent.add(new ModelStudent(3, "jignesh", "korat", "jigo"));
        alStudent.add(new ModelStudent(4, "jayshree", "lakhani", "mira"));
        alStudent.add(new ModelStudent(5, "kishan", "ribadiya", "kano"));

        List<ModelItemOrder> alItemOrder = new ArrayList<>();
        alItemOrder.add(new ModelItemOrder(1, "", "", "kamal", false));
        alItemOrder.add(new ModelItemOrder(5, "", "", "kano", false));
        alItemOrder.add(new ModelItemOrder(2, "", "", "milu", false));

        for (ModelStudent student : alStudent)
        {
            for (ModelItemOrder myItemOrder : alItemOrder)
            {
                int itemIndex = alItemOrder.indexOf(myItemOrder);

                if (student.getSortname().equalsIgnoreCase(myItemOrder.getSortname()))
                {
                    alItemOrder.set(itemIndex, new ModelItemOrder(student.getId(), student.getName(), "", "", true));
                }
            }
        }

        for (ModelItemOrder myItemOrder : alItemOrder)
        {
            System.out.println(myItemOrder.isFlagsortname() + "-" + myItemOrder.getName());
        }
    }
}
