package io.Corona.coronavirus_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.Corona.coronavirus_tracker.models.LocationsStats;
import io.Corona.coronavirus_tracker.services.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronavirusdataservice;

	@GetMapping("/")
	public String home(Model model) {
		List<LocationsStats> allStats = coronavirusdataservice.getStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotal_Cases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
	}
}
