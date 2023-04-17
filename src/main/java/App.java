import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args)  {

        Flyway flyway = Flyway
                .configure()
                .dataSource("jdbc:h2:./myBaseLaba11", null, null)
                .load();

        flyway.migrate();
    }
}
