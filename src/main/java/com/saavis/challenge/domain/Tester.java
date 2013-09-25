package com.saavis.challenge.domain;

import java.util.Set;
import java.util.UUID;

/**
 * Sub class of employee that models tester.
 */
public class Tester extends Employee
{
    /**
     * Constructor for this class.
     * 
     * @param id The unique ID for the employee.
     * @param employees The list of associated employees.
     * @param employeeType The employee type/position
     * @param department The department to which employee belongs to.
     */
    public Tester(UUID id, Set<UUID> employees, UUID department)
    {
        super(id, employees, department);
    }

    /**
     * @see com.saavis.challenge.domain.Employee#getAllocation()
     */
    @Override
    public Double getAllocation()
    {
        return 500.0;
    }
}
