package com.stasuma.controller;


import com.stasuma.model.*;
import com.stasuma.service.RouteWebService;
import com.stasuma.service.SimulationWebInfoService;

import com.stasuma.service.SimulationWebWorkTimeService;
import com.stasuma.util.SimulationInfoQueryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(names = {"infos", "pagedList", "simulationWorkTime"})
public class SimulationInfoController {

    private SimulationWebInfoService simulationWebInfoService;
    private RouteWebService routeWebService;
    private SimulationWebWorkTimeService simulationWebWorkTimeService;
    private SimulationInfoQueryValidator simulationInfoQueryValidator;

    @Autowired
    public void setSimulationWebInfoService(SimulationWebInfoService simulationWebInfoService) {
        this.simulationWebInfoService = simulationWebInfoService;
    }

    @Autowired
    public void setRouteWebService(RouteWebService routeWebService) {
        this.routeWebService = routeWebService;
    }

    @Autowired
    @Qualifier("simulationWorkTimeService")
    public void setSimulationWebWorkTimeService(SimulationWebWorkTimeService simulationWebWorkTimeService) {
        this.simulationWebWorkTimeService = simulationWebWorkTimeService;
    }

    @Autowired
    public void setSimulationInfoQueryValidator(SimulationInfoQueryValidator simulationInfoQueryValidator) {
        this.simulationInfoQueryValidator = simulationInfoQueryValidator;
    }


    @RequestMapping(value = "/simulation_info", method = RequestMethod.GET)
    public ModelAndView showBusInfoPage(@SessionAttribute("user") User user) {
        if (user.getLogin() == null) {
            return new ModelAndView("redirect:/go_login", "user", new User());
        }
        ModelAndView modelAndView = new ModelAndView("simulation_info");
        SimulationInfoQuery simulationInfoQuery = new SimulationInfoQuery();
        SimulationWorkTimeForSelect infoForSelect = processError();
        SimulationWorkTimeWeb simulationWorkTime = simulationWebWorkTimeService.getSimulationWorkTime();
        modelAndView.addObject("simulationInfoQuery", simulationInfoQuery);
        modelAndView.addObject("infoForSelect", infoForSelect);
        modelAndView.addObject("simulationWorkTime", simulationWorkTime);
        return modelAndView;
    }

    @RequestMapping(value = "/simulation_info/{action}")
    public ModelAndView getPage(@PathVariable("action") String action, @SessionAttribute("pagedList") PagedListHolder infoList) {
        PagedListHolder pagedList = (PagedListHolder) infoList;
        if (action.equals("next")) {
            pagedList.nextPage();
        } else if (action.equals("prev")) {
            pagedList.previousPage();
        } else {
            int pageNum = Integer.parseInt(action);
            pagedList.setPage(pageNum - 1);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/simulation_info");
        modelAndView.addObject("infos", pagedList.getPageList());
        return modelAndView;
    }

    @RequestMapping(value = "/simulation_info", method = RequestMethod.POST)
    public String getSpecificBusInfo(@ModelAttribute("simulationInfoQuery") SimulationInfoQuery simulationInfoQuery, BindingResult result, Model model) {
        simulationInfoQueryValidator.validate(simulationInfoQuery, result);
        if (result.hasErrors()) {
            SimulationWorkTimeForSelect infoForSelect = processError();
            model.addAttribute("simulationInfoQuery", simulationInfoQuery);
            model.addAttribute("infoForSelect", infoForSelect);
            return "simulation_info";
        } else {
            List<SimulationInfo> infos = simulationWebInfoService.getSpecificBusInfos(simulationInfoQuery);
            PagedListHolder pagedList = new PagedListHolder(infos);
            pagedList.setPageSize(15);
            model.addAttribute("infos", pagedList.getPageList());
            model.addAttribute("pagedList", pagedList);
            return "redirect:/simulation_info";
        }
    }

    private SimulationWorkTimeForSelect processError() {
        List<Route> routes = routeWebService.findAll();
        List<Bus> buses = getAllBuses(routes);
        List<Stop> stops = getAllStops(routes);
        return simulationWebWorkTimeService.getSimulationWorkTimeForSelect(routes, buses, stops);
    }


    private List<Bus> getAllBuses(List<Route> routes) {
        List<Bus> buses = new ArrayList<>();
        for (Route route : routes) {
            buses.addAll(route.getBuses());
        }
        return buses;
    }

    private List<Stop> getAllStops(List<Route> routes) {
        List<Stop> stops = new ArrayList<>();
        for (Route route : routes) {
            stops.addAll(route.getStops());
        }
        return stops;
    }
}
