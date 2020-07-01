package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	private static final String FIRST_NAME = "Pulkit";
	private static final String LAST_NAME = "Rastogi";
	private static final String USER_NAME = "pulkit97";
	private static final String USER_PASSWORD = "ch@nge4good06j@N";

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();

		// To maximize browser
		driver.manage().window().maximize();

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(
				2, TimeUnit.SECONDS);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void unauthorizedUserAccessTest() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "//");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/access");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/error");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/signup");
		Thread.sleep(1000);
		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/login");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());

	}

	@Test
	public void SingUpAndLoginFlowTest() throws InterruptedException{
		signUpUser();
		loginUser();
		Assertions.assertEquals("Home", driver.getTitle());

		WebElement element = driver.findElement(By.id("logout"));
		element.click();
		Thread.sleep(1000);

		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void credentialsTabTest() throws InterruptedException {
		signUpUser();
		loginUser();

		//Home Page
		Assertions.assertEquals("Home", driver.getTitle());

		//Navigate to Credentials Tab
		WebElement credentialTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialTab.click();
		Thread.sleep(1000);

		//Click to add a new credential
		WebElement credentialModal = driver.findElement(By.id("show-credentials-modal"));
		credentialModal.click();
		Thread.sleep(1000);

		//Input Credential Url
		WebElement credentialUrl = driver.findElement(By.id("credential-url"));
		credentialUrl.sendKeys("http://www.amway.in/login");

		//Input Credential Username
		WebElement credentialUserName = driver.findElement(By.id("credential-username"));
		credentialUserName.sendKeys(USER_NAME);

		//Input Credential Password
		WebElement credentialPassword = driver.findElement(By.id("credential-password"));
		credentialPassword.sendKeys(USER_PASSWORD);

		//Submit to add the new credential
		WebElement credentialSubmit = driver.findElement(By.id("credential-submit"));
		credentialSubmit.click();
		Thread.sleep(1000);

		//Should get a success message on results page.
		Assertions.assertEquals("Result", driver.getTitle());
		WebElement successMessage = driver.findElement(By.id("success"));
		Assertions.assertEquals("Success",successMessage.getText());

		//Click to return home.
		WebElement home = driver.findElement(By.id("return-home"));
		home.click();
		Thread.sleep(1000);

		//Navigate to credentials tab
		credentialTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialTab.click();
		Thread.sleep(1000);

		//There should be 1 credential saved.
		boolean isPresent = driver.findElements(By.cssSelector("td.credential-url-saved")).size()==1;
		Assertions.assertTrue(isPresent);

		//Check if the URL is the one we saved or not.
		WebElement savedUrl = driver.findElement(By.cssSelector("td.credential-url-saved"));
		Assertions.assertEquals("http://www.amway.in/login", savedUrl.getText());
		Thread.sleep(1000);

		//Click to edit credential
		WebElement editCredential = driver.findElement(By.cssSelector("button.credential-edit"));
		editCredential.click();
		Thread.sleep(1000);

		//Append the string to URL field
		credentialUrl = driver.findElement(By.id("credential-url"));
		credentialUrl.sendKeys("/india");

		//Submit to save the credential
		WebElement saveCredential = driver.findElement(By.id("credential-submit"));
		saveCredential.click();
		Thread.sleep(1000);

		//Should get a success message on results page.
		Assertions.assertEquals("Result", driver.getTitle());
		WebElement successMessage1 = driver.findElement(By.id("success"));
		Assertions.assertEquals("Success",successMessage1.getText());

		//Click to return home
		home = driver.findElement(By.id("return-home"));
		home.click();
		Thread.sleep(1000);

		//Click on credentials tab.
		credentialTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialTab.click();
		Thread.sleep(1000);

		//Edited credential should be saved.
		isPresent = driver.findElements(By.cssSelector("td.credential-url-saved")).size()==1;
		Assertions.assertTrue(isPresent);

		//Check if the URL is the one we saved or not.
		savedUrl = driver.findElement(By.cssSelector("td.credential-url-saved"));
		Assertions.assertEquals("http://www.amway.in/login"+"/india", savedUrl.getText());

		//Click on delete button
		WebElement deleteCredential = driver.findElement(By.cssSelector("a.credential-delete"));
		deleteCredential.click();
		Thread.sleep(1000);

		//Should get a success message on results page.
		Assertions.assertEquals("Result", driver.getTitle());
		successMessage = driver.findElement(By.id("success"));
		Assertions.assertEquals("Success",successMessage.getText());

		//Return home
		home = driver.findElement(By.id("return-home"));
		home.click();
		Thread.sleep(1000);

		//Navigate to credential tab.
		credentialTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialTab.click();
		Thread.sleep(1000);

		//Check if the credentials is deleted and list is empty
		boolean isDeleted = driver.findElements(By.cssSelector("td.credential-url-saved")).size()==0;
		Assertions.assertTrue(isDeleted);
	}


	@Test
	public void notesTabTest() throws InterruptedException {
		signUpUser();
		loginUser();

		//Home Page should be rendered.
		Assertions.assertEquals("Home", driver.getTitle());

		//Navigate to notes tab.
		WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
		notesTab.click();
		Thread.sleep(1000);

		//Click to create new note
		WebElement showNoteModel = driver.findElement(By.id("show-note-modal"));
		showNoteModel.click();
		Thread.sleep(1000);

		//Input note title
		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.sendKeys("Selenium");

		//Input note description
		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.sendKeys("Today, I learnt from the practical hands-on while working on the basics of selenium automation testing");

		//Submit to save note
		WebElement saveNote = driver.findElement(By.id("note-submit"));
		saveNote.click();
		Thread.sleep(1000);

		//Should get a success message on results page.
		Assertions.assertEquals("Result", driver.getTitle());
		WebElement successMessage = driver.findElement(By.id("success"));
		Assertions.assertEquals("Success",successMessage.getText());

		//Return home
		WebElement home = driver.findElement(By.id("return-home"));
		home.click();
		Thread.sleep(1000);

		//Navigate to notes tab.
		notesTab = driver.findElement(By.id("nav-notes-tab"));
		notesTab.click();
		Thread.sleep(1000);

		//There should be 1 note saved.
		boolean isPresent = driver.findElements(By.cssSelector("td.note-title-row")).size()==1;
		Assertions.assertTrue(isPresent);

		//Check the same note is saved or not.
		WebElement savedNote = driver.findElement(By.cssSelector("td.note-title-row"));
		Assertions.assertEquals("Selenium", savedNote.getText());
		Thread.sleep(1000);

		//Click to edit note
		WebElement editNote = driver.findElement(By.cssSelector("button.note-edit"));
		editNote.click();
		Thread.sleep(1000);

		//Append text to note title
		noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.sendKeys(" Testing");
		Thread.sleep(1000);

		//Submit to save edited note
		saveNote = driver.findElement(By.id("note-submit"));
		saveNote.click();
		Thread.sleep(1000);

		//Should get a success message on results page.
		Assertions.assertEquals("Result", driver.getTitle());
		successMessage = driver.findElement(By.id("success"));
		Assertions.assertEquals("Success",successMessage.getText());

		//Return home
		home = driver.findElement(By.id("return-home"));
		home.click();
		Thread.sleep(1000);

		//Navigate to notes tab.
		notesTab = driver.findElement(By.id("nav-notes-tab"));
		notesTab.click();
		Thread.sleep(1000);

		//There should be 1 note saved.
		isPresent = driver.findElements(By.cssSelector("td.note-title-row")).size()==1;
		Assertions.assertTrue(isPresent);

		//Check if the edited note is rendered or not.
		savedNote = driver.findElement(By.cssSelector("td.note-title-row"));
		Assertions.assertEquals("Selenium Testing", savedNote.getText());

		//Click to delete note
		WebElement deleteNote = driver.findElement(By.cssSelector("a.note-delete"));
		deleteNote.click();
		Thread.sleep(1000);

		//Should get a success message on results page.
		Assertions.assertEquals("Result", driver.getTitle());
		successMessage = driver.findElement(By.id("success"));
		Assertions.assertEquals("Success",successMessage.getText());

		//Return home
		home = driver.findElement(By.id("return-home"));
		home.click();
		Thread.sleep(1000);

		//Navigate to notes tab.
		notesTab = driver.findElement(By.id("nav-notes-tab"));
		notesTab.click();
		Thread.sleep(1000);

		//Is note deleted
		boolean isDeleted = driver.findElements(By.cssSelector("td.note-title-row")).size()==0;
		Assertions.assertTrue(isDeleted);
	}

	private void loginUser() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");
		Thread.sleep(1000);

		WebElement element = driver.findElement(By.id("inputUsername"));
		element.sendKeys(USER_NAME);

		element = driver.findElement(By.id("inputPassword"));
		element.sendKeys(USER_PASSWORD);

		element = driver.findElement(By.id("login-button"));
		element.click();
		Thread.sleep(1000);

	}

	private void signUpUser() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/signup");
		Thread.sleep(1000);

		WebElement element = driver.findElement(By.id("inputFirstName"));
		element.sendKeys(FIRST_NAME);

		element = driver.findElement(By.id("inputLastName"));
		element.sendKeys(LAST_NAME);

		element = driver.findElement(By.id("inputUsername"));
		element.sendKeys(USER_NAME);

		element = driver.findElement(By.id("inputPassword"));
		element.sendKeys(USER_PASSWORD);

		element = driver.findElement(By.id("signup"));
		element.click();
		Thread.sleep(1000);
	}

}