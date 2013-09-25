package com.saavis.challenge.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Abstract class that models the employee.
 */
public abstract class Employee
{
    /** Unique ID for the employee. **/
    private UUID id;

    /** Set of employees associated with this employee. **/
    private Set<UUID> employees;

    /** The department to which this employee belongs to. **/
    private UUID department;

    /**
     * Constructor for this class.
     * 
     * @param id The unique ID for the employee.
     * @param employees The list of associated employees.
     * @param department The department to which employee belongs to.
     */
    public Employee(UUID id, Set<UUID> employees, UUID department)
    {
        if (id == null)
        {
            throw new IllegalArgumentException("The given 'id' must not be null.");
        }
        
        if (department == null)
        {
            throw new IllegalArgumentException("The given 'department' must not be null.");
        }

        this.id = id;

        if (employees == null)
        {
            employees = new HashSet<UUID>();
        }

        this.employees = employees;
        this.department = department;
    }

    /**
     * @return Returns the id.
     */
    public UUID getId()
    {
        return id;
    }

    /**
     * @return Returns the employees.
     */
    public Set<UUID> getEmployees()
    {
        return employees;
    }

    /**
     * @return Returns the department.
     */
    public UUID getDepartment()
    {
        return department;
    }
    
    /**
     * Adds employee to the employee list to the department.
     * 
     * @param employeeID The employeeID to be added.
     */
    public void addEmployee(UUID employeeID)
    {
        employees.add(employeeID);
    }

    @Override
    public boolean equals(Object other)
    {
        Employee otherEmployee = (Employee) other;

        return this.id.equals(otherEmployee.getId());
    }

    @Override
    public int hashCode()
    {
        return this.id.hashCode();
    }
    
    /**
     * @return The allocation amount for this employee. To be determined by the implementing
     *         classes.
     */
    public abstract Double getAllocation();

}
