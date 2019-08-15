package com.cenfotec.pm.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.cenfotec.pm.domain.Activity;
import com.cenfotec.pm.domain.Project;
import com.cenfotec.pm.repository.ActivityRepository;
import com.cenfotec.pm.repository.ProjectRepository;

@Controller
public class ActivityController {

	@Autowired
	ActivityRepository repoAct;
	@Autowired
	ProjectRepository repoProj;

	
	@RequestMapping(value = "/detailActivity", method = RequestMethod.GET)
	public String projDetail(Model model, @RequestParam("idAct") Long idAct, @RequestParam("idProj") Long idProj) {
		Activity a = null;
		Optional<Activity> found = repoAct.findById(idAct);
		if (found.isPresent()) {
			a = found.get();
		}
		
		Project project = new Project();
		Optional<Project> foundP = repoProj.findById(idProj);
		if (foundP.isPresent()) {
			project = foundP.get();
		}
		
		
		model.addAttribute("p", project);
		
		model.addAttribute("a", a);
		return "activity/detail";
	}
	
	@RequestMapping(value = "/addActivity/{projectId}", method = RequestMethod.GET)
	public String employee(Model model, @PathVariable Long projectId) {

		model.addAttribute("newActivity", new Activity());
		model.addAttribute("projectId", projectId);

		return "activity/form";
	}

	@RequestMapping(value = "/saveAct/{projectId}", method = RequestMethod.POST)
	public RedirectView saveActivity(Activity newAct, BindingResult result, Model model, @PathVariable Long projectId) {

		Project project = new Project();
		Optional<Project> found = repoProj.findById(projectId);
		if (found.isPresent()) {
			project = found.get();
		}
		newAct.setProjectId(project.getId());
		newAct = repoAct.save(newAct);

		return new RedirectView("/project/" + projectId);
	}

	@RequestMapping(value = "/activity/delete/{actId}", method = RequestMethod.GET)
	public RedirectView deleteActivity(Model model, @PathVariable Long actId) {
		Activity e = null;
		Long projId;
		Optional<Activity> found = repoAct.findById(actId);
		if (found.isPresent()) {
			e = found.get();
		}

		projId = e.getProjectId();
		e.setState(0);
		repoAct.save(e);
		return new RedirectView("/project/" + projId);
	}
	
	
	@RequestMapping(value = "/editActivity", method = RequestMethod.GET)
	public String actUpdate(Model model, @RequestParam("idAct") Long idAct, @RequestParam("idProj") Long idProj) {
		Activity a = null;
		Optional<Activity> found = repoAct.findById(idAct);
		if (found.isPresent()) {
			a = found.get();
		}
		Project project = new Project();
		Optional<Project> foundP = repoProj.findById(idProj);
		if (foundP.isPresent()) {
			project = foundP.get();
		}
		
		
		model.addAttribute("p", project);
		
		model.addAttribute("a", a);
		return "activity/edit";
		
	}
	
	@RequestMapping(value = "/updateAct", method = RequestMethod.POST)
	public RedirectView updateAct(@RequestParam("idAct") Long idAct, @RequestParam("idProj") Long idProj, Activity a, BindingResult result, Model model) {
		a.setId(idAct);
		a.setProjectId(idProj);
		a = repoAct.save(a);
		return new RedirectView("/project/" + idProj);
	}

	@RequestMapping(value = "/findActivity/{projectId}", method = RequestMethod.POST)
	public String findByNameDescription(Activity e, BindingResult result, Model model, @PathVariable Long projectId) {
		Project p = null;
		Optional<Project> found = repoProj.findById(projectId);
		if (found.isPresent()) {
			p = found.get();
			p.setActivities(repoAct.findByIdNameDescription(e.getProjectId(), e.getName(), e.getDescription()));
		}
		model.addAttribute("p", p);
		model.addAttribute("blankActivity", e);

		return "/project/detail";
	}
}
