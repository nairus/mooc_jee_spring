
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  public static void main(String argv[]) {
    System.out.println("Hello");
    
    Logger log = LoggerFactory.getLogger(App.class);
    log.error("This is an error");
  }
}
