package project.test.todo.pagemodules;

import org.junit.Test;
import project.test.todo.pageobjects.AtTodoMVCWithClearDataAfterEachTest;
import static project.test.todo.pagemodules.pages.TodoMVCPage.*;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.visible;

public class TodoTest extends AtTodoMVCWithClearDataAfterEachTest {
    
    @Test
    public void testAtAllFilter(){

        //Given
        addTask("1", "2", "3", "4");
        tasks.shouldHave(exactTexts("1", "2", "3", "4"));
        //Tasks left counter
        checkCounter(4);

        //Edit task
        editTask("2", "2:edited");
        tasks.shouldHave(exactTexts("1", "2:edited", "3", "4"));

        //Delete task
        destroyTask("2:edited");
        tasks.shouldHave(texts("1", "3", "4"));
        checkCounter(3);

        //Mark task as completed
        toggleTask("4");
        //Clear all completed tasks
        clearCompleted();
        tasks.shouldHave(texts("1", "3"));
        checkCounter(2);

        //Mark all tasks as completed
        toggleAllTasks();
        checkCounter(0);
        //Unmark all tasks as completed
        toggleAllTasks();
        checkCounter(2);

        //Mark all tasks as completed
        toggleAllTasks();
        //Clear all completed tasks
        clearCompleted();
        tasks.shouldBe(empty);

    }

    @Test
    public void testAtActiveFilter(){

        //Given
        addTask("1");
        //Select Active filter
        setActiveFilter();
        addTask("2", "3", "4");
        tasks.shouldHave(exactTexts("1", "2", "3", "4"));
        checkCounter(4);

        //Edit task
        editTask("2", "2:edited");
        tasks.shouldHave(exactTexts("1", "2:edited", "3", "4"));

        //Delete task
        destroyTask("2:edited");
        tasks.shouldHave(texts("1", "3", "4"));
        checkCounter(3);

        //Mark task as completed
        toggleTask("4");
        tasks.filterBy(visible).shouldHave(texts("1", "3"));
        setAllFilter();
        tasks.shouldHave(texts("1", "3", "4"));
        setCompletedFilter();
        tasks.filterBy(visible).shouldHave(texts("4"));
        setActiveFilter();
        checkCounter(2);

        //Mark all tasks as completed
        toggleAllTasks();
        tasks.filterBy(visible).shouldBe(empty);
        checkCounter(0);
        //Unmark all tasks as completed
        toggleAllTasks();
        tasks.shouldHave(texts("1", "3", "4"));
        checkCounter(3);

    }

    @Test
    public void testCompletedFilter(){
        //Given
        addTask("1", "3", "4");

        //Select Completed filter
        setCompletedFilter();
        tasks.filterBy(visible).shouldBe(empty);

        //Mark all tasks as completed
        setAllFilter();
        toggleAllTasks();
        setCompletedFilter();
        tasks.shouldHave(texts("1", "3", "4"));
        checkCounter(0);

        //Delete task
        destroyTask("3");
        tasks.shouldHave(texts("1", "4"));

        //Unmark task as completed
        toggleTask("4");
        tasks.filterBy(visible).shouldHave(texts("1"));
        checkCounter(1);
        setAllFilter();
        tasks.shouldHave(texts("1", "4"));
        setActiveFilter();
        tasks.filterBy(visible).shouldHave(texts("4"));
        setCompletedFilter();

        //Unmark all tasks as completed
        toggleAllTasks();
        toggleAllTasks();
        tasks.filterBy(visible).shouldBe(empty);
        checkCounter(2);

        //Mark all tasks as completed
        toggleAllTasks();
        //Clear all completed tasks
        clearCompleted();
        tasks.shouldBe(empty);

    }

}