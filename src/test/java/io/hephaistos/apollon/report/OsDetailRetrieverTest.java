package io.hephaistos.apollon.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OsDetailRetrieverTest {

	OsDetailRetriever osDetailRetriever;

	@BeforeAll
	void setup(){
		osDetailRetriever = new OsDetailRetriever();
	}

	@Test
	void generatedTextHasTheCorrectFormat() {
		final String osName = System.getProperty("os.name");
		Assertions.assertEquals("System OS: " + osName, osDetailRetriever.generateText());
	}

}
