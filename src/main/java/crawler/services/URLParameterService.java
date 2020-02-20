package crawler.services;

import crawler.core.usecase.WebModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

public class URLParameterService {
    public final static String URL = "https://www.ss.lv/ru/transport/cars/";

    static WebModel webModel;

    public static WebModel buildURLFromArguments(ApplicationArguments args) {
        String url = URL;
        webModel = new WebModel();

        if (args.containsOption("make")) {
            final String makeFirst = args.getOptionValues("make").get(0);
            url = url + makeFirst + "/";
            webModel.setUrl(url);
            webModel.setUrls(args.getOptionValues("make").stream()
                    .map(make -> URL + make + "/")
                    .collect(Collectors.toSet()));
        }

        return webModel;
    }

    public static WebModel getWebModel() {
        return webModel;
    }
}

