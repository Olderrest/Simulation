package com.stasuma.controller;

import com.stasuma.objects.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("restart")
public class SimulationController {

    private Network network;

    @Autowired
    @Qualifier("simulationNetwork")
    public void setNetwork(Network network) {
        this.network = network;
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void startSimulation(Model model) {
        network.start();
        model.addAttribute("restart", "true");
    }

    @RequestMapping(value = "/restart", method = RequestMethod.GET)
    public void restartSimulation() {
        network.restart();
    }
}
