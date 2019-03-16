package com.stasuma.util;

import com.stasuma.exception.RouteTypeException;
import com.stasuma.objects.Route;
import com.stasuma.objects.RouteType;
import com.stasuma.objects.Stop;
import com.stasuma.objects.Bus;
import com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XmlSaxParser {
    private static final Logger logger = Logger.getLogger(XmlSaxParser.class);

    private static SAXParserFactory parserFactory = new SAXParserFactoryImpl();
    private static List<Route> routes = new ArrayList<>();
    private static Set<Bus> busSet = new HashSet<>();
    private static int years;
    private static int months;
    private static int days;
    private static Route route;
    private static List<Stop> stops = new ArrayList<>();

    public static List<Route> parse() {
        try {
            SAXParser parser = parserFactory.newSAXParser();
            XmlHandler handler = new XmlHandler();
            File xmlFile = new File(Constants.PATH_TO_APP_FOLDER + File.separator + Constants.XML_FILE_NAME);
            logger.debug("Parsing xml file.");
            parser.parse(xmlFile, handler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            logger.debug("Error during parse xml file.");
        }
        return routes;
    }

    public static int getYears() {
        return years;
    }

    public static int getMonths() {
        return months;
    }

    public static int getDays() {
        return days;
    }

    private static class XmlHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("years")) {
                years = Integer.parseInt(attributes.getValue("quantity"));
            }
            if (qName.equals("months")) {
                months = Integer.parseInt(attributes.getValue("quantity"));
            }
            if (qName.equals("days")) {
                days = Integer.parseInt(attributes.getValue("quantity"));
            }
            if (qName.equals("route")) {
                int interval = Integer.parseInt(attributes.getValue("interval"));
                route = new Route();
                route.setInterval(interval);
            }
            if (qName.equals("stop")) {
                int time = Integer.parseInt(attributes.getValue("time"));
                Stop stop = new Stop();
                stop.setTime(time);
                stops.add(stop);
            }
            if (qName.equals("bus")) {
                int size = Integer.parseInt(attributes.getValue("size"));
                busSet.add(new Bus(size));
            }
            if (qName.equals("routeType")) {
                String type = attributes.getValue("type");
                if (type.equalsIgnoreCase("straight")) {
                    route.setRouteType(RouteType.STRAIGHT);
                } else if (type.equalsIgnoreCase("circular")) {
                    route.setRouteType(RouteType.CIRCULAR);
                } else {
                    try {
                        throw new RouteTypeException("Wrong route type");
                    } catch (RouteTypeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("stops")) {
                route.setStops(stops);
                stops = new ArrayList<>();
            }
            if (qName.equals("buses")) {
                route.setBuses(busSet);
                busSet = new HashSet<>();
            }
            if (qName.equals("route")) {
                routes.add(route);
            }
        }
    }
}
