package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Given("command new user is selected")
    public void commandNewUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) {
        createUser(username, password, password);
    }

    @Then("a new user is created")
    public void aNewUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @When("incorrect username {string} and valid password {string} are entered")
    public void incorrectUsernameAndValidPasswordAreEntered(String username, String password) {
        createUser(username, password, password);
    }

    @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedAndErrorIsReported(String string) {
        pageHasContent(string);
    }
    
    @When("valid username {string} and too short password {string} with matching confirmation are entered")
    public void validUsernameAndTooShortPasswordWithMatchingConfirmationAreEntered(String username, String password) {
        createUser(username, password, password);
    }
    
    @When("valid username {string} and valid password {string} and non-matching confirmation {string} are entered")
    public void validUsernameAndValidPasswordAndNonMatchingConfirmationAreEntered(String username, String password, String passworddConf) {
        createUser(username, password, passworddConf);
    }
    
    @Given("user with username {string} with password {string} is succesfully created")
    public void userWithUsernameWithPasswordIsSuccesfullyCreated(String username, String password) {
        commandNewUserIsSelected();
        createUser(username, password, password);
        aNewUserIsCreated();
    }
    
    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithUsernameAndPasswordIsTriedToBeCreated(String username, String password) {
        commandNewUserIsSelected();
        createUser(username, password, password);
        driver.get(baseUrl + "/user");
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
    }

//    @When("username {string} and password {string} are given")
//    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
//        logInWith(username, password);
//    }   
//    @Then("system will respond {string}")
//    public void systemWillRespond(String pageContent) throws Throwable {
//        assertTrue(driver.getPageSource().contains(pageContent));
//    }
    @When("nonexistent username {string} and incorrect password {string} are given")
    public void nonexistentUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }
    
    private void createUser(String username, String password, String passwordConf) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConf);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
