package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest
{
    @Test
    public void Client_Constructor_Test()
    {

        int id = 1;
        String name = "John Doe";
        String phone = "123-456-7890";
        String projectName = "Sample Project";
        int accepted = 0;


        Client client = new Client(id, name, phone, projectName, accepted);


        assertEquals(id, client.id);
        assertEquals(name, client.name);
        assertEquals(phone, client.phone);
        assertEquals(projectName, client.project_name);
        assertEquals(accepted, client.accepted);
    }
}