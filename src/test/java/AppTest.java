import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Parcel Deliveries");
  }

  @Test
  public void checkCost() {
      goTo("http://localhost:4567/");
      fill("#height").with("1");
      fill("#width").with("1");
      fill("#length").with("1");
      fill("#weight").with("10");
      fill("#distance").with("150");
      submit(".btn");
      assertThat(pageSource()).contains("Your parcel is: $7");
  }

}
