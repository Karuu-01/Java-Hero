import static spark.Spark.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import models.Squad;
import models.Hero;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //GET INFO FROM ABOUT US
        get("/about", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());

        //GET INFO OF HEROES THROUGH A FORM
        get("/hero/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "heroForm.hbs");
        }, new HandlebarsTemplateEngine());

        //POST THE INFO ABOUT THE HEROES GENERATED THROUGH THE FORM
        post("/hero", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("heroName");
            int age = Integer.parseInt(request.queryParams("heroAge"));
            String power = request.queryParams("heroPower");
            String weakness = request.queryParams("heroWeakness");
            Hero hero = new Hero(name, age, power, weakness);
            model.put("hero", hero);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //CREATE A NEW HERO
        get("/hero", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Hero> heroes = Hero.getAllInstances();
            model.put("hero", heroes);
            return new ModelAndView(model, "hero.hbs");
        }, new HandlebarsTemplateEngine());

        //DELETE A HERO
        get("/hero/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Hero.clearAllHero();
            model.put("hero", Hero.getAllInstances());
            return new ModelAndView(model, "hero.hbs");
        }, new HandlebarsTemplateEngine());

        //CREATE A NEW SQUAD
        get("/squad", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("squad", Squad.getSquadInstances());
            return new ModelAndView(model, "squad.hbs");
        }, new HandlebarsTemplateEngine());

        //GET A NEW SQUAD
        get("/squad/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> hero = Hero.getAllInstances();
            ArrayList<Hero> heroList = new ArrayList<>();
            for (int i=0;i<hero.size();i++){
                if (hero.get(i).isSquadMember()){
                    heroList.add(hero.get(i));
                }
            }
            model.put("hero",Hero.getAllInstances());
            return new ModelAndView(model,"formSquad.hbs");
        }, new HandlebarsTemplateEngine());

        //POST THE INFORMATION GOTTEN FROM THE SQUAD FORM
        post("/squad",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("squadName");
            String purpose = request.queryParams("squadPurpose");
            int totalSize = Integer.parseInt(request.queryParams("squadSize"));
            ArrayList<Hero> hero = new ArrayList<>();
            if(request.queryParamsValues("squadHero")!=null){
                String[] chosenHero = request.queryParamsValues("squadHero");
                for (int i=1; i <= chosenHero.length; i++ ) {
                    Hero addHero = Hero.findById(i);
                    if(hero.size()< totalSize) {
                        addHero.updateHero(true);
                        hero.add(addHero);
                    }
                }
            }
            Squad newSquad = new Squad(name, purpose, totalSize, hero);
            model.put("hero", Hero.getAllInstances());
            model.put("squad", newSquad.getHero());
            return new ModelAndView(model, "formSquad.hbs");
        }, new HandlebarsTemplateEngine());

        //DELETE A SQUAD
        get("/squad/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad.clearSquad();
            ArrayList<Hero> hero = Hero.getAllInstances();
            for (int i = 0; i < hero.size(); i++) {
                hero.get(i).updateHero(false);
            }
            model.put("squad", Squad.getSquadInstances());
            return new ModelAndView(model, "squad.hbs");
        }, new HandlebarsTemplateEngine());

        //GET SQUADS BY ID
        get("/squad/id", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int idToFindSquad = Integer.parseInt(request.params(":id"));
            Squad searchSquad = Squad.findById(idToFindSquad);
            model.put("squad", searchSquad);
            ArrayList<Squad> squad = Squad.getSquadInstances();
            return new ModelAndView(model, "squad.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
