package com.saavis.challenge.manager;

import com.saavis.challenge.domain.Department;
import com.saavis.challenge.domain.Employee;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Class that models the manager which provides methods to add departments, employees and other
 * methods for calculating the allocation.
 */
public class AllocationManager
{
    /** Set of departments. **/
    private Set<Department> departments;

    /** Set of employees. **/
    private Set<Employee> employees;

    /**
     * Default constructor.
     */
    public AllocationManager()
    {
        this.departments = new HashSet<Department>();
        this.employees = new HashSet<Employee>();
    }

    /**
     * Adds departments to the department set.
     * 
     * @param department The department to be added.
     */
    public void addDepartment(Department department)
    {
        if (department == null)
        {
            throw new IllegalArgumentException("The given 'department' must not be null.");
        }

        departments.add(department);
    }

    /**
     * Adds employee to the employee to the employee set.
     * 
     * @param employee The employee to be added.
     */
    public void addEmployee(Employee employee)
    {
        if (employee == null)
        {
            throw new IllegalArgumentException("The given 'employee' must not be null.");
        }

        employees.add(employee);
    }

    /**
     * @return Returns the departments.
     */
    public Set<Department> getDepartments()
    {
        return departments;
    }

    /**
     * @return Returns the employees.
     */
    public Set<Employee> getEmployees()
    {
        return employees;
    }

    /**
     * @param employeeID The unique ID of the employee.
     * @return The employee with given ID. Null if not employee with given ID exists.
     */
    public Employee getEmployee(UUID employeeID)
    {
        if (employeeID == null)
        {
            throw new IllegalArgumentException("The given 'employeeID' must not be null.");
        }

        Employee result = null;

        for (Employee employee : employees)
        {
            if (employee.getId().equals(employeeID))
            {
                result = employee;
                break;
            }
        }

        return result;
    }

    /**
     * @param departmentID The unique ID of the department.
     * @return The department with given ID.
     */
    public Department getDepartment(UUID departmentID)
    {
        if (departmentID == null)
        {
            throw new IllegalArgumentException("The given 'departmentID' must not be null.");
        }

        Department result = null;

        for (Department department : departments)
        {
            if (department.getId().equals(departmentID))
            {
                result = department;
                break;
            }
        }

        return result;
    }

    /**
     * Calculates the allocation until the lowest level for the given employee ID. This method
     * assumes there is not circular dependencies between the employees.
     * 
     * @param employeeID The employee ID for which the allocation that needs to be calculated.
     * @return The allocation calculated.
     */
    public Double getAllocationByEmployeeID(UUID employeeID)
    {
        if (employeeID == null)
        {
            throw new IllegalArgumentException("The given 'employeeID' must not be null.");
        }

        Employee employee = getEmployee(employeeID);

        if (employee == null)
        {
            throw new IllegalArgumentException("No employee exists with given ID.");
        }

        Set<UUID> employees = new HashSet<UUID>();

        employees.add(employeeID);

        addAllChildren(employee, employees);

        return calculateAllocation(employees);
    }

    /**
     * Calculates the allocation until the lowest level for the given department ID. This method
     * assumes there is not circular dependencies between the employees.
     * 
     * @param deptID The department ID for which the allocation that needs to be calculated.
     * @return The allocation calculated.
     */
    public Double getAllocationByDepartmentID(UUID deptID)
    {
        if (deptID == null)
        {
            throw new IllegalArgumentException("The given 'deptID' must not be null.");
        }

        Department department = getDepartment(deptID);

        if (department == null)
        {
            throw new IllegalArgumentException("No department exists with given ID.");
        }

        Set<UUID> employeeIDs = department.getEmployees();

        Set<UUID> resultEmployees = new HashSet<UUID>();

        for (UUID employeeID : employeeIDs)
        {
            Employee employee = getEmployee(employeeID);

            resultEmployees.add(employeeID);

            addAllChildren(employee, resultEmployees);
        }

        return calculateAllocation(resultEmployees);
    }

    /**
     * Helper method to get all children/sub employees and adds it to the set.
     * 
     * @param employee The employee for which all the children are added.
     * @param employees The result set to which the children are added.
     */
    private void addAllChildren(Employee employee, Set<UUID> employees)
    {
        for (UUID subEmployeeID : employee.getEmployees())
        {
            employees.add(subEmployeeID);

            Employee subEmployee = getEmployee(subEmployeeID);

            addAllChildren(subEmployee, employees);
        }
    }

    /**
     * Calculates allocation for all the employees with given set.
     * 
     * @param employees The set of employees for which allocation is to be calculated.
     * @return The calculated allocation for given set of employees.
     */
    private Double calculateAllocation(Set<UUID> employees)
    {
        Double result = 0.0;

        for (UUID employeeID : employees)
        {
            Employee employee = getEmployee(employeeID);

            Double allocation = employee.getAllocation();

            result = result + allocation;
        }

        return result;
    }
}
