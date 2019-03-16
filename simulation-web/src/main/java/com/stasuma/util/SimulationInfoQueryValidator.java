package com.stasuma.util;

import com.stasuma.model.SimulationInfoQuery;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SimulationInfoQueryValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object query, Errors errors) {
        SimulationInfoQuery simulationInfoQuery = (SimulationInfoQuery) query;
        if ((simulationInfoQuery.getYearFrom() == -1) && (simulationInfoQuery.getYearUntil() == -1)) {
            errors.rejectValue("yearFrom", "simulationInfoQueryForm.year", "Choose at least one value for year");
        }
        if ((simulationInfoQuery.getMonthFrom() == -1) && (simulationInfoQuery.getMonthUntil() == -1)) {
            errors.rejectValue("monthFrom", "simulationInfoQueryForm.month", "Choose at least one value for month");
        }
        if ((simulationInfoQuery.getDayFrom() == -1) && (simulationInfoQuery.getDayUntil() == -1)) {
            errors.rejectValue("dayFrom", "simulationInfoQueryForm.day", "Choose at least one value for day");
        }
        if ((simulationInfoQuery.getHourFrom() == -1) && (simulationInfoQuery.getHourUntil() == -1)) {
            errors.rejectValue("hourFrom", "simulationInfoQueryForm.hour", "Choose at least one value for hour");
        }
        if ((simulationInfoQuery.getBusIdFrom() == 0) && (simulationInfoQuery.getBusIdUntil() == 0)) {
            errors.rejectValue("busIdFrom", "simulationInfoQueryForm.bus", "Choose at least one value for bus");
        }
        if ((simulationInfoQuery.getRouteIdFrom() == 0) && (simulationInfoQuery.getRouteIdUntil() == 0)) {
            errors.rejectValue("routeIdFrom", "simulationInfoQueryForm.route", "Choose at least one value for route");
        }
        if ((simulationInfoQuery.getStopIdFrom() == 0) && (simulationInfoQuery.getStopIdUntil() == 0)) {
            errors.rejectValue("stopIdFrom", "simulationInfoQueryForm.stop", "Choose at least one value for stop");
        }
    }
}
