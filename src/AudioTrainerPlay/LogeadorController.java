package AudioTrainerPlay;

import model.Logeador;

public class LogeadorController {
	public void loginLog(String userp){
		Logeador l = new Logeador();
		l.loginLog(userp);
	}
	public void gameLog(String userp, String levelp, String audiop, int pointsp){
		Logeador l = new Logeador();
		l.gameLog(userp, levelp, audiop, pointsp);
	}
}
