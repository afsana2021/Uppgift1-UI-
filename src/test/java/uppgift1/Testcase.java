package uppgift1;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;



import java.nio.file.Path;


import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;





public class Testcase extends TestBase {
	
    @Test
    public void testVerifyWebsiteTitle() {
        page.navigate("https://www.tahara.se/");
        String pageTitle = page.title();
        assertEquals("TAHARA", pageTitle);
    }

   
    @Test
    public void searchText() {
        page.navigate("https://www.tahara.se/");

        // Locate the search input field by its ARIA role and set its value
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).fill("byxor");

        // Verify that the search input field contains the expected value
        String actualSearchValue = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).inputValue();
        String expectedSearchValue = "byxor";

        assertEquals(expectedSearchValue, actualSearchValue);
    }
    
    
    @Test
    public void takeScreenshot() {
        page.navigate("https://www.tahara.se/");

     // Specify the path to save the screenshot
        Path screenshotPath = Path.of("screenshot.png");

        // Take a screenshot of the entire page
        page.screenshot(new ScreenshotOptions().setPath(screenshotPath.toString()));

        // Verify that the screenshot file exists
        assertTrue(screenshotPath.toFile().exists());
    }

    @Test
    public void testElementPresence() {
        page.navigate("https://www.tahara.se/");

        // Wait for the presence of the element with class "hero-content"
        boolean isElementPresent = page.waitForSelector(".hero-content") != null;

        // Assert that the element is present
        assertTrue(isElementPresent);
    }

    @Test
    public void testVerifyImagePresence() {
        page.navigate("https://www.tahara.se/");

        // Verify the presence of an image
        boolean isImagePresent = page.isVisible("img.img-fluid.img-logo");
        assertTrue(isImagePresent);
    }

    @Test
    public void testVerifyPlaceholderTextInInputField() {
        page.navigate("https://www.tahara.se/");

        // Locate the input field by its selector
        String placeholderText = page.getAttribute("input.form-control.pl-3.border-0", "placeholder");

        // Verify the placeholder text
        assertEquals("Sök produkt", placeholderText);
    }

    @Test
    public  void verifylist(){
        String baseUrl = "https://www.tahara.se/";
        page.navigate(baseUrl);
        Locator linkDam = page.getByRole(AriaRole.LISTITEM)
                .filter(new Locator.FilterOptions().setHasText("Dam"));
        linkDam.click();
        
        String expectedUrl = baseUrl + "dam";
        assertEquals(expectedUrl, page.url());
       
    }

    @Test
    public void loginAsUser() {
        String loginUrl = "https://www.tahara.se/customer/login";
        page.navigate(loginUrl);
        page.waitForLoadState(LoadState.LOAD);
        // Locate elements
        String Emailselector = "input[name=email][type=text]";
        ElementHandle emailInputElement = page.querySelector(Emailselector);
        String Passwordselector = "input[name=password][type=password]";
        ElementHandle passwordInputElement = page.querySelector(Passwordselector);
        Locator loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logga in"));
        assertThat(page).hasURL("https://www.tahara.se/customer/login");

        // Interact with elements
        emailInputElement.fill("abcd@gmail.com");
        passwordInputElement.fill("acd123");
        loginButton.click();
    }
    
    @Test
	void testAddProductToCart() {
	    page.navigate("https://www.tahara.se/");
	
	    // Click on "Dam" dropdown
	    page.hover("a.nav-link.dropdown-toggle:has-text('Dam')");
	
	    // Wait for the dropdown to expand
	    page.waitForSelector(".dropdown-menu");
	
	    // Click on "Slöjor"
	    page.click("a.dropdown-item:has-text('Slöjor')");
	
	   
	    // Click on a specific product, for example, 'Dnvng Medina | Picante Red'
	    page.click("a[href='/dam/slojor/dnvng-medina-picante-red']");
	
	    // Wait for the page or element to load
	   
	
	    // Click on "Lägg i korgen" button to add the product to the cart
	    page.click(".qs-cart-submit");
	
	    // Verify that the product is added to the cart

	    assertTrue(page.isVisible(".navbar-cart-product-link:has-text('Dnvng Medina | Picante Red')"), "Product not added to the cart");
	
	}
    
    /*@Test
    public  void countlist(){
        String baseUrl = "https://www.tahara.se/";
        page.navigate(baseUrl);
        Locator linkDam = page.getByRole(AriaRole.LISTITEM)
                .filter(new Locator.FilterOptions().setHasText("Dam"));
        linkDam.click();
        }*/

    @Test
    public void searchProduct() {

        String baseUrl = "https://www.tahara.se/";
        page.navigate(baseUrl);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).fill("byxor");
        Locator search = page.locator("#button-search-desktop");
        search.click();
        assertThat(search).isVisible();
    }
    
    @Test
    public void AddToWishlist() {

        String baseUrl = "https://www.tahara.se/";
        page.navigate(baseUrl);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).fill("byxor");
        Locator search = page.locator("#button-search-desktop");
        search.click();
       
        Locator linkByxor = page.locator("text=Salman byxor Mörkgrå");
        linkByxor.click();

        page.click("text=Lägg till i önskelista");
        assertEquals(false, page.isVisible(("text=Lägg till i önskelista")));
    }
    
  
    
}

