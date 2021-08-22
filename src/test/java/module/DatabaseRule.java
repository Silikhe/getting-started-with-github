package module;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before(){
//        Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-3-211-37-117.compute-1.amazonaws.com:5432/daam6pmoaonkp6", "mjwdrkmdtubnyr", "94221b8081abd0bd2f3763c3fa63e9d0a65c2630118dfe1ad698f90492a0838d");
        DB.sql2o =new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa", "star2030");
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM animals *;";
            String deleteSightingsQuery = "DELETE FROM sightings *;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
            con.createQuery(deleteSightingsQuery).executeUpdate();
        }
    }
}