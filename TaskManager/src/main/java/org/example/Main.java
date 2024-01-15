package org.example;

import java.sql.Array;
import java.sql.JDBCType;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


public class Main
{
    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            arg_invalid();
            return;
        }
        switch (args[0])
        {
            case "client":
                arg_client(args);
                System.out.println("Goodbye, see you soon!");
                break;
            case "manager":
                arg_manager(args);
                System.out.println("Goodbye, see you soon!");
                break;
            case "employee":
                arg_employee(args);
                System.out.println("Goodbye, see you soon!");
                break;
            default:
                arg_invalid();
                break;
        }
    }

    private static void arg_employee(String[] args)
    {
        if(args.length != 3)
        {
            System.out.println("Please enter your ID and password: employee <Id> <Password>.");
            System.out.println();
            return;
        }

        DBController dbc = new DBController();
        Employee employee = null;
        boolean found = false;

        for(Employee emp:dbc.Get_Employees())
        {
            if(emp.id == Integer.parseInt(args[1]) && emp.password.equals(args[2]))
            {
                found = true;
                employee = emp;
                System.out.println();
                break;
            }
        }

        if(found)
        {
            System.out.println("Access granted!");
            System.out.println("Welcome, " + employee.name + "!");
        }
        else
        {
            System.out.println("Access denied!");
            return;
        }

        System.out.println("What do you want to do?");
        System.out.println("[1] stats - to see your stats.");
        System.out.println("[2] tasks - to see your tasks.");
        System.out.println("[3] finish task - to finish a task.");
        System.out.println("[4] password - to change your password.");
        System.out.println("[5] exit - to exit.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("cmd >> ");
        String cmd = scanner.nextLine();
        System.out.println();

        while(!cmd.equals("exit"))
        {
            switch(cmd)
            {
                case "stats":
                    System.out.println("These are your stats: ");
                    System.out.println();
                    System.out.println("ID: " + employee.id);
                    System.out.println("Name: " + employee.name);
                    System.out.println("Manager ID: " + employee.manager_id);
                    System.out.println("Tasks Completed: " + employee.tasks_completed);
                    System.out.println("Password: " + employee.password);
                    System.out.println();
                    break;
                case "tasks":
                    System.out.println("These are your tasks:");
                    System.out.println();
                    for(Task task:dbc.Get_Tasks())
                    {
                        if (task.employee_id == employee.id && task.finished == 0)
                        {
                            System.out.println("ID: " + task.id);
                            System.out.println("Task: " + task.name);
                            System.out.println();
                        }
                    }
                    break;
                case "finish task":
                    System.out.println("These are your tasks:");
                    System.out.println();
                    for(Task task:dbc.Get_Tasks())
                    {
                        if (task.employee_id == employee.id && task.finished == 0)
                        {
                            System.out.println("ID: " + task.id);
                            System.out.println("Task: " + task.name);
                            System.out.println();
                        }
                    }
                    System.out.println("Which task do you want to finish?");
                    ArrayList<Task> tasks = dbc.Get_Tasks();
                    System.out.print("Please enter the task's ID: ");
                    int id_to_finish;
                    try
                    {
                        id_to_finish = Integer.parseInt(scanner.nextLine());
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid character entered.");
                        id_to_finish = 0;
                    }

                    boolean finished = false;
                    for(Task task:tasks)
                    {
                        if(id_to_finish == task.id && task.finished == 0)
                        {
                            finished = true;
                            task.finished = 1;
                            dbc.Add_Task(task);
                            employee = new Employee(employee.id, employee.name, employee.manager_id, employee.tasks_completed + 1, employee.password );
                            dbc.Add_Employee(employee);
                        }
                    }
                    if(finished)
                        System.out.println("Task finished!");
                    else
                        System.out.println("The task wasn't finished, the ID chosen wasn't a valid one.");
                    System.out.println();
                    break;
                case "password":
                    System.out.println("Please enter a new password: ");
                    System.out.print("Password: ");
                    String new_password = scanner.nextLine();
                    while(new_password.length() <= 0)
                    {
                        System.out.print("PLease enter a longer password!");
                        System.out.print("Password: ");
                        new_password = scanner.nextLine();
                    }
                    employee = new Employee(employee.id, employee.name, employee.manager_id, employee.tasks_completed, new_password);
                    dbc.Add_Employee(employee);
                    System.out.println("Password changed!");
                    System.out.println();

                    break;
                default:
                    System.out.println("Invalid command.");
                    System.out.println("Please choose from these commands:");
                    System.out.println("What do you want to do?");
                    System.out.println("[1] stats - to see your stats.");
                    System.out.println("[2] tasks - to see your tasks.");
                    System.out.println("[3] finish task - to finish a task.");
                    System.out.println("[4] password - to change your password.");
                    System.out.println("[5] exit - to exit.");
                    System.out.println();
                    break;
            }
            System.out.print("cmd >> ");
            cmd = scanner.nextLine();
            System.out.println();
        }
    }

    private static void arg_manager(String[] args)
    {
        if(args.length != 3)
        {
            System.out.println("Please enter your ID and password: manager <Id> <Password>.");
            System.out.println();
            return;
        }

        DBController dbc = new DBController();
        Manager manager = null;
        boolean found = false;

        for(Manager mng:dbc.Get_Managers())
        {
            if(mng.id == Integer.parseInt(args[1]) && mng.password.equals(args[2]))
            {
                found = true;
                manager = mng;
                System.out.println();
                break;
            }
        }

        if(found)
        {
            System.out.println("Access granted!");
            System.out.println("Welcome, " + manager.name + "!");
        }
        else
        {
            System.out.println("Access denied!");
            return;
        }

        System.out.println("What do you want to do?");
        System.out.println("[1] stats - to see your stats.");
        System.out.println("[2] tasks - to see your employee's tasks.");
        System.out.println("[3] set task - to set a task.");
        System.out.println("[4] accepted projects unfinished - to see your accepted projects.");
        System.out.println("[5] accepted projects finished - to see your accepted projects.");
        System.out.println("[6] clients - to see unaccepted clients.");
        System.out.println("[7] accept client - to accept a client.");
        System.out.println("[8] finish project - to finish a project.");
        System.out.println("[9] add employee - to add employee.");
        System.out.println("[10] password - to change your password.");
        System.out.println("[11] employees - to see your employees.");
        System.out.println("[12] exit - to exit");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("cmd >> ");
        String cmd = scanner.nextLine();
        System.out.println();

        while(!cmd.equals("exit"))
        {
            switch(cmd)
            {
                case "stats":
                    System.out.println("These are your stats: ");
                    System.out.println();
                    System.out.println("ID: " + manager.id);
                    System.out.println("Name: " + manager.name);
                    System.out.println("Projects Completed: " + manager.projects_completed);
                    System.out.println("Password: " + manager.password);
                    System.out.println();
                    break;
                case "tasks":
                    System.out.println("These are your employee's tasks:");
                    System.out.println();
                    ArrayList<Integer> employee_ids = new ArrayList<>();
                    for(Employee emp:dbc.Get_Employees())
                    {
                        if(emp.manager_id == manager.id)
                        {
                            employee_ids.add(emp.id);
                        }
                    }
                    for(Task task:dbc.Get_Tasks())
                    {
                        if(employee_ids.contains(task.employee_id))
                        {

                            String emp_name = null;
                            for(Employee emp:dbc.Get_Employees())
                            {
                                if(emp.id == task.employee_id)
                                {
                                    emp_name = emp.name;
                                }
                            }

                            System.out.println("ID: " + task.id);
                            System.out.println("Employee ID: " + task.employee_id);
                            System.out.println("Name: " + emp_name);
                            System.out.println("Task: " + task.name);
                        }
                        System.out.println();
                    }
                    break;
                case "set task":
                    System.out.print("Please enter the name of your task: ");
                    String task_name = scanner.nextLine();
                    while(check_invalid_characters(task_name))
                    {
                        System.out.print("Please enter the name of your task: ");
                        task_name = scanner.nextLine();
                    }
                    System.out.println();
                    for(Employee emp:dbc.Get_Employees())
                    {
                        if(emp.manager_id == manager.id)
                        {
                            System.out.println("ID: " + emp.id);
                            System.out.println("Name: " + emp.name);
                            System.out.println("Tasks Completed: " + emp.tasks_completed);
                        }
                        System.out.println();
                    }
                    System.out.println("Which employee should get this task?");
                    System.out.print("PLease enter his/her ID: ");
                    int id_of_employee;
                    try
                    {
                        id_of_employee = Integer.parseInt(scanner.nextLine());
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid character entered.");
                        id_of_employee = 0;
                    }
                    System.out.println();
                    boolean emp_found = false;
                    for(Employee emp:dbc.Get_Employees())
                    {
                        if(emp.id == id_of_employee)
                        {
                            emp_found = true;
                            dbc.Add_Task(new Task(0, task_name, emp.id, 0));
                        }
                    }
                    if(emp_found)
                        System.out.println("Task added.");
                    else
                        System.out.println("The task wasn't added, the ID chosen wasn't a valid one.");
                    System.out.println();
                    break;
                case "accepted projects unfinished":
                    System.out.println("These are your accepted projects (unfinished):");
                    System.out.println();
                    for(AcceptedProject ap:dbc.Get_Accepted_Projects())
                    {
                        if(ap.manager_id == manager.id && ap.finished == 0)
                        {
                            System.out.println("ID: " + ap.id);
                            System.out.println("Project ID: " + ap.project_id);
                            for(Client client:dbc.Get_Clients())
                            {
                                if(client.id == ap.project_id)
                                {
                                    System.out.println("Project Name: " + client.project_name);
                                    break;
                                }
                            }
                            System.out.println();
                        }
                    }
                    break;
                case "accepted projects finished":
                    System.out.println("These are your accepted projects (finished):");
                    System.out.println();
                    for(AcceptedProject ap:dbc.Get_Accepted_Projects())
                    {
                        if (ap.manager_id == manager.id && ap.finished == 1)
                        {
                            System.out.println("ID: " + ap.id);
                            System.out.println("Project ID: " + ap.project_id);
                            for (Client client : dbc.Get_Clients())
                            {
                                if (client.id == ap.project_id)
                                {
                                    System.out.println("Project Name: " + client.project_name);
                                    break;
                                }
                            }
                            System.out.println();
                        }
                    }
                    break;
                case "clients":
                    System.out.println("These are the waiting clients:");
                    System.out.println();

                    for(Client client:dbc.Get_Clients())
                    {
                        if(client.accepted == 0)
                        {
                            System.out.println("ID: " + client.id);
                            System.out.println("Name: " + client.name);
                            System.out.println("Phone: " + client.phone);
                            System.out.println("Project name: " + client.project_name);
                            System.out.println();
                        }
                    }
                    break;
                case "accept client":
                    System.out.println("Which client do you want to accept?");
                    System.out.println();
                    ArrayList<Client> clients = dbc.Get_Clients();

                    for(Client client:clients)
                    {
                        if(client.accepted == 0)
                        {
                            System.out.println("ID: " + client.id);
                            System.out.println("Name: " + client.name);
                            System.out.println("Phone: " + client.phone);
                            System.out.println("Project name: " + client.project_name);
                            System.out.println();
                        }
                    }
                    System.out.print("Please enter the client's ID: ");
                    int id_to_accept;
                    try
                    {
                        id_to_accept = Integer.parseInt(scanner.nextLine());
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid character entered.");
                        id_to_accept = 0;
                    }
                    boolean accepted = false;
                    for(Client client:clients)
                    {
                        if(id_to_accept == client.id && client.accepted == 0)
                        {
                            accepted = true;
                            client.accepted = 1;
                            dbc.Add_Client(client);

                            dbc.Accept_Project(new AcceptedProject(0, client.id, manager.id, 0));

                        }
                    }
                    if(accepted)
                        System.out.println("Client accepted!");
                    else
                        System.out.println("The client wasn't accepted, the ID chosen wasn't a valid one.");
                    System.out.println();
                    break;
                case "finish project":
                    for(AcceptedProject ap:dbc.Get_Accepted_Projects())
                    {
                        if(ap.manager_id == manager.id && ap.finished == 0)
                        {
                            System.out.println("ID: " + ap.id);
                            System.out.println("Project ID: " + ap.project_id);
                            for(Client client:dbc.Get_Clients())
                            {
                                if(client.id == ap.project_id)
                                {
                                    System.out.println("Project Name: " + client.project_name);
                                    break;
                                }
                            }
                            System.out.println();
                        }
                    }
                    System.out.println("Which project do you want to finish?");
                    ArrayList<AcceptedProject> aps = dbc.Get_Accepted_Projects();
                    System.out.print("Please enter the it's ID: ");
                    int id_to_finish;
                    try
                    {
                        id_to_finish = Integer.parseInt(scanner.nextLine());
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid character entered.");
                        id_to_finish = 0;
                    }
                    System.out.println();
                    boolean finished = false;
                    for(AcceptedProject ap:aps)
                    {
                        if(id_to_finish == ap.id && ap.finished == 0)
                        {
                            finished = true;
                            ap.finished = 1;
                            dbc.Accept_Project(ap);
                            manager.projects_completed += 1;
                            dbc.Add_Manager(manager);
                        }
                    }
                    if(finished)
                        System.out.println("Project finished!");
                    else
                        System.out.println("The project wasn't finished, the ID chosen wasn't a valid one.");
                    System.out.println();
                    break;
                case "add employee":
                    System.out.println("Please enter the following for your employee:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    while(check_invalid_characters(name))
                    {
                        System.out.print("Name: ");
                        name = scanner.nextLine();
                    }
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    while(password.length() <= 0)
                    {
                        System.out.println("Invalid length of the password.");
                        System.out.print("Password: ");
                        password = scanner.nextLine();
                    }
                    dbc.Add_Employee(new Employee(0, name, manager.id, 0, password));
                    System.out.println("Employee added!");
                    System.out.println();
                    break;
                case "employees":
                    System.out.println("These are your employees:");
                    System.out.println();
                    for(Employee emp:dbc.Get_Employees())
                    {
                        if(emp.manager_id == manager.id)
                        {
                            System.out.println("ID: " + emp.id);
                            System.out.println("Name: " + emp.name);
                            System.out.println("Tasks Completed: " + emp.tasks_completed);
                            System.out.println();
                        }
                    }

                    break;
                case "password":
                    System.out.println("Please enter a new password. ");
                    System.out.print("Password: ");
                    String new_password = scanner.nextLine();
                    while(new_password.length() <= 0)
                    {
                        System.out.println("Invalid length of the password.");
                        System.out.print("Password: ");
                        new_password = scanner.nextLine();
                    }
                    manager = new Manager(manager.id, manager.name, manager.projects_completed, new_password);
                    dbc.Add_Manager(manager);
                    System.out.println("Password changed!");
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid command.");
                    System.out.println("Please choose from this commands:");
                    System.out.println("What do you want to do?");
                    System.out.println("[1] stats - to see your stats.");
                    System.out.println("[2] tasks - to see your employee's tasks.");
                    System.out.println("[3] set task - to set a task.");
                    System.out.println("[4] accepted projects unfinished - to see your accepted projects.");
                    System.out.println("[5] accepted projects finished - to see your accepted projects.");
                    System.out.println("[6] clients - to see unaccepted clients.");
                    System.out.println("[7] accept client - to accept a client.");
                    System.out.println("[8] finish project - to finish a project.");
                    System.out.println("[9] add employee - to add employee.");
                    System.out.println("[10] password - to change your password.");
                    System.out.println("[11] exit - to exit.");
                    System.out.println();
                    break;
            }
            System.out.print("cmd >> ");
            cmd = scanner.nextLine();
            System.out.println();
        }
    }

    private static void arg_client(String[] args)
    {
        if(args.length != 4)
        {
            System.out.println("Invalid number of arguments.");
            System.out.println("Use: client <Name> <Phone> <ProjectName>");
            System.out.println();
            return;
        }

        char[] target_chars = {',', '[', ']', '!', '-', '+', '=', '.', '/', '\\', '`',
                '(', ')', '{', '}', ';', ':', '<', '>', '~', '?', '&', '|', '^', '@',
                '#', '$', '%', '*', '_', '\''};



        if (check_invalid_characters(args[1]))
        {
            return;
        }



        if(!args[2].matches("\\d+") || args[2].length() != 10)
        {
            System.out.println("The phone number should only contain digits and should have a length of 10.");
            System.out.println();
            return;
        }

        if (check_invalid_characters(args[3]))
        {
            return;
        }


        Client client = new Client(0, args[1], args[2], args[3], 0);

        DBController dbc = new DBController();
        dbc.Add_Client(client);

        System.out.println("Your request for project has been processed, a manager will contact you soon.");
        System.out.println();
    }

    private static void arg_invalid()
    {
        System.out.println("Invalid argument.");
        System.out.println("Valid arguments: client, manager, employee");
        System.out.println();
    }

    private static boolean check_invalid_characters(String arg)
    {
        char[] target_chars = {',', '[', ']', '!', '-', '+', '=', '.', '/', '\\', '`',
                '(', ')', '{', '}', ';', ':', '<', '>', '~', '?', '&', '|', '^', '@',
                '#', '$', '%', '*', '_', '\''};


        for (char target_char : target_chars) {
            if (arg.indexOf(target_char) >= 0)
            {
                System.out.println("No special characters allowed in the name.");
                System.out.println();
                return true;
            }
        }
        return false;
    }

}