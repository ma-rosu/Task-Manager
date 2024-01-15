package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest
{
    @Test
    public void Manager_Constructors_Test()
    {

        int id = 1;
        String name = "John Manager";
        int projectsCompleted = 3;
        String password = "securePassword";


        Manager manager = new Manager(id, name, projectsCompleted, password);


        assertEquals(id, manager.id);
        assertEquals(name, manager.name);
        assertEquals(projectsCompleted, manager.projects_completed);
        assertEquals(password, manager.password);
    }
}