package com.classroom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import javax.swing.text.StyledEditorKit.BoldAction;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.github.dockerjava.api.command.CreateConfigCmd;
import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.Exit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class single_login {
    static String details = "";

    public static boolean login(WebDriver driver, String Username, String Password) throws InterruptedException {
        driver.get("https://main.edvora.me/l");
        Thread.sleep(3000);
        if (driver.getCurrentUrl().equals("https://main.edvora.me/l")) {
            driver.findElement(By.id("username")).sendKeys(Username);
            driver.findElement(By.id("password")).sendKeys(Password);
            driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
            driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
            Thread.sleep(6000);
        }
        if (driver.getCurrentUrl().equals("https://classrooms.edvora.me/")) {
            return true;
        } else
            return false;
    }

    public static void CreateClassroom(WebDriver driver) throws InterruptedException, IOException {
        driver.findElement(By.id("menu-button-3")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("menu-list-3-menuitem-4")).click();
        Thread.sleep(1000);
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String title = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        driver.findElement(
                By.xpath("/html/body/div[2]/div[4]/div/section/div[2]/div[1]/div[1]/div[2]/div[1]/div/input"))
                .sendKeys(title);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[4]/div/section/div[2]/div[1]/div[1]/div[2]/div[2]/div/input"))
                .sendKeys("A");
        driver.findElement(
                By.xpath("/html/body/div[2]/div[4]/div/section/div[2]/div[1]/div[1]/div[2]/div[3]/div/input"))
                .sendKeys("B");
        driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/section/div[2]/div[2]/div/button")).click();
        driver.get("https://classrooms.edvora.me/");
        ;
        Thread.sleep(3000);
        WebElement clsrms = driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div[2]"));
        List<WebElement> clsrmslist = clsrms.findElements(By.tagName("a"));
        int last = clsrmslist.size();
        List<WebElement> classcode = clsrmslist.get(last - 1).findElements(By.tagName("p"));
        File file = new File("./classrooms.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linee = br.readLine();
        linee = linee + "," + classcode.get(1).getText();
        br.close();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        fw.write(linee);
        fw.close();
    }

    public static void createpost(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("feed")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/div[3]/div/button[1]")).click();
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 500; // limit of text area
        Random random = new Random();
        details = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div/div[1]/div[2]/div/div/textarea"))
                .sendKeys(details);
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/nav/div/div/div/button[2]")).click();
        Thread.sleep(3000);
    }

    public static void createnotes(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("materials")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/div[3]/div/button[2]")).click();
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 500; // limit of text area
        Random random = new Random();
        String Title = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div/div[1]/div[1]/div/input"))
                .sendKeys(Title);
        targetStringLength = 5000; // limit of text area
        String description = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div/div[1]/div[3]/div/div/textarea"))
                .sendKeys(description);
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/nav/div/div/div/button[2]")).click();
    }

    public static void createsyllabus(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("materials")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/div[3]/div/button[3]")).click();
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 500; // limit of text area
        Random random = new Random();
        String Title = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div/div[1]/div[1]/div/input"))
                .sendKeys(Title);
        targetStringLength = 5000; // limit of text area
        String description = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div/div[1]/div[3]/div/div/textarea"))
                .sendKeys(description);
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/nav/div/div/div/button[2]")).click();

    }

    public static void createassignment(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("assignments")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/div[3]/div/button[4]")).click();
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 500; // limit of text area
        Random random = new Random();
        String Title = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div/div[1]/div[1]/div/input"))
                .sendKeys(Title);
        targetStringLength = 5000; // limit of text area
        String description = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div/div[1]/div[3]/div/div/textarea"))
                .sendKeys(description);
        String cdate = driver
                .findElement(
                        By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div/div[1]/div[5]/div[1]/div/input"))
                .getAttribute("min");
        String[] spdate = cdate.split("-");
        int[] ymd = new int[spdate.length];
        for (int i = 0; i < spdate.length; i++) {
            ymd[i] = Integer.parseInt(spdate[i]);
        }
        int day = (int) (Math.random() * (28 - ymd[2] + 1) + ymd[2]);
        int mnth = (int) (Math.random() * (12 - ymd[1] + 1) + ymd[1]);
        // int yer=(int)(Math.random()*(2021-ymd[0]+1)+ymd[0]);
        WebElement dob = driver.findElement(
                By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div/div[1]/div[5]/div[1]/div/input"));
        if (mnth < 9) {
            dob.sendKeys(Integer.toString(mnth));
            dob.sendKeys(Keys.ARROW_RIGHT);
        } else {
            dob.sendKeys(Integer.toString(mnth));
        }
        if (day < 10) {
            dob.sendKeys(Integer.toString(day));
            dob.sendKeys(Keys.ARROW_RIGHT);
        } else {
            dob.sendKeys(Integer.toString(day));
        }
        // if(yer<1000){
        // dob.sendKeys(Integer.toString(Integer.parseInt(y)));
        // dob.sendKeys(Keys.ARROW_RIGHT);
        // }
        // else{
        // dob.sendKeys(Integer.toString(Integer.parseInt(y)));
        // }
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div/div/div[1]/div[7]/div/input"))
                .sendKeys(Integer.toString((int) (Math.random() * (100 - 1 + 1) + 1)));
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/div[2]/nav/div/div/div/button[2]")).click();
    }

    public static void createpoll(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("polls")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/div[3]/div/button[5]")).click();
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 500; // limit of text area
        Random random = new Random();
        String Title = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div[1]/div/div[1]/div/input"))
                .sendKeys(Title);
        targetStringLength = 500; // limit of text area
        String qeues = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        driver.findElement(
                By.xpath("/html/body/div/div/main/div/div/div[2]/div[1]/div/div[2]/div[1]/div/div/div/textarea"))
                .sendKeys(qeues);
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div[1]/div/div[2]/div[2]/div/input"))
                .sendKeys("A");
        driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/div[2]/div[1]/div/div[2]/div[3]/div/input"))
                .sendKeys("B");
        driver.findElement(By.xpath("/html/body/div/div/main/div/div/nav/div/div/div/button[2]")).click();

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        boolean flag = true;
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9000");
        WebDriverManager.chromedriver().setup();
        // setting up driver with dynamic chrome driver
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://classrooms.edvora.me/");
        Thread.sleep(3000);
        String User = "test";
        String pwd = "Hello@123";
        System.out.println("Logging into account");
        flag = login(driver, User, pwd);
        if (flag == true) {
            System.out.println("Logged in  with username " + User);
        } else {
            System.out.println("Login Failed");
            return;
        }
        Thread.sleep(3000);
        String src = driver.getPageSource();
        if (src.contains("No classroom found")) {
            System.out.println("No classroom found");
            System.out.println("Creating a classroom");
            CreateClassroom(driver);
            System.out.println("Creating a Assignment");
            createassignment(driver);
            System.out.println("Creating a Notes");
            createnotes(driver);
            System.out.println("Creating a Polls");
            createpoll(driver);
            System.out.println("Creating a Syllabus");
            createsyllabus(driver);
        } else {
            System.out.println("Classroom found");
        }

    }
}
