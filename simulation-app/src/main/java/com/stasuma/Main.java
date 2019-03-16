package com.stasuma;


import com.stasuma.objects.Network;
import com.stasuma.util.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {

        Network network = new Network();
        if (args[1].equals("true")) {
            network.start();
        } else {
            network.restart();
        }
    }
}
