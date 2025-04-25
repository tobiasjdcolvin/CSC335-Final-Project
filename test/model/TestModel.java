package test.model;

import model.Model;
import org.junit.Test;
import view.LoginView;
import view.Observer;
import view.TextLabel;
import view.View;
import model.LoginModel;

import static org.junit.Assert.*;

public class TestModel {
    private Model model = new Model("adam", new View("adam"));
    private LoginModel login = new LoginModel(new LoginView());

    @Test
    public void testWrong() {
        model.registerObserver(new TextLabel());
        model.registerObserver(new TextLabel());
        model.registerObserver(new TextLabel());
        model.registerObserver(new TextLabel());
        model.registerObserver(new TextLabel());
        TextLabel remove = new TextLabel();
        model.registerObserver(remove);
        model.deregisterObserver(remove);
        assertFalse(model.submitUserInput("apple"));

    }

    @Test
    public void testLose() {
        for (int i=0; i<5;i++) {
            model.registerObserver(new TextLabel());
            model.registerObserver(new TextLabel());
            model.registerObserver(new TextLabel());
            model.registerObserver(new TextLabel());
            model.registerObserver(new TextLabel());
        }
        model.submitUserInput("apple");
        model.submitUserInput("grape");
        model.submitUserInput("banks");
        model.submitUserInput("tango");
        assertFalse(model.submitUserInput("salad"));
        model.submitUserInput("candy");
    }

    @Test
    public void testBack() {
        model.back();
    }

    @Test
    public void testCorrect() {
        model.registerObserver(new TextLabel());
        model.registerObserver(new TextLabel());
        model.registerObserver(new TextLabel());
        model.registerObserver(new TextLabel());
        model.registerObserver(new TextLabel());
        assertTrue(model.submitUserInput(model.getSolution()));
    }
}
