package org.example;

public class Manager
{
    public int id;
    public String name;
    public int projects_completed;
    public String password;

    public Manager(int id, String name, int projects_completed, String password)
    {
        this.id = id;
        this.name = name;
        this.projects_completed = projects_completed;
        this.password = password;
    }
}
