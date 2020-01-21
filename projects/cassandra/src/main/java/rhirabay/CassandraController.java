package rhirabay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rhirabay.model.Sample;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CassandraController {
    private final CassandraTemplate template;

    // curl localhost:8080
    @GetMapping("")
    public List<Sample> all() {
        return template.select("SELECT * FROM t_sample", Sample.class);
    }

    // curl localhost:8080/key1
    @GetMapping("/{key}")
    public Sample getByKey(@PathVariable String key) {
        return template.selectOneById(key, Sample.class);
    }

    // curl localhost:8080/insert -d '{"key":"key1","value":"value1"}' -X PATCH -H 'Content-Type: application/json'
    @PatchMapping("/**")
    public Sample patch(@RequestBody Sample sample) {
        return template.insert(sample);
    }

    // curl localhost:8080/key1 -X DELETE
    @DeleteMapping("/{key}")
    public Boolean delete(@PathVariable String key) {
        return template.deleteById(key, Sample.class);
    }

}
