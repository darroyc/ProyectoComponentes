package com.cenfotec.examen2.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.cenfotec.examen2.domain.Employee;
import com.cenfotec.examen2.domain.Farm;
import com.cenfotec.examen2.repository.EmployeeRepository;
import com.cenfotec.examen2.repository.FarmRepository;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepository repoEmp;
	@Autowired
	FarmRepository repoFarm;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String listEmployees(Model model) {
		model.addAttribute("blankEmployee", new Employee());
		model.addAttribute("employees", repoEmp.findAll());
		model.addAttribute("farms", repoFarm.findAll());
		return "employee/list";
	}

	@RequestMapping(value = "/addEmployee/{farmId}", method = RequestMethod.GET)
	public String employee(Model model, @PathVariable Long farmId) {

		model.addAttribute("newEmployee", new Employee());
		model.addAttribute("farmId", farmId);

		return "employee/form";
	}

	@RequestMapping(value = "/saveEmp/{farmId}", method = RequestMethod.POST)
	public RedirectView saveEmployee(Employee newEmp, BindingResult result, Model model, @PathVariable Long farmId) {

		Farm farm = new Farm();
		Optional<Farm> found = repoFarm.findById(farmId);
		if (found.isPresent()) {
			farm = found.get();
		}
		newEmp.setFarm(farm);
		newEmp = repoEmp.save(newEmp);

		return new RedirectView("/farm/" + farmId);
	}

	@RequestMapping(value = "/employee/delete/{empId}", method = RequestMethod.GET)
	public RedirectView deleteEmployee(Model model, @PathVariable Long empId) {
		Employee e = null;
		Long farmId;
		Optional<Employee> found = repoEmp.findById(empId);
		if (found.isPresent()) {
			e = found.get();
		}

		farmId = e.getFarm().getId();
		e.setState(0);
		repoEmp.save(e);
		return new RedirectView("/farm/" + farmId);
	}

	@RequestMapping(value = "/findEmployee", method = RequestMethod.POST)
	public String findByNameOrLastName(Employee e, BindingResult result, Model model) {

		model.addAttribute("blankEmployee", e);
		model.addAttribute("farms", repoFarm.findAll());
		model.addAttribute("employees", repoEmp.findByNameOrLastName(e.getName(), e.getLastName(), e.getFarmId()));

		return "employee/list";
	}
}
