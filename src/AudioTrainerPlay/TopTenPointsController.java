package AudioTrainerPlay;

import beans.Player;
import java.util.ArrayList;
import model.TopTenPoints;

public class TopTenPointsController {
	static String path;
	public TopTenPointsController(String pathp){
		path=pathp;
	}
	public ArrayList<Player> getTopTenPoints(){
		TopTenPoints t10 = new TopTenPoints(path);
		ArrayList<Player> topplayers = t10.getTopTenPoints();
		return topplayers;
	}
}
