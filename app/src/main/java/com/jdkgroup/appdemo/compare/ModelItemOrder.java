package com.jdkgroup.appdemo.compare;

public class ModelItemOrder {
    int id;
    String name, surname, sortname;
    boolean flagsortname;

    public ModelItemOrder(int id, String name, String surname, String sortname, boolean flagsortname)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sortname = sortname;
        this.flagsortname = flagsortname;
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

    public boolean isFlagsortname()
    {
        return flagsortname;
    }

    public void setFlagsortname(boolean flagsortname)
    {
        this.flagsortname = flagsortname;
    }
    
    
}
