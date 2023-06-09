package com.ryan.CoronaVirusTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.ryan.CoronaVirusTracker.models.LocationStats;
import com.ryan.CoronaVirusTracker.services.CoronaVirusDataServices;

import org.springframework.ui.Model;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataServices coronaVirusDataServices;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allstats = coronaVirusDataServices.getAllstats();
        int totalReportedCases = allstats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats", allstats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "home";
    }
}
