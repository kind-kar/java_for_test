package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void init(String admin, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_password_page.php");
        type(By.name("username"), admin);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void start() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        wd.findElement(By.cssSelector("a[href='manage_user_edit_page.php?user_id=15']")).click();
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }

}
