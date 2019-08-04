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

import com.cenfotec.examen2.domain.Farm;
import com.cenfotec.examen2.repository.EmployeeRepository;
import com.cenfotec.examen2.repository.FarmRepository;

@Controller
public class FarmController {
	@Autowired
	FarmRepository repoFarm;
	@Autowired
	EmployeeRepository repoEmp;

	@RequestMapping(value = "/farms", method = RequestMethod.GET)
	public String listFarms(Model model) {
		model.addAttribute("newFarm", new Farm());
		model.addAttribute("farms", repoFarm.findAll());
		return "farm/list";
	}
	
	@RequestMapping(value = "/farm/{id}", method = RequestMethod.GET)
	public String farmDetail(Model model, @PathVariable Long id) {
		Farm farm = null;
		Optional<Farm> found = repoFarm.findById(id);
		if (found.isPresent()) {
			farm = found.get();
			farm.setEmployeeList(repoEmp.findByFarmId(farm.getId()));
		}
		model.addAttribute("farm", farm);
		return "farm/detail";
	}

	@RequestMapping(value = "/addFarm", method = RequestMethod.GET)
	public String farm(Model model) {
		model.addAttribute("newFarm", new Farm());
		return "farm/form";
	}
	
	@RequestMapping(value = "/updateFarm/{id}", method = RequestMethod.GET)
	public String updateFarm(Model model, @PathVariable Long id) {
		Farm farm = new Farm();
		Optional<Farm> found = repoFarm.findById(id);
		if (found.isPresent()) {
			farm = found.get();
		}
		model.addAttribute("newFarm", farm);
		return "farm/form";
	}

	@RequestMapping(value = "/saveFarm", method = RequestMethod.POST)
	public RedirectView saveFarm(Farm newFarm, BindingResult result, Model model) {
		repoFarm.save(newFarm);
		return new RedirectView("farms");
	}
}
