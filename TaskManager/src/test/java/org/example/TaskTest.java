package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest
{
    @Test
    public void Task_Constructor_Test()
    {

        int id = 1;
        String name = "Example Task";
        int employeeId = 123;
        int finished = 0;


        Task task = new Task(id, name, employeeId, finished);


        assertEquals(id, task.id);
        assertEquals(name, task.name);
        assertEquals(employeeId, task.employee_id);
        assertEquals(finished, task.finished);
    }
}