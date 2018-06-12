package com.pomeisl.schemizer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pomeisl.schematizer.Schematizer;
import com.pomeisl.schematizer.SchematizerFactory;
import com.pomeisl.schematizer.SchematizerForm;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
public class Schemizing extends TestCase {

	@Test
	public void testJsonIsInitializedProperly() {
		// Arrange
		Schematizer mock = SchematizerFactory.getInstance(SchematizerForm.DRAFT7); // Mockito.mock(Draft7Schemizer.class);
		// Act
		mock.load("{}");
		// Assert
		String prop = Whitebox.getInternalState(mock, "json");
		assertNotNull(prop);
	}

	@Test
	public void testGeneratePropoertiesNode() {
		// Arrange
		Schematizer mock = SchematizerFactory.getInstance(SchematizerForm.DRAFT7); // Mockito.mock(Draft7Schemizer.class);
		// Act
		mock.load("{ \"title\": \"tst\"}");
		String schema = mock.schematize();
		JsonObject e = new Gson().fromJson(schema, JsonObject.class);
		// Assert
		String prop = Whitebox.getInternalState(mock, "json");
		assertNotNull(prop);
		assertTrue(e.has("properties"));
		assertNotNull(e.has("properties"));
	}

}
