#!/bin/bash
cd "$(dirname "$0")"
java -cp /Users/p/IdeaProjects/Simulation/SimulationApp/target/simulation.app-1.0-SNAPSHOT.jar:/Users/p/.m2/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar:/Users/p/.m2/repository/net/sf/opencsv/opencsv/2.3/opencsv-2.3.jar:/Users/p/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar com.stasuma.Main /Users/p/Desktop/appFiles false