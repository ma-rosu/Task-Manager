package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DBControllerTest
{
    @Test
    public void testAddAndGetClients() {
        DBController dbController = new DBController();

        Client testClient = new Client(0, "TestClient", "123456789", "TestProject", 1);

        dbController.Add_Client(testClient);

        ArrayList<Client> clients = dbController.Get_Clients();

        boolean clientFound = false;
        for (Client client : clients)
        {
            if (client.name.equals("TestClient"))
            {
                clientFound = true;
                break;
            }
        }

        Assertions.assertTrue(clientFound);

        deleteTestClient(dbController, testClient);
    }

    private void deleteTestClient(DBController dbController, Client testClient)
    {

        ArrayList<Client> clients = dbController.Get_Clients();
        int testClientId = 0;
        for (Client client : clients) {
            if (client.name.equals(testClient.name)) {
                testClientId = client.id;
                break;
            }
        }


        if (testClientId != 0) {
            dbController.Open_Connection();
            String command = "DELETE FROM Clients WHERE ID = ?";
            try (PreparedStatement preparedStatement = dbController.connection.prepareStatement(command))
            {
                preparedStatement.setInt(1, testClientId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                dbController.Close_Connection();
            }
        }
    }


    @Test
    public void testAddAndGetManagers() {
        DBController dbController = new DBController();

        Manager testManager = new Manager(0, "TestManager", 3, "TestPassword");

        dbController.Add_Manager(testManager);

        ArrayList<Manager> managers = dbController.Get_Managers();

        boolean managerFound = false;
        for (Manager manager : managers) {
            if (manager.name.equals("TestManager")) {
                managerFound = true;
                break;
            }
        }

        Assertions.assertTrue(managerFound);

        deleteTestManager(dbController, testManager);
    }

    private void deleteTestManager(DBController dbController, Manager testManager)
    {

        ArrayList<Manager> managers = dbController.Get_Managers();
        int testManagerId = 0;
        for (Manager manager : managers)
        {
            if (manager.name.equals(testManager.name))
            {
                testManagerId = manager.id;
                break;
            }
        }

        if (testManagerId != 0) {
            dbController.Open_Connection();
            String command = "DELETE FROM Managers WHERE ID = ?";
            try (PreparedStatement preparedStatement = dbController.connection.prepareStatement(command))
            {
                preparedStatement.setInt(1, testManagerId);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
            finally
            {
                dbController.Close_Connection();
            }
        }
    }

    @Test
    public void testAddAndGetEmployees() {
        DBController dbController = new DBController();

        Employee testEmployee = new Employee(0, "TestEmployee", 2, 10, "TestPassword");

        dbController.Add_Employee(testEmployee);

        ArrayList<Employee> employees = dbController.Get_Employees();

        boolean employeeFound = false;
        for (Employee employee : employees)
        {
            if (employee.name.equals("TestEmployee")) {
                employeeFound = true;
                break;
            }
        }

        Assertions.assertTrue(employeeFound);

        deleteTestEmployee(dbController, testEmployee);
    }

    private void deleteTestEmployee(DBController dbController, Employee testEmployee)
    {

        ArrayList<Employee> employees = dbController.Get_Employees();
        int testEmployeeId = 0;
        for (Employee employee : employees) {
            if (employee.name.equals(testEmployee.name)) {
                testEmployeeId = employee.id;
                break;
            }
        }


        if (testEmployeeId != 0) {
            dbController.Open_Connection();
            String command = "DELETE FROM Employees WHERE ID = ?";
            try (PreparedStatement preparedStatement = dbController.connection.prepareStatement(command)) {
                preparedStatement.setInt(1, testEmployeeId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                dbController.Close_Connection();
            }
        }
    }
}