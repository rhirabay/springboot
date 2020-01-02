package rhirabay.client;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.DatabaseClient;
import rhirabay.model.Person;

import java.util.UUID;

@Slf4j
public class SampleClient {
    public static void main (String[] args) {
        ConnectionFactory connectionFactory
                = ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");

        DatabaseClient client = DatabaseClient.create(connectionFactory);

        client.execute("CREATE TABLE person"
                + "(id VARCHAR(255) PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "age INT)")
                .fetch()
                .rowsUpdated()
                .block();

        client.execute("select * from INFORMATION_SCHEMA.tables where TABLE_NAME = 'PERSON'")
                .fetch()
                .all()
                .subscribe(data -> log.info("data: {}", data));

        client.insert()
                .into(Person.class)
                .using(Person.of(UUID.randomUUID().toString(), "Ryo", 26))
                .then()
                .block();

        client.select()
                .from(Person.class)
                .as(Person.class)
                .all()
                .subscribe(data -> log.info("Person data: {}", data));
    }
}
