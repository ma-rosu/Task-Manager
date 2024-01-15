package org.example;

import javax.lang.model.type.ArrayType;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController
{
    public Connection connection = null;

    public void Open_Connection()
    {
        if(connection == null)
        {
            try
            {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\maros\\Documents\\Projects\\JAVA Projects\\TaskManager\\database.db");
            }
            catch(ClassNotFoundException | SQLException e)
            {
                System.out.println(e+"");
            }
        }
        else
            System.out.println("Connection is not null to add another, please close it.");

    }


    public void Close_Connection()
    {
        if(connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                System.out.println("An error has occurred.");
            }
            connection = null;
        }
        else
            System.out.println("Connection is already null.");

    }


    public void Add_Client(Client client)
    {
        Open_Connection();
        if(client.id == 0)
        {
            String command = "insert into Clients (Name, Phone, ProjectName, Accepted) values (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(command))
            {
                preparedStatement.setString(1, client.name);
                preparedStatement.setString(2, client.phone);
                preparedStatement.setString(3, client.project_name);
                preparedStatement.setInt(4, client.accepted);

                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                System.out.println("An error has occurred.");
            }
        }
        else
        {
            String command = "UPDATE Clients SET Name = ?, Phone = ?, ProjectName = ?, Accepted = ? WHERE ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(command))
            {
                preparedStatement.setString(1, client.name);
                preparedStatement.setString(2, client.phone);
                preparedStatement.setString(3, client.project_name);
                preparedStatement.setInt(4, client.accepted);
                preparedStatement.setInt(5, client.id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                System.out.println("An error has occurred.");
            }
        }
        Close_Connection();
    }


    public void Add_Employee(Employee employee)
    {
        Open_Connection();
        if(employee.id == 0)
        {
            String command = "insert into Employees (Name, ManagerID, TasksCompleted, Password) values (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(command))
            {
                preparedStatement.setString(1, employee.name);
                preparedStatement.setInt(2, employee.manager_id);
                preparedStatement.setInt(3, employee.tasks_completed);
                preparedStatement.setString(4, employee.password);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        else
        {
            String command = "update Employees set Name = ?, ManagerID = ?, TasksCompleted = ?, Password = ? where ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(command))
            {
                preparedStatement.setString(1, employee.name);
                preparedStatement.setInt(2, employee.manager_id);
                preparedStatement.setInt(3, employee.tasks_completed);
                preparedStatement.setString(4, employee.password);
                preparedStatement.setInt(5, employee.id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        Close_Connection();
    }


    public void Add_Manager(Manager manager)
    {
        Open_Connection();
        if(manager.id == 0)
        {
            String command = "insert into Managers (Name, ProjectsCompleted, Password) values (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(command))
            {
                preparedStatement.setString(1, manager.name);
                preparedStatement.setInt(2, manager.projects_completed);
                preparedStatement.setString(3, manager.password);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        else
        {
            String command = "update Managers set Name = ?, ProjectsCompleted = ?, Password = ? where ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(command))
            {
                preparedStatement.setString(1, manager.name);
                preparedStatement.setInt(2, manager.projects_completed);
                preparedStatement.setString(3, manager.password);
                preparedStatement.setInt(4, manager.id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        Close_Connection();
    }


    public void Add_Task(Task task)
    {
        Open_Connection();
        if(task.id == 0)
        {
            String command = "insert into Tasks (Name, EmployeeID, Finished) values (?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(command))
            {
                preparedStatement.setString(1, task.name);
                preparedStatement.setInt(2, task.employee_id);
                preparedStatement.setInt(3, task.finished);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        else
        {
            String command = "update Tasks set Name = ?, EmployeeID = ?, Finished = ? where ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(command))
            {
                preparedStatement.setString(1, task.name);
                preparedStatement.setInt(2, task.employee_id);
                preparedStatement.setInt(3, task.finished);
                preparedStatement.setInt(4, task.id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        Close_Connection();
    }


    public void Accept_Project(AcceptedProject ap)
    {
        Open_Connection();
        if(ap.id == 0)
        {
            String command = "insert into AcceptedProjects (ProjectID, ManagerID, Finished) values (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(command))
            {
                preparedStatement.setInt(1, ap.project_id);
                preparedStatement.setInt(2, ap.manager_id);
                preparedStatement.setInt(3, ap.finished);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        else
        {
            String command = "update AcceptedProjects set ProjectID = ?, ManagerID = ?, Finished = ? where ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(command))
            {
                preparedStatement.setInt(1, ap.project_id);
                preparedStatement.setInt(2, ap.manager_id);
                preparedStatement.setInt(3, ap.finished);
                preparedStatement.setInt(4, ap.id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                System.out.println("An error has occurred.");
            }
        }
        Close_Connection();
    }


    public ArrayList<Client> Get_Clients()
    {
        Open_Connection();
        ArrayList<Client> clients = new ArrayList<Client>();
        String reader = "select * from Clients";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(reader);
            while (results.next())
            {
                Client new_client = new Client(results.getInt("ID"),
                        results.getString("Name"),
                        results.getString("Phone"),
                        results.getString("ProjectName"),
                        results.getInt("Accepted")
                );
                clients.add(new_client);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong.");
        }
        Close_Connection();
        return clients;
    }


    public ArrayList<Employee> Get_Employees()
    {
        Open_Connection();
        ArrayList<Employee> employees = new ArrayList<Employee>();
        String reader = "select * from Employees";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(reader);
            while (results.next())
            {
                Employee new_employee = new Employee(results.getInt("ID"),
                        results.getString("Name"),
                        results.getInt("ManagerID"),
                        results.getInt("TasksCompleted"),
                        results.getString("Password"));
                employees.add(new_employee);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong.");
        }
        Close_Connection();
        return employees;
    }


    public ArrayList<Manager> Get_Managers()
    {
        Open_Connection();
        ArrayList<Manager> managers = new ArrayList<Manager>();
        String reader = "select * from Managers";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(reader);
            while (results.next())
            {
                Manager new_managers = new Manager(results.getInt("ID"),
                        results.getString("Name"),
                        results.getInt("ProjectsCompleted"),
                        results.getString("Password"));
                managers.add(new_managers);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong.");
        }
        Close_Connection();
        return managers;
    }


    public ArrayList<Task> Get_Tasks()
    {
        Open_Connection();
        ArrayList<Task> tasks = new ArrayList<Task>();
        String reader = "select * from Tasks";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(reader);
            while (results.next())
            {
                Task new_task = new Task(results.getInt("ID"),
                        results.getString("Name"),
                        results.getInt("EmployeeID"),
                        results.getInt("Finished"));
                tasks.add(new_task);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong.");
        }
        Close_Connection();
        return tasks;
    }


    public ArrayList<AcceptedProject> Get_Accepted_Projects()
    {
        Open_Connection();
        ArrayList<AcceptedProject> aps = new ArrayList<AcceptedProject>();
        String reader = "select * from AcceptedProjects";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(reader);
            while (results.next())
            {
                AcceptedProject new_ap = new AcceptedProject(results.getInt("ID"),
                        results.getInt("ProjectID"),
                        results.getInt("ManagerID"),
                        results.getInt("Finished"));
                aps.add(new_ap);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong.");
        }
        Close_Connection();
        return aps;
    }
}
