package com.pomeisl.schemizer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
public class Schemizing extends TestCase {

	@Test
	public void testJsonIsInitializedProperly() {
		// Arrange
		Schemizer mock = SchemizerFactory.getInstance(SchemizeForm.DRAFT7); // Mockito.mock(Draft7Schemizer.class);
		// Act
		mock.load("{}");
		// Assert
		String prop = Whitebox.getInternalState(mock, "json");
		assertNotNull(prop);
	}

	@Test
	public void testGeneratePropoertiesNode() {
		// Arrange
		Schemizer mock = SchemizerFactory.getInstance(SchemizeForm.DRAFT7); // Mockito.mock(Draft7Schemizer.class);
		// Act
		mock.load("{ \"title\": \"tst\"}");
		String schema = mock.schmaize();
		JsonObject e = new Gson().fromJson(schema, JsonObject.class);
		// Assert
		String prop = Whitebox.getInternalState(mock, "json");
		assertNotNull(prop);
		assertTrue(e.has("properties"));
		assertNotNull(e.has("properties"));
	}

}
