package test_AudioTrainerPlay;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ test_ATPController.class, test_AudioController.class, test_ContinueExplorerAction.class,
		test_DeleteAction.class, test_JSONController.class, test_LogeadorController.class, test_LoginAction.class,
		test_PrizeController.class, test_TranscriptController.class, test_UpdateAction.class })
public class tests_Controller {

}
