package org.example;

public class Employee
{
    public int id;
    public String name;
    public int manager_id;
    public int tasks_completed;
    public String password;

    public Employee(int id, String name, int manager_id, int tasks_completed, String password)
    {
        this.id = id;
        this.name = name;
        this.manager_id = manager_id;
        this.tasks_completed = tasks_completed;
        this.password = password;
    }
}
