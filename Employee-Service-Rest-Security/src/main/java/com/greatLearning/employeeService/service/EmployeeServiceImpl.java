package com.greatLearning.employeeService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatLearning.employeeService.dao.EmployeeRepository;
import com.greatLearning.employeeService.dao.RoleRepository;
import com.greatLearning.employeeService.dao.UserRepository;

//import com.greatLearning.employeeService.dao.RoleRepository;
//import com.greatLearning.employeeService.dao.UserRepository;
import com.greatLearning.employeeService.entity.Employee;
//import com.greatLearning.employeeService.entity.Role;
//import com.greatLearning.employeeService.entity.User;
import com.greatLearning.employeeService.entity.Role;
import com.greatLearning.employeeService.entity.User;

@SuppressWarnings("unused")
@Service
public class EmployeeServiceImpl implements EmployeeService {

	//private EmployeeRepository employeeRepository;
	//private UserRepository userRepository;
	//private RoleRepository roleRepository;

	@Autowired
	EmployeeRepository employeeRepository;
		@Autowired
		RoleRepository roleRepository;
		@Autowired
		UserRepository userRepository;
		//@Autowired
		//BCryptPasswordEncoder bcryptEncoder;
	//	@Autowired
	//public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
	//employeeRepository = theEmployeeRepository;
	//}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theEmployee;
	}

	@Override
	public User save(User theUser) {
		//theUser.setPassword(bcryptEncoder.encode(theUser.getPassword()));
		return userRepository.save(theUser);
	}

	@Override
	public Role save(Role theRole) {
		return roleRepository.save(theRole);
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Employee> sortByFirstNameAsc() {
		// TODO Auto-generated method stub
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public Employee update(int employeeId,Employee theEmployee) {
		Optional<Employee> Optionalemp = employeeRepository.findById(employeeId);
		Employee emp = null;

		if (Optionalemp.isPresent()) {

			emp = Optionalemp.get();
			emp.setFirstName(theEmployee.getFirstName());
			emp.setLastName(theEmployee.getLastName());
			emp.setEmail(theEmployee.getEmail());

			return employeeRepository.save(emp);

		} 
		
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + employeeId);
		}		
	}

	@Override
	public List<Employee> sortByFirstNameDesc() {
		return employeeRepository.findAllByOrderByFirstNameDesc();
	}


	//	@Override
	//	public User saveUser(User user) {
	//		// TODO Auto-generated method stub
	//		user.setPassword(user.getPassword());
	//		return userRepository.save(user);
	//	}
	//
	//	@Override
	//	public Role saveRole(Role role) {
	//		// TODO Auto-generated method stub
	//		return roleRepository.save(role);
	//	}

}
