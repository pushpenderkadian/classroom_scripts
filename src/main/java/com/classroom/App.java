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

public class App 
{
    public static boolean login(WebDriver driver,String Username,String Password) throws InterruptedException{
        driver.get("https://main.edvora.me/l");
        Thread.sleep(3000);
        System.out.println("Creating Event");
        if(driver.getCurrentUrl().equals("https://main.edvora.me/l")){
            driver.findElement(By.id("username")).sendKeys(Username);
            driver.findElement(By.id("password")).sendKeys(Password);
            driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
            driver.findElement(By.xpath("//*[@id='__next']/div/main/div/div/button")).click();
            Thread.sleep(6000);
        }
        if(driver.getCurrentUrl().equals("https://classrooms.edvora.me/")){
            return true;
        }
        else 
            return false;
    }
    public static void main( String[] args ) throws InterruptedException
    {
        boolean flag=true;
        ChromeOptions options = new ChromeOptions(); 
        options.setExperimentalOption("debuggerAddress","localhost:9000");
		WebDriverManager.chromedriver().setup();
        //setting up driver with dynamic chrome driver
		WebDriver driver = new ChromeDriver(options);
        driver.get("https://classrooms.edvora.me/");
        Thread.sleep(3000);
        File file = new File("./idpass.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linee = br.readLine();
        String[] eves = linee.split(",")
        System.out.println("Creating Event");
        
    }
}
