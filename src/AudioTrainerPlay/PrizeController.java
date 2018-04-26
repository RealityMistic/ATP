package AudioTrainerPlay;

import beans.Noprize;
import beans.Prize;
import beans.FPrize;
import model.NoprizeManager;
import model.PrizeManager;
import model.UserManager;

public class PrizeController {
	static String path;
	
	public PrizeController(String pathp) {
		super();
		path=pathp;
	}
	public Prize getNewPrize(String levelp, int pointsp){
		PrizeManager pm=new PrizeManager(path);
		Prize prize=pm.getPrize(pointsp, levelp);
		return prize;
	}
	public FPrize getFinalPrize(int pointsp){
		PrizeManager pm=new PrizeManager(path);
		FPrize finalprize=pm.getFinalPrize(pointsp);
		return finalprize;
	}
	public boolean updateExploPoints(String userp, int pointsp){
		UserManager um = new UserManager(path);
		if (um.updateExploPoints(userp, pointsp)) 
			return true;
		else return false;
	}
	
	public Noprize getNoprize(String levelp){
		NoprizeManager npm= new NoprizeManager(path);
		Noprize noprize=npm.getNoprize(levelp);
		return noprize;
	}
}
