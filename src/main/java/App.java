//import module.*;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {

        staticFileLocation("/public");

        System.out.println("Hey Silas");

        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal-new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-new.hbs");
        },new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", AllSightings.getAll());
            model.put("animal", EndangeredAnimal.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

    }

}