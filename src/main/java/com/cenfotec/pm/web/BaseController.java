package com.cenfotec.pm.web;

import java.util.ArrayList;
import java.util.List;
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
import com.cenfotec.pm.domain.ActivityWeekData;
import com.cenfotec.pm.repository.ActivityRepository;
import com.cenfotec.pm.repository.ProjectRepository;
import com.cenfotec.pm.repository.ProjectWeekDataRepository;
import com.cenfotec.pm.repository.ActivityWeekDataRepository;

@Controller
public class BaseController {

	@Autowired
	protected ActivityRepository activityRepository;
	@Autowired
	protected ProjectRepository projectRepository;
	@Autowired
	protected ActivityWeekDataRepository activityWeekDataRepository;
	@Autowired
	protected ProjectWeekDataRepository projectWeekDataRepository;

	
	protected List<Integer> getWeeks(Project project){
		List<Integer> weeks = new ArrayList<>();
		for(int i = 1; i<=project.getWeeks(); i++) {
			weeks.add(i);
		}
		return weeks;
	}
}
