package io.hephaistos.apollon.report;

public class ExceptionInterpreter implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		System.out.println(thread.getName());
	}
}
