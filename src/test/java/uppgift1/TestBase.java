package uppgift1;

import org.junit.jupiter.api.*;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TestBase {
	// Shared between all tests in this class and subclasses.
		  protected static Playwright playwright;
		  protected static Browser browser;

		  // New instance for each test method.
		  protected BrowserContext context;
		  protected Page page;

		  @BeforeAll
		  static void launchBrowser() {
		    playwright = Playwright.create();
		    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		  }

		  @AfterAll
		  static void closeBrowser() {
		    playwright.close();
		  }

		  @BeforeEach
		  void createContextAndPage() {
		    context = browser.newContext();
		    page = context.newPage();
		  }

		  @AfterEach
		  void closeContext() {
		    context.close();
		  }


}
