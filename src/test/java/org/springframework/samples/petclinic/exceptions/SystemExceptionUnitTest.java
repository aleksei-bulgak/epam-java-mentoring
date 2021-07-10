package org.springframework.samples.petclinic.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemExceptionUnitTest {

	@Test
	void testCreateSystemException() {
		assertNotNull(new SystemException());
	}

	@Test
	void testCreateSystemExceptionWithMessage() {
		String message = "test";
		assertEquals(new SystemException(message).getMessage(), message);
	}

	@Test
	void testCreateSystemExceptionWithException() {
		String message = "test";
		RuntimeException internalException = new RuntimeException(message);

		assertEquals(new SystemException(internalException).getCause(), internalException);
	}

	@Test
	void testCreateSystemExceptionWithExceptionAndMessage() {
		String message = "test";
		RuntimeException internalException = new RuntimeException(message);

		Exception ex = new SystemException(message, internalException);
		assertEquals(ex.getMessage(), message);
		assertEquals(new SystemException(internalException).getCause(), internalException);
	}

}
