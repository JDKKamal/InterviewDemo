package com.jdkgroup.interviewdemo;

import com.jdkgroup.model.ModelStudent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8Predict {

    public static void main(String[] args) {
        List<ModelStudent> studentList = createStudentList();

        List<ModelStudent> namesWithA = studentList.stream().filter(name -> name.getName().equals("Arpit")).collect(Collectors.toList());
        System.out.println("Tag" + namesWithA);

        double avgAgeOfEmployees = studentList.stream().collect(Collectors.averagingInt(ModelStudent::getAge));
        System.out.println("Tag Average age of employees: " + avgAgeOfEmployees);
        System.out.println("-----------------------------------------");

        long totalNoOfEmployees = studentList.stream().collect(Collectors.counting());
        System.out.println("Tag Total no of employees: " + totalNoOfEmployees);
        System.out.println("-----------------------------------------");

        Predicate<ModelStudent> male = e -> e.getName() == "Arpit";
        namesWithA.stream().filter(male).collect(Collectors.toList());


        Predicate<ModelStudent> mahesh = person -> person.getName().equals("Arpit");
        boolean returnValue = studentList.stream().allMatch(mahesh);
        System.out.println("allMatch() == Mahesh ? " + returnValue);

        Map<String, Integer> cities = new HashMap<>();
        cities.put("Shanghai", 24256800);
        cities.put("Karachi", 23500000);
        cities.put("Beijing", 21516000);
        cities.put("Delhi", 16349831);
        cities.put("Lagos", 16060303);
        cities.put("Tianjin", 15200000);

        System.out.println("Sort by key:-");
        cities.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByKey())
                .forEachOrdered(e -> System.out.println(e.getKey()));

        System.out.println("\nSort by key in reversed order:-");
        cities.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
                .forEachOrdered(e -> System.out.println(e.getKey()));

        System.out.println("\nSort by value:-");
        cities.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue())
                .forEachOrdered(e -> System.out.println(e.getValue()));

        System.out.println("\nSort by value with limit option (Top two populous cities in the world):- ");
        cities.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(2)
                .forEachOrdered(e -> System.out.println(e));


        Predicate<ModelStudent> nameNotNull = p -> p.getName() != null;
        Predicate<ModelStudent> nonNullPredicate = Objects::nonNull;
        Predicate<ModelStudent> teamWIPredicate = p -> p.getName().equals("Wisconsin");
        Predicate<ModelStudent> fullPredicate = nonNullPredicate.and(nameNotNull)
                .and(teamWIPredicate);

        List<ModelStudent> teams2 = studentList.stream().filter(fullPredicate).collect(Collectors.toList());
        teams2.forEach(u -> System.out.println("\t" + u.getName()));

        List<String> collect = studentList.stream().map(ModelStudent::getName).map(x -> x.toUpperCase()).collect(Collectors.toList()); //Collectors.toCollection(ArrayList::new)
        collect.forEach(u -> System.out.println("\t Tag" + u));
    }

    public static List createStudentList() {
        List<ModelStudent> studentList = new ArrayList();
        studentList.add(new ModelStudent(1, "Arpit", "M", 19));
        studentList.add(new ModelStudent(2, "John", "M", 17));
        studentList.add(new ModelStudent(3, "Mary", "F", 14));
        studentList.add(new ModelStudent(4, "Martin", "M", 21));
        studentList.add(new ModelStudent(5, "Monica", "F", 16));
        studentList.add(new ModelStudent(6, "Ally", "F", 20));
        return studentList;
    }
}