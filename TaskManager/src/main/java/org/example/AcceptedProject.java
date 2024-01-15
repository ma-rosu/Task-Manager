package org.example;

public class AcceptedProject
{
    public int id;
    public int project_id;
    public int manager_id;
    public int finished;


    public AcceptedProject(int id, int project_id, int manager_id, int finished)
    {
        this.id = id;
        this.project_id = project_id;
        this.manager_id = manager_id;
        this.finished = finished;
    }
}
