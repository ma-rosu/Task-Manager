package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcceptedProjectTest
{
    @Test
    public void AcceptedProject_Constructor_Test()
    {

        int id = 1;
        int projectId = 101;
        int managerId = 201;
        int finished = 0;


        AcceptedProject acceptedProject = new AcceptedProject(id, projectId, managerId, finished);


        assertEquals(id, acceptedProject.id);
        assertEquals(projectId, acceptedProject.project_id);
        assertEquals(managerId, acceptedProject.manager_id);
        assertEquals(finished, acceptedProject.finished);
    }
}