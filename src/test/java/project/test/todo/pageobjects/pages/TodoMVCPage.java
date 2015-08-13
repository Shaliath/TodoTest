package project.test.todo.pageobjects.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodoMVCPage {

    public ElementsCollection tasks = $$("#todo-list>li");

    @Step
    public void clearCompleted() {
        $("#clear-completed").click();
        $("#clear-completed").shouldBe(hidden);
    }

    @Step
    public void addTask(String... texts){
        for (String text:texts){
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public void destroyTask(String text){
        tasks.find(text(text)).hover().find(".destroy").click();
    }

    @Step
    public void toggleTask(String text){
        tasks.find(text(text)).find(".toggle").click();
    }

    @Step
    public void toggleAllTasks(){
        $("#toggle-all").click();
    }

    public void checkCounter(int number) {
        $$("#todo-count strong").shouldHave(exactTexts(String.valueOf(number)));
    }

    @Step
    public void editTask(String from, String to){
        tasks.find(exactText(from)).find("label").doubleClick();
        tasks.findBy(cssClass("editing")).find(".edit").setValue(to).pressEnter();
    }

    @Step
    public void setAllFilter(){
        $(By.linkText("All")).click();
    }

    @Step
    public void setActiveFilter(){
        $(By.linkText("Active")).click();
    }

    @Step
    public void setCompletedFilter(){
        $(By.linkText("Completed")).click();
    }


}
