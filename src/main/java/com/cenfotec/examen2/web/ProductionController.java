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

import com.cenfotec.examen2.domain.Production;
import com.cenfotec.examen2.domain.Farm;
import com.cenfotec.examen2.repository.FarmRepository;
import com.cenfotec.examen2.repository.ProductionRepository;;

@Controller
public class ProductionController {

	@Autowired
	ProductionRepository repoProd;
	@Autowired
	FarmRepository repoFarm;
	
	@RequestMapping(value = "/productions", method = RequestMethod.GET)
	public String listEmployees(Model model) {
		model.addAttribute("blankFarm", new Farm());
		model.addAttribute("farms", repoFarm.findAll());
		model.addAttribute("productions", repoProd.findAll());
		return "prod/list";
	}
	
	@RequestMapping(value = "/findProds", method = RequestMethod.POST)
	public String findByNameOrLastName(Farm f, BindingResult result, Model model) {

		model.addAttribute("blankFarm", f);
		model.addAttribute("farms", repoFarm.findAll());
		model.addAttribute("productions", repoProd.findByFarmId(f.getId()));

		return "prod/list";
	}
	
	@RequestMapping(value = "/saveProd/{farmId}", method = RequestMethod.POST)
	public RedirectView saveEmployee(Production newProd, BindingResult result, Model model, @PathVariable Long farmId) {

		Farm farm = new Farm();
		
		Optional<Farm> found = repoFarm.findById(farmId);
		if (found.isPresent()) {
			farm = found.get();
		}
		newProd.setFarm(farm);
		newProd = repoProd.save(newProd);

		return new RedirectView("/farm/" + farmId);
	}
	
	@RequestMapping(value = "/addProd/{farmId}", method = RequestMethod.GET)
	public String employee(Model model, @PathVariable Long farmId) {

		model.addAttribute("newProd", new Production());
		model.addAttribute("farmId", farmId);

		return "prod/form";
	}
}
