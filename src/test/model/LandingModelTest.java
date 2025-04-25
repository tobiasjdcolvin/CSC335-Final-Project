package test.model;

import model.LandingModel;

import org.junit.Assert;
import org.junit.Test;
import view.LandingView;
import view.Observer;

import java.awt.*;

public class LandingModelTest {
    public class myObserver implements Observer{
        @Override
        public void newText(String text, Color color) {
            String myString = "this method doesn't need to do anything right now.";
        }
    }
    myObserver myObserverInstance = new myObserver();
    String myUsername = "jumpo";
    LandingView myView = new LandingView(myUsername);
    LandingModel myModel = new LandingModel(myView, myUsername);

    @Test
    public void testConstructor() {
        Assert.assertEquals(myModel.getUsername(), myUsername);
    }

    @Test
    public void testPlay() {
        myModel.play();
        Assert.assertEquals(myModel.getUsername(), myUsername);
    }

    @Test
    public void testLogout() {
        boolean result = myModel.logout();
        Assert.assertEquals(true, result);
    }

    @Test
    public void testLeaderboard() {
        boolean result = myModel.leaderboard();
        Assert.assertEquals(true, result);
    }

    @Test
    public void testRegisterDeregister() {
        myModel.registerObserver(myObserverInstance);
        myModel.deregisterObserver(myObserverInstance);
    }
}
