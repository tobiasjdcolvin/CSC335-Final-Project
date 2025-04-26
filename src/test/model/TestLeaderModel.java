package test.model;

import model.LeaderModel;
import org.junit.Test;
import view.LeaderView;
import view.Observer;

import java.awt.*;

public class TestLeaderModel {
    public class myObserver implements Observer {
        @Override
        public void newText(String text, Color color) {
            String myString = "this method doesn't need to do anything right now.";
        }
    }
    myObserver myObserverInstance = new myObserver();
    String myUserName = "adam";
    LeaderView myLeaderView = new LeaderView(myUserName);
    LeaderModel myLeaderModel = new LeaderModel(myLeaderView, myUserName);


    @Test
    public void testBack() {
        myLeaderModel.back();
    }

    @Test
    public void testRegisterDeregister() {
        myLeaderModel.registerObserver(myObserverInstance);
        myLeaderModel.deregisterObserver(myObserverInstance);
    }
}
