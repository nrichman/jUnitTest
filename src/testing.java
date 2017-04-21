
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class testing {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe");

    driver = new FirefoxDriver();
    baseUrl = "http://ec2-52-10-150-59.us-west-2.compute.amazonaws.com:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test //Registers a user
  public void registerTest() throws Exception {
    driver.get(baseUrl + "/TestProject/welcome.jsp");
    driver.findElement(By.linkText("Register")).click();
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("newRegisteredUser");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("newemail@unomaha.edu");
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys("new");
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys("user");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("newuserpassword");
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    Thread.sleep(5000);;
  }
  
  @Test //Logs the user in
  public void loginTest() throws Exception {
    driver.get(baseUrl + "/TestProject/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    Thread.sleep(5000);;
  }
  
  @Test //Creates a new thread
  public void newThreadTest() throws Exception {
    driver.get(baseUrl + "/TestProject/welcome.jsp?value=0");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("Forums")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("new");
    driver.findElement(By.name("description")).clear();
    driver.findElement(By.name("description")).sendKeys("thread");
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    Thread.sleep(5000);;
  }
  
  @Test //Logs the user out
  public void testLogout() throws Exception {
    driver.get(baseUrl + "/TestProject/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    Thread.sleep(5000);;
    driver.findElement(By.linkText("Logout")).click();
  }

  @Test //Creates a new comment in a thread
  public void testNewComment() throws Exception {
    driver.get(baseUrl + "/TestProject/Login.jsp");
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("My Page")).click();
    driver.findElement(By.linkText("Forums")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("newThread");
    driver.findElement(By.name("description")).clear();
    driver.findElement(By.name("description")).sendKeys("this is a new thread");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'newThread')])[3]")).click();
    driver.findElement(By.name("userPost")).clear();
    driver.findElement(By.name("userPost")).sendKeys("this is a comment");
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
    Thread.sleep(5000);;
  }

  @Test //Tests to see if a login can fail with the wrong password
  public void testLoginFail() throws Exception {
    driver.get(baseUrl + "/TestProject/welcome.jsp?value=0");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlul");
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    Thread.sleep(5000);;
  }
  
  @Test //Tests to see if a user can view threads on their profile
  public void testViewThreadFromProfile() throws Exception {
    driver.get(baseUrl + "/TestProject/Login.jsp");
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("My Page")).click();
    Thread.sleep(5000);;
    driver.findElement(By.linkText("newThread")).click();
    Thread.sleep(5000);;
  }
  
  @Test //Tests a comment on a thread created by another user
  public void testCommentOtherUserThread() throws Exception {
    driver.get(baseUrl + "/TestProject/Login.jsp");
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("Forums")).click();
    driver.findElement(By.linkText("Hey")).click();
    driver.findElement(By.name("userPost")).clear();
    driver.findElement(By.name("userPost")).sendKeys("hello");
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
    Thread.sleep(5000);;
  }
  

  @Test //Comments on a thread that already has a comment
  public void testCommentReply() throws Exception {
    driver.get(baseUrl + "/TestProject/Login.jsp");
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("chrisssyy");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("pass");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("My Page")).click();
    driver.findElement(By.linkText("Hey")).click();
    driver.findElement(By.name("userPost")).clear();
    driver.findElement(By.name("userPost")).sendKeys("do you have any marbles");
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
    Thread.sleep(5000);;
  }
  
  @Test
  public void testPrivateMessage() throws Exception {
    driver.get(baseUrl + "/TestProject/welcome.jsp?value=0");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("Forums")).click();
    driver.findElement(By.linkText("chrisssyy")).click();
    driver.findElement(By.cssSelector("button")).click();
    driver.findElement(By.name("message")).clear();
    driver.findElement(By.name("message")).sendKeys("private message test");
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    Thread.sleep(5000);;
  }
  
  @Test //Tests two users having a conversation
  public void testMessageReply() throws Exception {
    driver.get(baseUrl + "/TestProject/welcome.jsp?value=0");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("Forums")).click();
    driver.findElement(By.linkText("chrisssyy")).click();
    driver.findElement(By.cssSelector("button")).click();
    driver.findElement(By.name("message")).clear();
    driver.findElement(By.name("message")).sendKeys("hello! I have marbles");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("chrisssyy");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("pass");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("Forums")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'nrichman')])[2]")).click();
    driver.findElement(By.cssSelector("button")).click();
    driver.findElement(By.name("message")).clear();
    driver.findElement(By.name("message")).sendKeys("how many you got");
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    Thread.sleep(5000);;
  }

  @Test //Creates and deletes a thread
  public void testDeleteThread() throws Exception {
    driver.get(baseUrl + "/TestProject/welcome.jsp?value=0");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("Forums")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("delete");
    driver.findElement(By.name("description")).clear();
    driver.findElement(By.name("description")).sendKeys("this");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("delete")).click();
    Thread.sleep(5000);;
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    Thread.sleep(5000);;
  }
  
  @Test //User must be logged in to view welcome page
  public void testMustBeLoggedIn() throws Exception {
    driver.get(baseUrl + "/TestProject/");
    Thread.sleep(5000);;
    driver.findElement(By.linkText("Welcome Page")).click();
    Thread.sleep(5000);;
  }
  
  @Test //Shows that only the creator of a thread can delete it
  public void testOnlyCreatorCanDelete() throws Exception {
    driver.get(baseUrl + "/TestProject/Login.jsp");
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("Forums")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("dont delete this :(");
    driver.findElement(By.name("description")).clear();
    driver.findElement(By.name("description")).sendKeys("you cant!");
    new Select(driver.findElement(By.name("tag"))).selectByVisibleText("Trade");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("dont delete this :(")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("chrisssyy");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("pass");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    driver.findElement(By.linkText("Forums")).click();
    Thread.sleep(5000);;
    driver.findElement(By.linkText("dont delete this :(")).click();
    Thread.sleep(5000);;
  }
  
  @Test //Users cannot message themselves
  public void testCantMessageSelf() throws Exception {
    driver.get(baseUrl + "/TestProject/Login.jsp");
    driver.findElement(By.name("loginName")).clear();
    driver.findElement(By.name("loginName")).sendKeys("nrichman");
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("passwordlol");
    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
    Thread.sleep(5000);;
    driver.findElement(By.linkText("My Page")).click();
    Thread.sleep(5000);;
  }


  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
