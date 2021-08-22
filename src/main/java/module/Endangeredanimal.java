package module;

import org.sql2o.Connection;

import java.util.List;

public class Endangeredanimal extends Wildlife implements DatabaseManager{
    public static final String ANIMAL_TYPE = "danger";

    public  Endangeredanimal(String name, String health, String age){
        this.name = name;
        this.health = health;
        this.age = age;
        this.type = ANIMAL_TYPE;

        if (name.isEmpty() || health.isEmpty() || age.isEmpty()){
            throw new IllegalArgumentException("Fields are empty");
        }
    }

    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Endangeredanimal)) {
            return false;
        } else {
            Endangeredanimal newAnimal = (Endangeredanimal) otherAnimal;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getHealth().equals(newAnimal.getHealth()) &&
                    this.getAge().equals(newAnimal.getAge());
        }
    }

    public static List<Endangeredanimal> all() {
        String sql = "SELECT * FROM animals WHERE type='danger';";
        try(Connection connection = DB.sql2o.open()) {
            return connection.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Endangeredanimal.class);
        }
    }

    public static Endangeredanimal find(int id) {
        try (Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Endangeredanimal animal = connection.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Endangeredanimal.class);
            return animal;
        }
    }
}
