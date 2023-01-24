package io.hephaistos.apollon.report;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultExceptionHandlingTest {

	private ExceptionInterpreter exceptionInterpreter;
	private ExceptionThrower exceptionThrower;

	class ExceptionThrower extends Thread {

		public ExceptionThrower(){
			setDefaultUncaughtExceptionHandler(exceptionInterpreter);
		}

		@Override
		public void run() {
			try {
				throw new IOException("This shouldn't trigger");
			} catch (IOException ioe){
				//all good
			}
			throw new NullPointerException("NullPointer thrown :)");
		}
	}

	@BeforeEach
	void setup() {
		this.exceptionInterpreter = mock(ExceptionInterpreter.class);
		this.exceptionThrower = new ExceptionThrower();
	}

	@Test
	void itShouldBeTriggeredOnlyWhenThereIsAnUnhandledException() throws InterruptedException {
		var thread = new Thread(exceptionThrower);
		Assertions.assertDoesNotThrow(thread::start);
		thread.join();
		verify(exceptionInterpreter).uncaughtException(ArgumentMatchers.any(), ArgumentMatchers.any());
	}

}
