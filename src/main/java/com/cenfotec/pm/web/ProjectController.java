package com.cenfotec.pm.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.cenfotec.pm.domain.Activity;
import com.cenfotec.pm.domain.Project;
import com.cenfotec.pm.repository.ActivityRepository;
import com.cenfotec.pm.repository.ProjectRepository;

@Controller
public class ProjectController {

	@Autowired
	ProjectRepository repoProj;
	@Autowired
	ActivityRepository repoAct;

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String listProjects(Model model) {
		model.addAttribute("blankProject", new Project());
		model.addAttribute("projects", repoProj.findAll());
		return "project/list";
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.GET)
	public String project(Model model) {

		model.addAttribute("newProject", new Project());

		return "project/form";
	}
	
	@RequestMapping(value = "/saveProj", method = RequestMethod.POST)
	public RedirectView saveProj(Project newProj, BindingResult result, Model model) {
		newProj = repoProj.save(newProj);
		return new RedirectView("/projects");
	}
	
	
	@RequestMapping(value = "/project/delete/{id}", method = RequestMethod.GET)
	public RedirectView deleteEmployee(Model model, @PathVariable Long id) {
		Project e = null;
		Optional<Project> found = repoProj.findById(id);
		if (found.isPresent()) {
			e = found.get();
		}

		e.setState(0);
		repoProj.save(e);
		return new RedirectView("/projects");
	}
	
	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public String projDetail(Model model, @PathVariable Long id) {
		Project p = null;
		Optional<Project> found = repoProj.findById(id);
		if (found.isPresent()) {
			p = found.get();
			p.setActivities(repoAct.findByProjectId(p.getId()));
		}
		model.addAttribute("p", p);
		model.addAttribute("blankActivity", new Activity());
		return "project/detail";
	}
	
	@RequestMapping(value = "/editProject/{id}", method = RequestMethod.GET)
	public String projUpdate(Model model, @PathVariable Long id) {
		Project p = null;
		Optional<Project> found = repoProj.findById(id);
		if (found.isPresent()) {
			p = found.get();
			p.setActivities(repoAct.findByProjectId(p.getId()));
		}
		model.addAttribute("p", p);
		return "project/edit";
		
	}
	
	@RequestMapping(value = "/updateProj/{id}", method = RequestMethod.POST)
	public RedirectView updateProj(@PathVariable Long id, Project p, BindingResult result, Model model) {
		p.setId(id);
		p = repoProj.save(p);
		return new RedirectView("/projects");
	}
	

	@RequestMapping(value = "/findProject", method = RequestMethod.POST)
	public String findByNameDescription(Project e, BindingResult result, Model model) {

		model.addAttribute("blankProject", e);
		model.addAttribute("projects", repoProj.findByNameDescription(e.getName(), e.getDescription()));

		return "project/list";
	}
}
