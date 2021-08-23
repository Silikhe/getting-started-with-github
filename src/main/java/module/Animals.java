package module;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class Animals extends Wildlife implements DatabaseManager{
    public static final String ANIMAL_TYPE = "animal";
    private int danger;

    public Animals(String name){
        this.name = name;
        this.type = ANIMAL_TYPE;
        if (name.isEmpty()){
            throw new IllegalArgumentException("Please enter the animal name.");
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (id, name, type, health, age) VALUES (:id, :name, :type, :health, :age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("id", this.id)
                    .addParameter("name", this.name)
                    .addParameter("type",this.type)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animals)) {
            return false;
        } else {
            Animals newAnimal = (Animals) otherAnimal;
            return this.getName().equals(newAnimal.getName());
        }
    }

    public static List<Animals> all() {
        String sql = "SELECT * FROM animals WHERE type='animal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
        }
    }

    public static Animals find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animals animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animals.class);
            return animal;
        }
    }

    public static List<Object> getAnimals() {
        List<Object> allAnimals = new ArrayList<Object>();

        try(Connection con = DB.sql2o.open()) {
            String sqlFire = "SELECT * FROM animals WHERE id=:id AND type='animal';";
            List<Animals> animals = con.createQuery(sqlFire)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
            allAnimals.addAll(animals);

            String sqlWater = "SELECT * FROM animals WHERE id=:id AND type='animal';";
            List<Endangeredanimal> endangeredAnimals = con.createQuery(sqlWater)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Endangeredanimal.class);
            allAnimals.addAll(endangeredAnimals);
        }

        return allAnimals;
    }

}
