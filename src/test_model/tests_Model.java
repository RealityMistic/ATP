package test_model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ test_AudioManager.class, test_Data2.class, test_JSONParser.class, test_Logeador.class,
		test_NoprizeManager.class, test_PrizeManager.class, test_TopTenPoints.class, test_TranscriptManager.class,
		test_UserManager.class })
public class tests_Model {

}
