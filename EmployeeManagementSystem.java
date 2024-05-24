package rasel;

import java.io.*;
import java.util.*;

class Employee implements Serializable {
    int id;
    String name;
    String desgn;
    String gender;
    String branch;
    double sal;
    String psaddr;
    String prtaddr;
    String phone;
    String mail;

    public Employee(int id, String name, String desgn, String gender, String branch, double sal, String psaddr, String prtaddr, String phone, String mail) {
        this.id = id;
        this.name = name;
        this.desgn = desgn;
        this.gender = gender;
        this.branch = branch;
        this.sal = sal;
        this.psaddr = psaddr;
        this.prtaddr = prtaddr;
        this.phone = phone;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nNAME: %s\nDESIGNATION: %s\nGENDER: %s\nBRANCH: %s\nSALARY: %.2f\nPRESENT ADDRESS: %s\nPERMANENT ADDRESS: %s\nPHONE: %s\nE-MAIL: %s\n",

                              id, name, desgn, gender, branch, sal, psaddr, prtaddr, phone, mail);
    }
}

public class EmployeeManagementSystem {
    private static void printHead() {
        // Implement your header printing logic here
    }

    private static List<Employee> readEmployees(File file) {
        List<Employee> employees = new ArrayList<>();
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    Employee e = (Employee) ois.readObject();
                    employees.add(e);
                }
            } catch (EOFException eof) {
                // End of file reached
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }

    private static void writeEmployees(File file, List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Employee e : employees) {
                oos.writeObject(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(File file) {
        List<Employee> employees = readEmployees(file);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Designation: ");
        String desgn = scanner.nextLine();

        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter Branch: ");
        String branch = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double sal = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Present Address: ");
        String psaddr = scanner.nextLine();

        System.out.print("Enter Permanent Address: ");
        String prtaddr = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter E-Mail: ");
        String mail = scanner.nextLine();

        Employee e = new Employee(id, name, desgn, gender, branch, sal, psaddr, prtaddr, phone, mail);
        employees.add(e);

        writeEmployees(file, employees);

        System.out.println("Employee added successfully!");
    }

    private static void displayList(File file) {
        printHead();
        System.out.println("\n\t\t\t\t\t\t\t\t\t List of Employees");
        System.out.println("\t\t\t\t\t\t\t\t      -----------------------");
        List<Employee> employees = readEmployees(file);
        for (Employee e : employees) {
            System.out.println(e);
            System.out.println("\t\t\t\t\t\t\t     ===============================================");
        }
        System.out.println("\n\n\n\t");
        System.out.println("\n\n\t\t\t\t\t\t\t     Enter any key to continue...");
        new Scanner(System.in).nextLine();
    }

    private static void searchRecord(File file) {
        Scanner scanner = new Scanner(System.in);
        char another = 'y';

        while (another == 'y' || another == 'Y') {
            System.out.println("\n\t\t\t\t\t\t\t\t\tSearch Employee");
            System.out.println("\t\t\t\t\t\t\t\t     ---------------------");
            System.out.print("\n\n\t\t\t\t\t\t       Enter ID Number of Employee to search the record: ");
            int tempid = scanner.nextInt();

            boolean found = false;
            List<Employee> employees = readEmployees(file);
            for (Employee e : employees) {
                if (e.id == tempid) {
                    System.out.println(e);
                    System.out.println("\t\t\t\t\t\t\t\t========================");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\n\n\t\t\t\t\t\t\t\t      ERROR, RECORD NOT FOUND!");
            }

            System.out.println("\n\n\t\t\t\t\t\t\tWant to enter another search (Y/N)");
            another = scanner.next().charAt(0);
        }
    }

    private static void maleemp(File file) {
        printHead();
        System.out.println("\n\t\t\t\t\t\t\t\t      List of Male Employee");
        System.out.println("\t\t\t\t\t\t\t\t    -------------------------");
        List<Employee> employees = readEmployees(file);
        for (Employee e : employees) {
            if (e.gender.equalsIgnoreCase("male")) {
                System.out.printf("ID: %d\nNAME: %s\nDESIGNATION: %s\nPHONE: %s\n\t",
                                  e.id, e.name, e.desgn, e.phone);
                System.out.println("\n\t\t\t\t\t\t\t      ======================================");
            }
        }
        System.out.println("\n\t");
        System.out.println("\n\n\t\t\t\t\t\t\t           Enter any key to continue...");
        new Scanner(System.in).nextLine();
    }

    private static void femaleemp(File file) {
        printHead();
        System.out.println("\n\t\t\t\t\t\t\t\t      List of Female Employee");
        System.out.println("\t\t\t\t\t\t\t\t    ---------------------------");
        List<Employee> employees = readEmployees(file);
        for (Employee e : employees) {
            if (e.gender.equalsIgnoreCase("female")) {
                System.out.printf("ID: %d\nNAME: %s\nDESIGNATION: %s\nPHONE: %s\n\t",
                                  e.id, e.name, e.desgn, e.phone);
                System.out.println("\n\t\t\t\t\t\t\t      ======================================");
            }
        }
        System.out.println("\n\t");
        System.out.println("\n\n\t\t\t\t\t\t\t           Enter any key to continue...");
        new Scanner(System.in).nextLine();
    }

    private static void frmdhaka(File file) {
        printHead();
        System.out.println("\n\t\t\t\t\t\t\t\t    List of Employee from Dhaka");
        System.out.println("\t\t\t\t\t\t\t\t  -------------------------------");
        List<Employee> employees = readEmployees(file);
        for (Employee e : employees) {
            if (e.prtaddr.toLowerCase().contains("dhaka")) {
                System.out.printf("ID: %d\nNAME: %s\nDESIGNATION: %s\nPHONE: %s\nE-MAIL: %s\n\t",
                                  e.id, e.name, e.desgn, e.phone, e.mail);
                System.out.println("\n\t\t\t\t\t\t\t      ======================================");
            }
        }
        System.out.println("\n\t");
        System.out.println("\n\n\t\t\t\t\t\t\t           Enter any key to continue...");
        new Scanner(System.in).nextLine();
    }

    private static void frmors(File file) {
        printHead();
        System.out.println("\n\t\t\t\t\t\t\t       List of Employee from Others District");
        System.out.println("\t\t\t\t\t\t\t     -----------------------------------------");
        List<Employee> employees = readEmployees(file);
        for (Employee e : employees) {
            if (!e.prtaddr.toLowerCase().contains("dhaka")) {
                System.out.printf("ID: %d\nNAME: %s\nDESIGNATION: %s\nPHONE: %s\nE-MAIL: %s\n\t",
                                  e.id, e.name, e.desgn, e.phone, e.mail);
                System.out.println("\n\t\t\t\t\t\t\t      ======================================");
            }
        }
        System.out.println("\n\t");
        System.out.println("\n\n\t\t\t\t\t\t\t           Enter any key to continue...");
        new Scanner(System.in).nextLine();
    }

    private static void displaybasic(File file) {
        printHead();
        System.out.println("\n\t\t\t\t\t\t\t      List of Employee Basic Information");
        System.out.println("\t\t\t\t\t\t\t    ---------------------------------------");
        List<Employee> employees = readEmployees(file);
        for (Employee e : employees) {
            System.out.printf("ID: %d\nNAME: %s\nDESIGNATION: %s\nBRANCH: %s\nSALARY: %.2f\n\t",
                              e.id, e.name, e.desgn, e.branch, e.sal);
            System.out.println("\n\t\t\t\t\t\t\t      ======================================");
        }
        System.out.println("\n\t");
        System.out.println("\n\n\t\t\t\t\t\t\t           Enter any key to continue...");
        new Scanner(System.in).nextLine();
    }
    public static void clearScreen() {
        // This only works in the console/terminal, not in IDE consoles.
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void about() {
        clearScreen();
        System.out.println("\n\n\n");
        System.out.println("\t\t\t\t\t\t                   ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("\t\t\t\t\t\t                   ::          Project Details & About Programmer          ::");
        System.out.println("\t\t\t\t\t\t                   ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        System.out.println("\t\t    :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("\t\t    ::                                  Project Name : Employee Management System (EMS)                                ::");
        System.out.println("\t\t    ::                                ---------------------------------------------------                              ::");
        System.out.println("\t\t    ::                                                                                                                 ::");
        System.out.println("\t\t    ::                                             ----------------------                                              ::");
        System.out.println("\t\t    ::                                                  Submitted By                                                   ::");
        System.out.println("\t\t    ::                                             ----------------------                                              ::");
        System.out.println("\t\t    ::                                        Name : Md. Rasel Hossain                                                 ::");
        System.out.println("\t\t    ::                                   ID Number : 0242220005341013                                                  ::");
        System.out.println("\t\t    ::                                       Batch : 39th                                                              ::");
        System.out.println("\t\t    ::                                     Section : A                                                                 ::");
        System.out.println("\t\t    ::                                  Department : Software Engineering - SWE                                        ::");
        System.out.println("\t\t    ::                                   Institute : Daffodil International University                                 ::");
        System.out.println("\t\t    ::                                                                                                                 ::");
        System.out.println("\t\t    ::                                             ----------------------                                              ::");
        System.out.println("\t\t    ::                                                  Submitted To                                                   ::");
        System.out.println("\t\t    ::                                             ----------------------                                              ::");
        System.out.println("\t\t    ::                                                 Akash Ghosh                                                     ::");
        System.out.println("\t\t    ::                                                   Lecturer                                                      ::");
        System.out.println("\t\t    ::                                    Department of Software Engineering - SWE                                     ::");
        System.out.println("\t\t    ::                                        Daffodil International University                                        ::");
        System.out.println("\t\t    ::                                                                                                                 ::");
        System.out.println("\t\t    ::                                             ----------------------                                              ::");
        System.out.println("\t\t    ::                                                 Submission Date                                                 ::");
        System.out.println("\t\t    ::                                             ----------------------                                              ::");
        System.out.println("\t\t    ::                                                   24 May, 2024                                                  ::");
        System.out.println("\t\t    ::                                                                                                                 ::");
        System.out.println("\t\t    ::                           Copyright | 2024 - Md. Rasel Hossain. All Right Reserved.                             ::");
        System.out.println("\t\t    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        System.out.println("\n\n\t\t\t\t\t\t\t\tEnter any key to continue...");
        new Scanner(System.in).nextLine(); // Wait for user input
    }
    public static void logo() {
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("\t\t\t\t\t\t\t     ____ _  _ ___  _    ____ _   _ ____ ____");
        System.out.println("\t\t\t\t\t\t\t     |___ |\\/| |__] |    |  |  \\_/  |___ |___");
        System.out.println("\t\t\t\t\t\t\t     |___ |  | |    |___ |__|   |   |___ |___");
        System.out.println("\t\t\t\t\t\t\t _  _ ____ _  _ ____ ____ ____ _  _ ____ _  _ ___");
        System.out.println("\t\t\t\t\t\t\t |\\/| |__| |\\ | |__| | __ |___ |\\/| |___ |\\ |  |");
        System.out.println("\t\t\t\t\t\t\t |  | |  | | \\| |  | |__] |___ |  | |___ | \\|  |");
        System.out.println("\t\t\t\t\t\t\t          ____ _   _ ____ ___ ____ _  _");
        System.out.println("\t\t\t\t\t\t\t          [__   \\_/  [__   |  |___ |\\/|");
        System.out.println("\t\t\t\t\t\t\t          ___]   |   ___]  |  |___ |  |");
        System.out.println("\n\n\n");
    }

    public static void main(String[] args) {
        File employeeFile = new File("employees.dat");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        logo();
        about();


        while (!exit) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Display Male Employees");
            System.out.println("5. Display Female Employees");
            System.out.println("6. Display Employees from Dhaka");
            System.out.println("7. Display Employees from Other Districts");
            System.out.println("8. Display Basic Information of All Employees");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(employeeFile);
                    break;
                case 2:
                    displayList(employeeFile);
                    break;
                case 3:
                    searchRecord(employeeFile);
                    break;
                case 4:
                    maleemp(employeeFile);
                    break;
                case 5:
                    femaleemp(employeeFile);
                    break;
                case 6:
                    frmdhaka(employeeFile);
                    break;
                case 7:
                    frmors(employeeFile);
                    break;
                case 8:
                    displaybasic(employeeFile);
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter again.");
            }
        }
    }
}
