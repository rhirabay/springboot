package rhirabay.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import rhirabay.ApplicationTest;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleStepDef {
    private RestTemplate restTemplate = new RestTemplate();

    private String name;

//    @Given("name is {string}")
//    public void name_is(String name) throws Exception {
//        this.name = name;
//    }

    @Given("^name is (.*)$")
    public void name_is_ryo(String name) throws Exception {
        this.name = name;
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        // nothing to do
    }

    @Then("response is {string}")
    public void i_should_be_told(String expect) {
        var actual = restTemplate.getForObject("http://localhost:8080/hello?name={name}", String.class, name);
        assertThat(actual).isEqualTo(expect);
    }

    @Then("response is:")
    public void then2(String expect) {
        var actual = restTemplate.getForObject("http://localhost:8080/hello?name={name}", String.class, name);
        assertThat(actual).isEqualTo(expect);
    }
}
