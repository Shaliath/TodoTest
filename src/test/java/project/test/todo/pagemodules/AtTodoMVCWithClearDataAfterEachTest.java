package project.test.todo.pagemodules;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class AtTodoMVCWithClearDataAfterEachTest extends WithReportedScreenshotsPerTest {

    @Before
    public void openTodoMVC(){
        open("http://todomvc.com/examples/troopjs_require/#");
    }

    public void clearData(){
        executeJavaScript("localStorage.clear()");
        open("http://todomvc.com");
    }

    @After
    public void screenshotAndClearData() throws IOException{
        tearDown();
        clearData();
    }

}
