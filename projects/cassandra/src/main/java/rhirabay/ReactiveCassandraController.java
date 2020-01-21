package rhirabay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rhirabay.model.Sample;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/reactive")
public class ReactiveCassandraController {
    private final ReactiveCassandraTemplate template;

    // curl localhost:8080/reactive
    @GetMapping("")
    public Publisher<Sample> all() {
        return template.select("SELECT * FROM t_sample", Sample.class);
    }

    // curl localhost:8080/reactive/key1
    @GetMapping("/{key}")
    public Publisher<Sample> getByKey(@PathVariable String key) {
        return template.selectOneById(key, Sample.class);
    }

    // curl localhost:8080/reactive/insert -d '{"key":"key1","value":"value1"}' -X PATCH -H 'Content-Type: application/json'
    @PatchMapping("/**")
    public Publisher<Sample> patch(@RequestBody Sample sample) {
        return template.insert(sample);
    }

    // curl localhost:8080/reactive/key1 -X DELETE
    @DeleteMapping("/{key}")
    public Publisher<Boolean> delete(@PathVariable String key) {
        return template.deleteById(key, Sample.class);
    }

}
