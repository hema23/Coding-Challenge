package com.saavis.challenge.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Class that models the department.
 */
public class Department
{
    /** Unique ID for the department. **/
    private UUID id;

    /** Set of employees for this department. **/
    private Set<UUID> employees;

    /**
     * Default constructor for this class.
     * 
     * @param id The department ID.
     * @param employees The list of emplyeeID associated.
     */
    public Department(UUID id)
    {
        if (id == null)
        {
            throw new IllegalArgumentException("The given 'id' must not be null.");
        }

        this.id = id;
        this.employees = new HashSet<UUID>();
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
     * Adds employee to the employee list to the department.
     * 
     * @param employeeID The employeeID to be added.
     */
    public void addEmployee(UUID employeeID)
    {
        employees.add(employeeID);
    }

}
