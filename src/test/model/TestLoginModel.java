package model;

import org.junit.Assert;
import org.junit.Test;
import view.LoginView;
import view.Observer;

import java.awt.*;
import java.util.Random;
import java.util.random.RandomGenerator;

public class TestLoginModel {
    public class myObserver implements Observer{
        @Override
        public void newText(String text, Color color) {
            String myString = "this method doesn't need to do anything right now.";
        }
    }
    myObserver myObserverInstance = new myObserver();
    String myUsername = "jumpo";
    LoginView myView = new LoginView();
    LoginModel myModel = new LoginModel(myView);

    @Test
    public void testConstructor() {
        Assert.assertEquals(0, myModel.getObserversLen());
    }

    @Test
    public void testRegisterDeregister() {
        myModel.registerObserver(myObserverInstance);
        myModel.deregisterObserver(myObserverInstance);
    }

    @Test
    public void testSubmitUserLoginFail() {
        myModel.registerObserver(myObserverInstance);
        boolean result = myModel.submitUserLogin("NOT A USERNAME", "NOT A PASSWORD");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testSubmitUserLoginPass() {
        myModel.registerObserver(myObserverInstance);
        boolean result = myModel.submitUserLogin("adam", "smasher");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testSubmitUserLoginFailNoDebug() {
        myModel.removeDebug();
        myModel.registerObserver(myObserverInstance);
        boolean result = myModel.submitUserLogin("NOT A USERNAME", "NOT A PASSWORD");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testSubmitUserLoginPassNoDebug() {
        myModel.removeDebug();
        myModel.registerObserver(myObserverInstance);
        boolean result = myModel.submitUserLogin("adam", "smasher");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testSubmitUserRegisterFail() {
        String myUser = "adam";
        String myPass = "smasher";
        myModel.registerObserver(myObserverInstance);
        boolean result = myModel.submitUserRegister(myUser, myPass);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testSubmitUserRegisterPass() {
        Random myRand = new Random();
        int randInteger = myRand.nextInt();
        String myUser = "" + randInteger;
        String myPass = "test123";
        myModel.registerObserver(myObserverInstance);
        boolean result = myModel.submitUserRegister(myUser, myPass);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testGetUserWinLosses() {
        String myUser = "adam";
        myModel.registerObserver(myObserverInstance);
        int[] result = myModel.getUserWinLosses(myUser);
        Assert.assertEquals(true, result.length > 0);
    }

    @Test
    public void testUpdateUserWinLosses() {
        String myUser = "adam";
        myModel.registerObserver(myObserverInstance);
        myModel.updateUserWinLoss(myUser, 2, 2);
    }
}

