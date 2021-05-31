package mainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainPage {

    private WebDriver driver;
    private DBWorker worker = new DBWorker();
    int a = 0; // Начальное значение диапазона - "от"
    int b = 99; // Конечное значение диапазона - "до"

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    By sigInButton = By.xpath("//a[@aria-label='Вход или регистрация']");
    By lcButton = By.xpath("//a[@aria-label='Личный кабинет']");
    By login = By.id("authLogin");
    By getPassword = By.xpath("//button/span[text()='Получить код']");
    By authEmail = By.id("authEmail");
    By authName = By.id("authFullName");
    By consentButton = By.xpath("//label[@for='authPersonalDataAgreement']");
    By registerButton = By.xpath("//span[text()='Зарегистрироваться']");
    By authPassword = By.id("authCode");
    By authEmailPassword = By.xpath("//input[@id='authCode']");
    By authPhone = By.id("authPhone");
    By exitButton = By.xpath("//span[text()='Выйти']");
    By phoneFromSite = By.xpath("//li[3]/div[@class='free-num__numbers-text']");
    By phoneFromSite2 = By.xpath("//li[2]/div[@class='free-num__numbers-text']");
    By mailFromSite = By.id("email_addr");
//    By mailFromSite = By.id("mail");
    By copyButton = By.id("click-to-copy");





    //headers
    By sigInHeader = By.xpath("//a[@href='/profile?section=personalData']/span");
    By incorrectSigInHeader = By.xpath("//p[@class='message popup-auth__message message_error']");
    By incorrectCodeHeader = By.xpath("//p[text()='Необходимо указать код подтверждения']");
    By incorrectEmailHeader = By.xpath("//p[text()='Необходимо указать электронную почту']");
    By incorrectNameHeader = By.xpath("//p[text()='Необходимо указать имя']");
    By noConsentHeader = By.xpath("//p[@class='message popup-auth__message message_error']");
    By incorrectPhoneHeader = By.xpath("//p[text()='Необходимо указать телефон']");


    //    ---Методы и хедеры--------


    public MainPage clickOnConsentButton() {
        driver.findElement(consentButton).click();
        return this;
    }

    public String getPhoneFromSite() {
        return driver.findElement(phoneFromSite).getAttribute("textContent");
    }

    public String getPhoneFromSite2() {
        return driver.findElement(phoneFromSite2).getAttribute("textContent");
    }

    public String getPhoneFromSite3() {
        int random_number = a + (int) (Math.random() * b);
        return driver.findElement(By.xpath("//tr[" + random_number + "]/td[@class='text-left']/a/b")).getAttribute("textContent");
    }

    public String getMailFromSite() {
        return driver.findElement(mailFromSite).getAttribute("textContent");
    }

//    public String getMailFromSite() {
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(copyButton));
//        return driver.findElement(mailFromSite).getAttribute("value");
//    }

    public MainPage clickOnExitButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton));
         driver.findElement(exitButton).click();
        return this;
    }

    public MainPage clickOnSigInButton() {
        driver.findElement(sigInButton).click();
        return this;
    }

    public MainPage clickOnLcInButton() {
        driver.findElement(lcButton).click();
        return this;
    }

    public MainPage typeLogin(String phone) {
        driver.findElement(login).sendKeys(phone);
        return this;
    }

    public MainPage typeEmail(String email) {
        driver.findElement(authEmail).sendKeys(email);
        return this;
    }

    public MainPage typePhone(String phone) {
        driver.findElement(authPhone).sendKeys(phone);
        return this;
    }

    public MainPage typeName(String name) {
        driver.findElement(authName).sendKeys(name);
        return this;
    }

    public MainPage clickOnRegisterButton() {
        driver.findElement(registerButton).click();
        return this;
    }

    public MainPage typePassword(String password) {
        driver.findElement(authPassword).sendKeys(password);
        return this;
    }

    public MainPage typeEmailPassword(String password) {
        driver.findElement(authEmailPassword).sendKeys(password);
        return this;
    }

    public MainPage clickOnGetPasswordButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(getPassword));
        return this;
    }

    public MainPage sigInWithPhoneOrEmail(String phoneOrEmail) {
        this.clickOnSigInButton();
        this.typeLogin(phoneOrEmail);
        this.clickOnGetPasswordButton();

        return new MainPage(driver);
    }


    public MainPage sigInWithPassword(String password) {
        this.typePassword(password);
        return new MainPage(driver);
    }

    public MainPage registerWithPhoneNumber(String password, String email, String name) {
        this.typePassword(password);
        this.typeEmail(email);
        this.typeName(name);
        this.clickOnRegisterButton();
        return new MainPage(driver);
    }

    public MainPage registerWithEmail(String password, String phone, String name) {
        this.typeEmailPassword(password);
        this.typePhone(phone);
        this.typeName(name);
        this.clickOnRegisterButton();
        return new MainPage(driver);
    }

    public MainPage registerWithoutConsent(String password, String email, String name) {
        this.typePassword(password);
        this.typeEmail(email);
        this.typeName(name);
        this.clickOnConsentButton();
        this.clickOnRegisterButton();
        return new MainPage(driver);
    }

    public MainPage emailRegisterWithoutConsent(String password, String phone, String name) {
        this.typeEmailPassword(password);
        this.typePhone(phone);
        this.typeName(name);
        this.clickOnConsentButton();
        this.clickOnRegisterButton();
        return new MainPage(driver);
    }


    public String getSigOutHeader() {
        return driver.findElement(sigInButton).getAttribute("aria-label");
    }

    public String getIncorrectSigInHeader() {
        return driver.findElement(incorrectSigInHeader).getText();
    }

    public String getSigInHeader() {
        return driver.findElement(sigInHeader).getAttribute("textContent");
    }

    public String getIncorrectEmailHeader() {
        return driver.findElement(incorrectEmailHeader).getText();
    }

    public String getIncorrectPhoneHeader() {
        return driver.findElement(incorrectPhoneHeader).getText();
    }

    public String getIncorrectNameHeader() {
        return driver.findElement(incorrectNameHeader).getText();
    }

    public String getIncorrectCodeHeader() {
        return driver.findElement(incorrectCodeHeader).getText();
    }

    public String getNoConsentHeader() {
        return driver.findElement(noConsentHeader).getText();
    }


//    ------------SQL---------------------

    public void deletePhone() {
        worker = new DBWorker();
        String query = "delete from user where login=+79501978905";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
//                user.setUser_id(resultSet.getInt("user_id"));
//                user.setPhone(resultSet.getString("phone"));
//                user.setIs_verified(resultSet.getInt("is_verified"));
//                user.setCreated_at(resultSet.getDate("created_at"));
//                user.setUpdated_at(resultSet.getDate("updated_at"));
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteEmail() {
        worker = new DBWorker();
        String query = "delete from user where login=+79500000000";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
//                user.setUser_id(resultSet.getInt("user_id"));
//                user.setPhone(resultSet.getString("phone"));
//                user.setIs_verified(resultSet.getInt("is_verified"));
//                user.setCreated_at(resultSet.getDate("created_at"));
//                user.setUpdated_at(resultSet.getDate("updated_at"));
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getPhonePassword() {
        worker = new DBWorker();
        String code = null;
        String query = "select code from user_authentication_code where id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }


    public String getEmailPassword() {
        worker = new DBWorker();
        String code = null;
        String query = "select code from user_authentication_code where id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }


    public static void main(String[] args) {
        DBWorker worker = new DBWorker();
//        String query = "select * from user where login=+79501978905";
//
        String query = "select code from user_authentication_code where phone=+79126459328 and id=(SELECT MAX(id) FROM user_authentication_code)";


//        String query = "select code from user_authentication_code where email='rundkvist@poisondrop.ru' and id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String phone = resultSet.getString("phone");
                String code = resultSet.getString("code");
//                mainPage.User user = new mainPage.User();
//                user.setId(resultSet.getInt("id"));
//                user.setUser_id(resultSet.getInt("user_id"));
//                user.setPhone(resultSet.getString("phone"));
//                user.setIs_verified(resultSet.getInt("is_verified"));
//                user.setCreated_at(resultSet.getDate("created_at"));
//                user.setUpdated_at(resultSet.getDate("updated_at"));
//                System.out.println(id);
                System.out.println(code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        worker.getSession().disconnect();

    }

}
