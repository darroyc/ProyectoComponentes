package com.cenfotec.pm.web.calculator;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.cenfotec.pm.domain.ActivityWeekData;
import com.cenfotec.pm.domain.ProjectWeekData;
import com.cenfotec.pm.web.BaseController;

@Controller
public class Statistics extends BaseController {
	ProjectWeekData temp;
	
	public void updateCumulativeValues(List<ActivityWeekData> weekDataList, String type) {
		if(!weekDataList.isEmpty()) {		
			Long projectId = weekDataList.get(0).getActivity().getProjectId();		
			for(ActivityWeekData weekData : weekDataList) {
					temp = projectWeekDataRepository.findByIdentifierTypeWeek(projectId,
							type,
							weekData.getWeekDataId().getWeek());
					temp.setValue(temp.getValue() + weekData.getValue());
					projectWeekDataRepository.save(temp);
				
			}
		}
	}

	public void updateMixedStatistics(List<ProjectWeekData> cpvdata, List<ProjectWeekData> cevdata, List<ProjectWeekData> cacdata) {
		Long projectId = cpvdata.get(0).getWeekDataId().getIdentifier();
		Long bac = cpvdata.get(0).getProject().getBudget();
		List<ProjectWeekData> tempList;
		List<ProjectWeekData> cacList;
		//Cost Variance (CV): EV – AC
		for(int i = 0; i<cevdata.size(); i++) {
				temp = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_TypeAndWeekDataId_Week(projectId,
						"CV",
						cevdata.get(i).getWeekDataId().getWeek());
				temp.setValue(cevdata.get(i).getValue() - cacdata.get(i).getValue());
				projectWeekDataRepository.save(temp);
		}
		//Schedule Variance (SV): EV – PV
		for(int i = 0; i<cevdata.size(); i++) {
				temp = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_TypeAndWeekDataId_Week(projectId,
						"SV",
						cevdata.get(i).getWeekDataId().getWeek());
				temp.setValue(cevdata.get(i).getValue() +- cpvdata.get(i).getValue());
				projectWeekDataRepository.save(temp);
		}
		//Cost Performance Index (CPI): EV / AC
		cacList = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(projectId, "CAC");
		for(int i = 0; i<cevdata.size(); i++) {
			if(cacList.get(i).getValue()!=0) {
				temp = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_TypeAndWeekDataId_Week(projectId,
						"CPI",
						cevdata.get(i).getWeekDataId().getWeek());
				temp.setValue(cevdata.get(i).getValue() / cacList.get(i).getValue());
				projectWeekDataRepository.save(temp);
			}
		}
		//Schedule Performance Index (SPI) EV / PV
		tempList = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(projectId, "CPV");
		for(int i = 0; i<cevdata.size(); i++) {
			if(tempList.get(i).getValue()!=0) {
				temp = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_TypeAndWeekDataId_Week(projectId,
						"SPI",
						cevdata.get(i).getWeekDataId().getWeek());
				temp.setValue(cevdata.get(i).getValue() / tempList.get(i).getValue());
				projectWeekDataRepository.save(temp);
			}
		}
		//Estimate at Completion (EAC): BAC / CPI
		tempList = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(projectId, "CPI");
		for(int i = 0; i<cevdata.size(); i++) {
			if(tempList.get(i).getValue()!=0) {
				temp = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_TypeAndWeekDataId_Week(projectId,
						"EAC",
						cevdata.get(i).getWeekDataId().getWeek());
				temp.setValue(bac / tempList.get(i).getValue());
				projectWeekDataRepository.save(temp);
			}
		}
		//Estimate at Completion (ETC): EAC - AC
		tempList = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_Type(projectId, "EAC");
		for(int i = 0; i<cevdata.size(); i++) {
				temp = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_TypeAndWeekDataId_Week(projectId,
						"ETC",
						cevdata.get(i).getWeekDataId().getWeek());
				temp.setValue(tempList.get(i).getValue() - cacList.get(i).getValue());
				projectWeekDataRepository.save(temp);
		}
		//Variance at Completion (VAC): BAC – EAC
		for(int i = 0; i<cevdata.size(); i++) {
				temp = projectWeekDataRepository.findByWeekDataId_IdentifierAndWeekDataId_TypeAndWeekDataId_Week(projectId,
						"VAC",
						cevdata.get(i).getWeekDataId().getWeek());
				temp.setValue(bac - tempList.get(i).getValue());
				projectWeekDataRepository.save(temp);
		}
		
	}
	
	public void updateActiviyBudgetedCost(List<ActivityWeekData> weekDataList) {
		
	}
}
