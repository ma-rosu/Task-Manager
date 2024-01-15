package org.example;

public class Task
{
    public int id;
    public String name;
    public int employee_id;
    public int finished;


    public Task(int id, String name, int employee_id, int finished)
    {
        this.id = id;
        this.name = name;
        this.employee_id = employee_id;
        this.finished = finished;
    }
}
