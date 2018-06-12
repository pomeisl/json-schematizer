package com.pomeisl.schemizer;

public class SchemizerFactory {

	public static Schemizer getInstance(SchemizeForm schema) {
		return schema.getInstance(schema);
	}

}
