import Enums.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner sc = new Scanner(System.in);

    /**
     * Database of all employees.
     */
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    /**
     * Wrapper method for System.out.print
     * @param s The message to print.
     */
    void p(String s)
    {
        System.out.print(s);
    }

    char askMenu()
    {
        p("> Select an option below:\n");
        p("\t [1]: Register a new account.\r\n");
        p("\t [2]: Login as a user.\n");
        p("\t [3]: Time-in and time-out users.\n");
        p("\t [4]: Manager user information.\n");
        p("\t [5]: Generate payslip.\n");

        return sc.nextLine().charAt(0);
    }

    char askEmployeeActions()
    {
        p("> Employee Actions:\n");
        p("\t [1]: Reset / Change Password.\r\n");
        p("\t [2]: Logout.\n");

        return sc.nextLine().charAt(0);
    }
    Employee askEmployee(){
        Employee employee = new Employee();
        p("> Create New Employee:\r\n");

        p("\t First Name: ");
        employee.setFirstName(sc.nextLine());

        p("\t Middle Name: ");
        employee.setMiddleName(sc.nextLine());

        p("\t Last Name: ");
        employee.setLastName(sc.nextLine());

        p("\t Address: ");
        employee.setAddress(sc.nextLine());

        p("\t Birthdate (M/d/yyyy): ");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        employee.setBirthDate(LocalDate.parse(sc.nextLine(), dateFormat));

        p("\t Marital Status (Single, Married, Widowed, Divorced, Separated, RegisteredPartnership): ");
        employee.setMaritalStatus(MaritalStatus.valueOf(sc.nextLine()));

        p("\t Position (Chairman, CEO, CFO, CMO, CHRO, SeniorVP, VP, AssistantVP, Manager, SeniorManager, AssistantManager, Executive, Admin, Clerks, Interns): ");
        employee.setPosition(EmployeePosition.valueOf(sc.nextLine()));

        p("\t Rate per Hour: ");
        employee.setRatePerHour(Double.parseDouble(sc.nextLine()));

        p("\t Type of Payroll (Semi, Monthly): ");
        employee.setPayrollType(PayrollType.valueOf(sc.nextLine()));

        p("\t Department (Marketing, Operations, Finance, Sales, HR, Purchase): ");
        employee.setDepartments(Departments.valueOf(sc.nextLine()));

        p("\t Dependant's Employee Id (-1 if none): ");
        int dependant = Integer.parseInt(sc.nextLine());

        if (dependant > -1)
            employee.setDependent(employees.stream().filter(x -> x.getId() == dependant).collect(Collectors.toList()).get(0));

        employee.setCredentials(askCredentials());

        return employee;
    }

    Credentials askCredentials(){
        Credentials creds = new Credentials(null,null);
        p("\t [Credentials] Username: ");
        creds.setUsername(sc.nextLine());
        p("\t [Credentials] Password: ");
        creds.setPassword(sc.nextLine());
        return creds;
    }

    void employeeActions(Employee selectedEmployee) {
        boolean exiting = false;
        while (!exiting) {
            switch (askEmployeeActions())
            {
                case '1':
                    p("\t Input New Password: ");
                    selectedEmployee.getCredentials().setPassword(sc.nextLine());
                    p("\t Password set! ");
                    break;
                case '2':
                    exiting = true;
                    break;
            }
        }

    }

    char askEmployeeDataActions()
    {
        p("> Employee Actions:\n");
        p("\t [1]: Reset data.\r\n");
        p("\t [2]: Deactivate.\r\n");
        p("\t [3]: Logout.\n");

        return sc.nextLine().charAt(0);
    }

    void employeeDataActions(Employee selectedEmployee) {
        boolean exiting = false;
        while (!exiting) {
            switch (askEmployeeDataActions())
            {
                case '1':
                    var newData = askEmployee();
                    newData.setId(selectedEmployee.getId());
                    p("\t Data has been replaced!\n");
                    break;
                case '2':
                    selectedEmployee.deactivate();
                    break;
                case '3':
                    exiting = true;
                    break;
            }
        }
    }

    public void start()
    {
        while(true)
        {
            p("Welcome to Management! " + /* lambda */ employees.stream().filter(x -> x.getEmployeeStatus() == EmployeeStatus.Normal).count() + " total employees online!\r\n");
            switch (askMenu())
            {
                case '1':
                {
                    var em = askEmployee();
                    em.setId(employees.size());
                    employees.add(em);
                }
                    break;
                case '2':
                {
                    p("> Login: \r\n");
                    var creds = askCredentials();

                    // Find matching employee.
                    var matchingAccounts = employees.stream().filter(x -> x.getCredentials().equals(creds)).collect(Collectors.toList());
                    if (matchingAccounts.isEmpty()) {
                        p("> User does not exist.\r\n");
                        continue;
                    }

                    employeeActions(matchingAccounts.get(0));
                }
                    break;
                case '3':
                {
                    p("> Employee Id: \r\n");
                    int id = sc.nextInt();
                    var employeeOfId = employees.stream().filter(x -> x.getId() == id).collect(Collectors.toList());
                    if (employeeOfId.isEmpty()){
                        p("\t No matching employee has that id!");
                        continue;
                    }
                    var employee = employeeOfId.get(0);
                    if (employee.getTimeInTimeOutRecords().size() % 2 == 0)
                    {
                        // Ask time in!
                        p("\t Input time IN (M/d/yyyy HH:mm:ss): ");
                    }
                    else
                        p("\t Input time OUT (M/d/yyyy HH:mm:ss): ");
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
                    employee.getTimeInTimeOutRecords().add(LocalDateTime.parse(sc.nextLine(), format));
                }
                    break;
                case '4':
                {
                    p("> Employee Id: \r\n");
                    int id = sc.nextInt();
                    var employeeOfId = employees.stream().filter(x -> x.getId() == id).collect(Collectors.toList());
                    if (employeeOfId.isEmpty()){
                        p("\t No matching employee has that id!");
                        continue;
                    }
                    var employee = employeeOfId.get(0);
                    p("\t Employee Data: ");
                    p("\t " + employee.toString());
                    employeeDataActions(employee);
                }
                    break;
                case '5':
                {
                    p("> Employee Id: \r\n");
                    int id = sc.nextInt();
                    var employeeOfId = employees.stream().filter(x -> x.getId() == id).collect(Collectors.toList());
                    if (employeeOfId.isEmpty()){
                        p("\t No matching employee has that id!");
                        continue;
                    }

                    var employee = employeeOfId.get(0);

                    // Calculate total hours.
                    var minTime = LocalDateTime.now();
                    switch (employee.getPayrollType())
                    {
                        case Semi:
                        {
                            minTime.minusMonths(6);
                        }
                        break;
                        case Monthly:
                        {
                            minTime.minusMonths(1);
                        }
                        break;
                    }

                    long totalHours = 0;
                    LocalDateTime lastDate = null;
                    for (int i = 0; i < employee.getTimeInTimeOutRecords().size(); i++)
                    {
                        var currentDate = employee.getTimeInTimeOutRecords().get(i);

                        if (currentDate.isAfter(minTime))
                        {
                            // Calculate total hours.
                            if (i % 2 == 0)
                            {
                                // Time In.
                                lastDate = currentDate;
                            }
                            else
                            {
                                // Time out.
                                if (lastDate != null)
                                {
                                    totalHours += ChronoUnit.HOURS.between(lastDate, currentDate);
                                }
                            }
                        }
                    }

                    var grossIncome = totalHours *  employee.getRatePerHour();
                    long taxDeduction = 0;

                    for(Employee i = employee.getDependent(); i != null; i = i.getDependent())
                        taxDeduction += 1000;


                    p("\t Employee Id: " + employee.getId());
                    p("\r\n");
                    p("\t Employee Name: " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getLastName());
                    p("\r\n");
                    p("\t Rate Per Hour: " + employee.getRatePerHour());
                    p("\r\n");
                    p("\t Department: " + employee.getDepartments());
                    p("\r\n");
                    p("\t Position: " + employee.getPosition());
                    p("\r\n");
                    p("\t Total Hours: " + totalHours);
                    p("\r\n");
                    p("\t Gross Income: " + grossIncome);
                    p("\r\n");
                    p("\t Tax Deduction: " + taxDeduction);
                    p("\r\n");
                    p("\t Net Income: " + (grossIncome - taxDeduction));
                    p("\r\n");
                }
                    break;

            }
        }
    }

    public static void main(String[] args) {
        new Main().start();
    }
}