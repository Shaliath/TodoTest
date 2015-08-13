package project.test.todo.pageobjects;

import org.junit.Test;
import project.test.todo.pageobjects.pages.TodoMVCPage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.visible;

public class TodoTest extends AtTodoMVCWithClearDataAfterEachTest {

    TodoMVCPage page = new TodoMVCPage();
    @Test
    public void testAtAllFilter(){

        //Given
        page.addTask("1", "2", "3", "4");
        page.tasks.shouldHave(exactTexts("1", "2", "3", "4"));
        //Tasks left counter
        page.checkCounter(4);

        //Edit task
        page.editTask("2", "2:edited");
        page.tasks.shouldHave(exactTexts("1", "2:edited", "3", "4"));

        //Delete task
        page.destroyTask("2:edited");
        page.tasks.shouldHave(texts("1", "3", "4"));
        page.checkCounter(3);

        //Mark task as completed
        page.toggleTask("4");
        //Clear all completed tasks
        page.clearCompleted();
        page.tasks.shouldHave(texts("1", "3"));
        page.checkCounter(2);

        //Mark all tasks as completed
        page.toggleAllTasks();
        page.checkCounter(0);
        //Unmark all tasks as completed
        page.toggleAllTasks();
        page.checkCounter(2);

        //Mark all tasks as completed
        page.toggleAllTasks();
        //Clear all completed tasks
        page.clearCompleted();
        page.tasks.shouldBe(empty);

    }

    @Test
    public void testAtActiveFilter(){

        //Given
        page.addTask("1");
        //Select Active filter
        page.setActiveFilter();
        page.addTask("2", "3", "4");
        page.tasks.shouldHave(exactTexts("1", "2", "3", "4"));
        page.checkCounter(4);

        //Edit task
        page.editTask("2", "2:edited");
        page.tasks.shouldHave(exactTexts("1", "2:edited", "3", "4"));

        //Delete task
        page.destroyTask("2:edited");
        page.tasks.shouldHave(texts("1", "3", "4"));
        page.checkCounter(3);

        //Mark task as completed
        page.toggleTask("4");
        page.tasks.filterBy(visible).shouldHave(texts("1", "3"));
        page.setAllFilter();
        page.tasks.shouldHave(texts("1", "3", "4"));
        page.setCompletedFilter();
        page.tasks.filterBy(visible).shouldHave(texts("4"));
        page.setActiveFilter();
        page.checkCounter(2);

        //Mark all tasks as completed
        page.toggleAllTasks();
        page.tasks.filterBy(visible).shouldBe(empty);
        page.checkCounter(0);
        //Unmark all tasks as completed
        page.toggleAllTasks();
        page.tasks.shouldHave(texts("1", "3", "4"));
        page.checkCounter(3);

    }

    @Test
    public void testCompletedFilter(){
        //Given
        page.addTask("1", "3", "4");

        //Select Completed filter
        page.setCompletedFilter();
        page.tasks.filterBy(visible).shouldBe(empty);

        //Mark all tasks as completed
        page.setAllFilter();
        page.toggleAllTasks();
        page.setCompletedFilter();
        page.tasks.shouldHave(texts("1", "3", "4"));
        page.checkCounter(0);

        //Delete task
        page.destroyTask("3");
        page.tasks.shouldHave(texts("1", "4"));

        //Unmark task as completed
        page.toggleTask("4");
        page.tasks.filterBy(visible).shouldHave(texts("1"));
        page.checkCounter(1);
        page.setAllFilter();
        page.tasks.shouldHave(texts("1", "4"));
        page.setActiveFilter();
        page.tasks.filterBy(visible).shouldHave(texts("4"));
        page.setCompletedFilter();

        //Unmark all tasks as completed
        page.toggleAllTasks();
        page.toggleAllTasks();
        page.tasks.filterBy(visible).shouldBe(empty);
        page.checkCounter(2);

        //Mark all tasks as completed
        page.toggleAllTasks();
        //Clear all completed tasks
        page.clearCompleted();
        page.tasks.shouldBe(empty);

    }



}