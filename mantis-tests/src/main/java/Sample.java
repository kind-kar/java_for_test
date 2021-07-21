import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample {

    public static void main(String[] args) {
        System.out.println(System.getenv("PATH"));
        WebDriver driver = new ChromeDriver();
        driver.quit();
    }
}
