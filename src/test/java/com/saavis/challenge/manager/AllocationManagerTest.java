package com.saavis.challenge.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import com.saavis.challenge.domain.Department;
import com.saavis.challenge.domain.Developer;
import com.saavis.challenge.domain.Employee;
import com.saavis.challenge.domain.Manager;
import com.saavis.challenge.domain.Tester;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link AllocationManager}.
 * 
 */
public class AllocationManagerTest
{
    
    private AllocationManager testManager;
    
    @Before
    public void setUp()
    {
        testManager = new AllocationManager();
    }
    
    @Test
    public void testAddNullEmployee()
    {
        try
        {
            testManager.addEmployee(null);
            fail("Expected to throw exception if null employee is passed as argument");
        }
        catch(IllegalArgumentException ex)
        {
            assertTrue(ex.getMessage().contains("The given 'employee' must not be null."));
        }
    }
    
    @Test
    public void testAddAndGetEmployeeByID()
    {
        UUID developerID = UUID.randomUUID();

        Employee developer = new Developer(developerID, new HashSet<UUID>(), UUID.randomUUID());
        
        testManager.addEmployee(developer);
        
        assertEquals(developer, testManager.getEmployee(developerID));
    }
    
    @Test
    public void testAddNullDepartment()
    {
        try
        {
            testManager.addDepartment(null);
            fail("Expected to throw exception if null department is passed as argument");
        }
        catch(IllegalArgumentException ex)
        {
            assertTrue(ex.getMessage().contains("The given 'department' must not be null."));
        }
    }
    
    @Test
    public void testAddAndGetDepartmentByID()
    {
        UUID deptID = UUID.randomUUID();

        Department department = new Department(deptID);
        
        testManager.addDepartment(department);
        
        assertEquals(department, testManager.getDepartment(deptID));
    }
    
    @Test
    public void testGetAllocationWithNullEmployeeID()
    {
        try
        {
            testManager.getAllocationByEmployeeID(null);
            fail("Expected to throw exception if null employeeID is passed as argument");
        }
        catch(IllegalArgumentException ex)
        {
            assertTrue(ex.getMessage().contains("The given 'employeeID' must not be null."));
        }
    }
    
    @Test
    public void testGetAllocationWithNullDepartmentID()
    {
        try
        {
            testManager.getAllocationByDepartmentID(null);
            fail("Expected to throw exception if null departmentID is passed as argument");
        }
        catch(IllegalArgumentException ex)
        {
            assertTrue(ex.getMessage().contains("The given 'deptID' must not be null."));
        }
    }
    
    @Test
    public void testGetAllocationByEmployeeID()
    {
        UUID deptID = UUID.randomUUID();

        Department department = new Department(deptID);

        UUID developerID = UUID.randomUUID();

        Employee developer = new Developer(developerID, new HashSet<UUID>(), deptID);

        UUID testerID = UUID.randomUUID();

        Employee tester = new Tester(testerID, new HashSet<UUID>(), deptID);

        UUID managerA_ID = UUID.randomUUID();

        Set<UUID> managerAEmployees = new HashSet<UUID>();

        managerAEmployees.add(testerID);
        managerAEmployees.add(developerID);

        Employee managerA = new Manager(managerA_ID, managerAEmployees, deptID);

        UUID managerB_ID = UUID.randomUUID();

        Set<UUID> managerBEmployees = new HashSet<UUID>();

        managerBEmployees.add(managerA_ID);

        Employee managerB = new Manager(managerB_ID, managerBEmployees, deptID);

        department.addEmployee(developerID);
        department.addEmployee(testerID);
        department.addEmployee(managerA_ID);
        department.addEmployee(managerB_ID);

        testManager.addDepartment(department);
        testManager.addEmployee(developer);
        testManager.addEmployee(tester);
        testManager.addEmployee(managerA);
        testManager.addEmployee(managerB);

        assertEquals(new Double(2100.0), testManager.getAllocationByEmployeeID(managerB_ID));
    }
    
    @Test
    public void testGetAllocationByDepartmentID()
    {
        UUID deptID = UUID.randomUUID();

        Department department = new Department(deptID);

        UUID developerID = UUID.randomUUID();

        Employee developer = new Developer(developerID, new HashSet<UUID>(), deptID);

        UUID testerID = UUID.randomUUID();

        Employee tester = new Tester(testerID, new HashSet<UUID>(), deptID);

        UUID managerA_ID = UUID.randomUUID();

        Set<UUID> managerAEmployees = new HashSet<UUID>();

        managerAEmployees.add(testerID);
        managerAEmployees.add(developerID);

        Employee managerA = new Manager(managerA_ID, managerAEmployees, deptID);

        UUID managerB_ID = UUID.randomUUID();

        Set<UUID> managerBEmployees = new HashSet<UUID>();

        managerBEmployees.add(managerA_ID);

        Employee managerB = new Manager(managerB_ID, managerBEmployees, deptID);

        department.addEmployee(developerID);
        department.addEmployee(testerID);
        department.addEmployee(managerA_ID);
        department.addEmployee(managerB_ID);

        testManager.addDepartment(department);
        testManager.addEmployee(developer);
        testManager.addEmployee(tester);
        testManager.addEmployee(managerA);
        testManager.addEmployee(managerB);

        assertEquals(new Double(2100.0), testManager.getAllocationByDepartmentID(deptID));
    }
    
}
