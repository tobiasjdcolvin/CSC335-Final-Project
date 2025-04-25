package test.model;

import java.awt.Color;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import model.VictoryModel;
import view.VictoryView;
import view.Observer;

public class TestVictoryModel {

	public class myObserver implements Observer{
        @Override
        public void newText(String text, Color color) {
        }
    }
	
	myObserver myObserverInstance = new myObserver();
    String user = "meep";
    VictoryView myView = new VictoryView("meep");
    VictoryModel myModel = new VictoryModel(myView, user);

    @Test
	public void testConstructor() {
		Assert.assertEquals(myModel.getUsername(), user);
	}
	
    @Test
    public void testPlay() {
        myModel.play();
        Assert.assertEquals(myModel.getUsername(), user);
    }
    
    @Test
    public void testLeaderboard() {
        boolean result = myModel.leaderboard();
        Assert.assertEquals(true, result);
    }
    
    @Test
    public void testBack() {
		Assert.assertTrue(myModel.back());
		Assert.assertEquals(myModel.getUsername(), user);
    }
    
    @Test
    public void testRegisterDeregister() {
        myModel.registerObserver(myObserverInstance);
        myModel.deregisterObserver(myObserverInstance);
    }

}
