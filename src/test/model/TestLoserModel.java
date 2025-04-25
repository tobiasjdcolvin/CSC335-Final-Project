package test.model;

import java.awt.Color;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import model.LoserModel;
import view.LoserView;
import view.Observer;


public class TestLoserModel {

	public class myObserver implements Observer{
        @Override
        public void newText(String text, Color color) {
        }
    }
	
	myObserver myObserverInstance = new myObserver();
    String user = "meep";
    LoserView myView = new LoserView("meep");
    LoserModel myModel = new LoserModel(myView, user);
    
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
