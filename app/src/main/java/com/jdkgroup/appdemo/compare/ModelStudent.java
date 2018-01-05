package com.jdkgroup.appdemo.compare;

public class ModelStudent {
    int id;
    String name, surname, sortname;

    public ModelStudent(int id, String name, String surname, String sortname)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sortname = sortname;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getSortname()
    {
        return sortname;
    }

    public void setSortname(String sortname)
    {
        this.sortname = sortname;
    }
    
    
}
   