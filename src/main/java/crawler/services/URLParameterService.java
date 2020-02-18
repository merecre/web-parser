package crawler.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class URLParameterService {
    public final static String URL = "https://www.ss.lv/ru/transport/cars/";

    static String url;

    static  String make;

    static  String model;

    public URLParameterService(String url) {
        this.url = url;
    }

    public static String buildURLFromArguments(ApplicationArguments args) {
        String url = URL;

        if (args.containsOption("make")) {
            make = args.getOptionValues("make").get(0);
            url = url + make + "/";
        }

        if (args.containsOption("model")) {
            model = args.getOptionValues("model").get(0);
            //url = url + model+ "/";
        }

        return url;
    }

    public static String getModel() {
        return model;
    }

    public static boolean hasModel() {
        return model != null;
    }
}

