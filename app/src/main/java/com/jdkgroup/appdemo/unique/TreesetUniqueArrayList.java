package com.jdkgroup.appdemo.unique;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.jdkgroup.appdemo.unique.ModelStudent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class TreesetUniqueArrayList {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String args[])
    {
        List<ModelStudent> alStudent = new ArrayList<>();
        alStudent.add(new ModelStudent(1, "kamal"));
        alStudent.add(new ModelStudent(2, "kamlesh"));
        alStudent.add(new ModelStudent(3, "kaushik"));
        alStudent.add(new ModelStudent(3, "kamal"));

        TreeSet<ModelStudent> treeSet = new TreeSet<>(new CompareStudent());
        treeSet.addAll(alStudent);

        treeSet.forEach((student) ->
        {
            System.out.println(student.getName());
        });

        Collections.sort(alStudent, new Comparator<ModelStudent>() {
            @Override
            public int compare(ModelStudent o1, ModelStudent o2)
            {
                return o1.getId()- o2.getId();
            }
        });
    }

    public static class CompareStudent implements Comparator<ModelStudent> {

        @Override
        public int compare(ModelStudent o1, ModelStudent o2)
        {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }
}
