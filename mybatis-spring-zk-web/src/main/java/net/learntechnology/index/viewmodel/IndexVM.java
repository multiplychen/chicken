package net.learntechnology.index.viewmodel;

import net.learntechnology.empmaint.domain.Employee;
import net.learntechnology.empmaint.services.DepartmentService;
import net.learntechnology.empmaint.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.tien.chicken.domain.Customer;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class IndexVM {
	private final static Logger logger = LoggerFactory.getLogger(IndexVM.class);

	@WireVariable
	private EmployeeService employeeService;
	@WireVariable
	private DepartmentService departmentService;

	private Employee employee;
	private boolean hideForm = true;

	@Command
	public void goToEmployeePage() {
		Executions.getCurrent().sendRedirect("/employee.zul");
	}
	
	@NotifyChange({"employee", "hideForm"})
	@Command
	public void createEmployee() {
		employee = new Employee();
		employee.setDepartment(getDepartments().get(0));
		hideForm = false;
	}

	@NotifyChange({"hideForm"})
	@Command
	public void editEmployee() {
		if (employee != null) hideForm = false;
	}

	@NotifyChange({"employees", "hideForm"})
	@Command
	public void add() {
		employeeService.insert(employee);
		hideForm = true;
	}

	@NotifyChange({"employees", "hideForm"})
	@Command
	public void update() {
		employeeService.update(employee);
		hideForm = true;
	}

	@NotifyChange({"employees", "hideForm"})
	@Command
	public void delete() {
		employeeService.delete(employee.getId());
		hideForm = true;
	}

	@NotifyChange({"employees", "hideForm"})
	@Command
	public void cancel() {
		hideForm = true;
	}

	public List<Employee> getEmployees() {
		return employeeService.fetchAll();
	}

	public boolean isHideForm() {
		return hideForm;
	}

	public List<Customer> getDepartments() {
		return departmentService.fetchAll();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
