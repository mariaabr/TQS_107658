import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;
import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class HelloWorldFireworkJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

        @Test
        void test(FirefoxDriver driver) {
            // Same test logic than other "hello world" tests

            String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
            driver.get(sutUrl);
            String title = driver.getTitle();
            log.debug("The title of {} is {}", sutUrl, title);

            // Verify
            assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
        }
}
