//import module.*;
import module.AllSightings;
import module.Animals;
import module.Endangeredanimal;
import module.Sightings;
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

        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String animalName = request.queryParams("animal");
            String rangerName = request.queryParams("ranger");
            String location = request.queryParams("location");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String type = request.queryParams("type");

               Animals animal = new Animals(animalName);
               animal.save();
               Sightings sights = new Sightings(animal.getId(),location,rangerName);
               sights.save();

            if(type.equals("animal")){
                Animals animals = new Animals(animalName);
                animals.save();
                Sightings sight = new Sightings(animal.getId(),location,rangerName);
                sight.save();
            } else if(type.equals("endangered")){
                Endangeredanimal endangered = new Endangeredanimal(animalName,health,age);
                endangered.save();
                Sightings anotherSighting = new Sightings(endangered.getId(), location, rangerName);
                anotherSighting.save();
            }

            List<AllSightings> allSightings = AllSightings.getAll();
            List<Endangeredanimal> animals= Endangeredanimal.all();
            model.put("sightings", allSightings);
            model.put("animals", animals);

            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

    }

}