package io.hephaistos.apollon.report;

public class OsDetailRetriever implements DetailRetriever{

	private String getOsName() {
		return System.getProperty("os.name");
	}
	public String generateText() {
		return String.format("System OS: %s", getOsName());
	}
}
