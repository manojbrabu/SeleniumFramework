package base;

import Annotations.Mandatory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

import java.lang.reflect.Field;

public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        validateMandatoryFields();
    }

    private void validateMandatoryFields() {
        for (Field field : getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(Mandatory.class)) {
                continue;
            }

            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value instanceof WebElement element) {
                    WaitUtils.visibilityOf(element);
                } else {
                    throw new IllegalStateException(
                            "Mandatory field is not a WebElement: " + getClass().getSimpleName() + "." + field.getName()
                    );
                }
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Failed to validate mandatory field: " + field.getName(), e);
            } catch (Exception e) {
                throw new IllegalStateException(
                        "Mandatory element not available: " + getClass().getSimpleName() + "." + field.getName(),
                        e
                );
            }
        }
    }
}
