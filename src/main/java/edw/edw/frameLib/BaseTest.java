package edw.edw.frameLib;

import org.testng.ITest;

public abstract class BaseTest implements ITest {

	private String testName;

	public String getTestName() {
		return this.testName;
	}

	public void setTestName(String tn) {
		this.testName = tn;
	}

}
