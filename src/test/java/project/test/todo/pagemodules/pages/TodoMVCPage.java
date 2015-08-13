package project.test.todo.pagemodules.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodoMVCPage {

    public static ElementsCollection tasks = $$("#todo-list>li");

    @Step
    public static void clearCompleted() {
        $("#clear-completed").click();
        $("#clear-completed").shouldBe(hidden);
    }

    @Step
    public static void addTask(String... texts){
        for (String text:texts){
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public static void destroyTask(String text){
        tasks.find(text(text)).hover().find(".destroy").click();
    }

    @Step
    public static void toggleTask(String text){
        tasks.find(text(text)).find(".toggle").click();
    }

    @Step
    public static void toggleAllTasks(){
        $("#toggle-all").click();
    }

    public static void checkCounter(int number) {
        $$("#todo-count strong").shouldHave(exactTexts(String.valueOf(number)));
    }

    @Step
    public static void editTask(String from, String to){
        tasks.find(exactText(from)).find("label").doubleClick();
        tasks.findBy(cssClass("editing")).find(".edit").setValue(to).pressEnter();
    }

    @Step
    public static void setAllFilter(){
        $(By.linkText("All")).click();
    }

    @Step
    public static void setActiveFilter(){
        $(By.linkText("Active")).click();
    }

    @Step
    public static void setCompletedFilter(){
        $(By.linkText("Completed")).click();
    }


}
