import Enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Employee {
    private Credentials credentials;
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private LocalDate birthDate;
    private MaritalStatus maritalStatus;
    private EmployeePosition position;
    private double ratePerHour;
    private PayrollType payrollType;
    private Departments departments;
    private EmployeeStatus employeeStatus;
    private Employee dependent;
    private ArrayList<LocalDateTime> timeInTimeOutRecords = new ArrayList<LocalDateTime >();

    public Employee() {
        this(null, 0, null, null, null, null, null, null, null, 0.0, null, null, null);
    }

    public ArrayList<LocalDateTime> getTimeInTimeOutRecords(){
        return timeInTimeOutRecords;
    }

    public Employee(Credentials credentials,
                    int id,
                    String lastName,
                    String firstName,
                    String middleName,
                    String address,
                    LocalDate birthDate,
                    MaritalStatus maritalStatus,
                    EmployeePosition position,
                    double ratePerHour,
                    PayrollType payrollType,
                    Departments departments,
                    EmployeeStatus employeeStatus) {
        this(credentials, id, lastName, firstName, middleName, address, birthDate, maritalStatus, position, ratePerHour, payrollType, departments, employeeStatus, null);
    }

    public String toString(){
        return "Employee Id: " + id + ", Full Name: " + firstName + " " + middleName + " " + lastName + ", Address: " + address + ", Birthdate: " + birthDate + ", Marital Status: " +
                maritalStatus + ", Position: " + position + ", Rate Per Hour: " + ratePerHour + ", Payroll Type: " + payrollType + ", Department: " + departments +
                ", Employee Status: " + employeeStatus + "\r\n\tDependent: " + (dependent == null ? "None!" : dependent.toString());
    }

    public Employee(Credentials credentials,
                    int id,
                    String lastName,
                    String firstName,
                    String middleName,
                    String address,
                    LocalDate birthDate,
                    MaritalStatus maritalStatus,
                    EmployeePosition position,
                    double ratePerHour,
                    PayrollType payrollType,
                    Departments departments,
                    EmployeeStatus employeeStatus,
                    Employee dependent) {

        this.credentials = credentials;
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.birthDate = birthDate;
        this.maritalStatus = maritalStatus;
        this.position = position;
        this.ratePerHour = ratePerHour;
        this.payrollType = payrollType;
        this.departments = departments;
        this.employeeStatus = employeeStatus;
        this.dependent = dependent;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public PayrollType getPayrollType() {
        return payrollType;
    }

    public void setPayrollType(PayrollType payrollType) {
        this.payrollType = payrollType;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void deactivate() {
        this.employeeStatus = EmployeeStatus.Deactivated;
    }

    public Employee getDependent() {
        return dependent;
    }

    public void setDependent(Employee dependent) {
        this.dependent = dependent;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}

