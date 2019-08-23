package com.cenfotec.pm.web;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
import com.cenfotec.pm.domain.ProjectWeekData;
import com.cenfotec.pm.web.calculator.Statistics;
import com.cenfotec.pm.domain.ActivityWeekData;

@Controller
public class ProjectController extends BaseController{
	@Autowired
	private Statistics statistics;

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String listProjects(Model model) {
		model.addAttribute("blankProject", new Project());
		model.addAttribute("projects", projectRepository.findAll());
		return "project/list";
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.GET)
	public String project(Model model) {
		model.addAttribute("newProject", new Project());

		return "project/form";
	}
	
	@RequestMapping(value = "/saveProj", method = RequestMethod.POST)
	public RedirectView saveProj(Project newProj, BindingResult result, Model model) {
		setWeeks(newProj);		
		newProj = projectRepository.save(newProj);
		for(Integer week : getWeeks(newProj)) {	
			projectWeekDataRepository.save(new ProjectWeekData(week, newProj.getId(), "CPV"));
			projectWeekDataRepository.save(new ProjectWeekData(week, newProj.getId(), "CEV"));
			projectWeekDataRepository.save(new ProjectWeekData(week, newProj.getId(), "CAC"));
			projectWeekDataRepository.save(new ProjectWeekData(week, newProj.getId(), "SV"));
			projectWeekDataRepository.save(new ProjectWeekData(week, newProj.getId(), "CPI"));
			projectWeekDataRepository.save(new ProjectWeekData(week, newProj.getId(), "SPI"));
			projectWeekDataRepository.save(new ProjectWeekData(week, newProj.getId(), "EAC"));
			projectWeekDataRepository.save(new ProjectWeekData(week, newProj.getId(), "ETC"));
			projectWeekDataRepository.save(new ProjectWeekData(week, newProj.getId(), "VAC"));
		}
		return new RedirectView("/projects");
	}
	
	
	@RequestMapping(value = "/project/delete/{id}", method = RequestMethod.GET)
	public RedirectView deleteEmployee(Model model, @PathVariable Long id) {
		Project e = null;
		Optional<Project> found = projectRepository.findById(id);
		if (found.isPresent()) {
			e = found.get();
		}

		e.setState(0);
		projectRepository.save(e);
		return new RedirectView("/projects");
	}
	
	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public String projDetail(Model model, @PathVariable Long id) {
		Project p = null;
		Optional<Project> found = projectRepository.findById(id);
		if (found.isPresent()) {
			p = found.get();
			p.setActivities(activityRepository.findByProjectId(p.getId()));
		}
		
		List<ActivityWeekData> pvdata = activityWeekDataRepository.findByActivity_ProjectIdAndWeekDataId_Type(id, "PV");
		List<ActivityWeekData> evdata = activityWeekDataRepository.findByActivity_ProjectIdAndWeekDataId_Type(id, "AC");
		List<ActivityWeekData> acdata = activityWeekDataRepository.findByActivity_ProjectIdAndWeekDataId_Type(id, "EV");
		
		statistics.updateCumulativeValues(pvdata, "CPV");
		statistics.updateCumulativeValues(acdata, "CAC");
		statistics.updateCumulativeValues(evdata, "CEV");
		
		List<ProjectWeekData> cpvdata = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(id, "CPV");
		List<ProjectWeekData> cevdata = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(id, "CAC");
		List<ProjectWeekData> cacdata = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(id, "CEV");
		
		statistics.updateMixedStatistics(cpvdata, cevdata, cacdata);
		
		model.addAttribute("p", p);
		model.addAttribute("blankActivity", new Activity());
		model.addAttribute("activities", activityRepository.findByProjectId(id));
		model.addAttribute("pvdata", pvdata);
		model.addAttribute("evdata", evdata);
		model.addAttribute("acdata", acdata);
		model.addAttribute("cpvdata", cpvdata);
		model.addAttribute("cacdata", cevdata);
		model.addAttribute("cevdata", cacdata);
		model.addAttribute("cvdata", projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(id, "CV"));
		model.addAttribute("svdata", projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(id, "SV"));
		model.addAttribute("cpidata", projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(id, "CPI"));
		model.addAttribute("spidata", projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(id, "SPI"));
		model.addAttribute("eacdata", projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(id, "EAC"));
		model.addAttribute("etcdata", projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(id, "ETC"));
		model.addAttribute("vacdata", projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(id, "VAC"));
		List<Integer> weeks =  getWeeks(p);
		model.addAttribute("weeks",weeks);
		model.addAttribute("weeksq", weeks.size());
		return "project/detail";
	}
	
	@RequestMapping(value = "/editProject/{id}", method = RequestMethod.GET)
	public String projUpdate(Model model, @PathVariable Long id) {
		Project p = null;
		Optional<Project> found = projectRepository.findById(id);
		if (found.isPresent()) {
			p = found.get();
			p.setActivities(activityRepository.findByProjectId(p.getId()));
		}
		model.addAttribute("p", p);
		return "project/edit";
		
	}
	
	@RequestMapping(value = "/updateProj/{id}", method = RequestMethod.POST)
	public RedirectView updateProj(@PathVariable Long id, Project p, BindingResult result, Model model) {
		p.setId(id);
		p = projectRepository.save(p);
		return new RedirectView("/projects");
	}
	

	@RequestMapping(value = "/findProject", method = RequestMethod.POST)
	public String findByNameDescription(Project e, BindingResult result, Model model) {

		model.addAttribute("blankProject", e);
		model.addAttribute("projects", projectRepository.findByNameDescription(e.getName(), e.getDescription()));

		return "project/list";
	}
	
	//Week Data
	
	@RequestMapping(value = "/addWeekData/{projectId}", method = RequestMethod.GET)
	public String newWeekData(Model model, @PathVariable Long projectId) {

		model.addAttribute("newWeekData", new ActivityWeekData());
		model.addAttribute("activities", activityRepository.findAll());

		
		Project project = new Project();
		Optional<Project> foundP = projectRepository.findById(projectId);
		if (foundP.isPresent()) {
			project = foundP.get();
		}
		model.addAttribute("weeks", getWeeks(project));
		model.addAttribute("p", projectId);

		return "project/weekdata/form";
	}

	@RequestMapping(value = "/saveWeekData", method = RequestMethod.POST)
	public RedirectView saveWeekData(ActivityWeekData newWeekData, BindingResult result, Model model) {

		Activity activity = new Activity();
		Optional<Activity> found = activityRepository.findById(newWeekData.getWeekDataId().getIdentifier());
		if (found.isPresent()) {
			activity = found.get();
		}
		
		newWeekData = activityWeekDataRepository.save(newWeekData);
		
		Project project = new Project();
		Optional<Project> foundP = projectRepository.findById(activity.getProjectId());
		if (foundP.isPresent()) {
			project = foundP.get();
		}
		
		model.addAttribute("p", project);
		
		model.addAttribute("a", activity);

		model.addAttribute("blankActivity", new Activity());
		return new RedirectView("addWeekData/" + project.getId());
	}
		
	private void setWeeks(Project project) {
		long diffInMillies = Math.abs(project.getEndDate().getTime() - project.getStartDate().getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		project.setWeeks(Math.round(diff / 7)); 
	}
}
