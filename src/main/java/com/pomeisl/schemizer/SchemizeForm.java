package com.pomeisl.schemizer;

public enum SchemizeForm {
	DRAFT7 {
		@Override
		Schemizer getInstance(SchemizeForm schema) {
			return new Draft7Schemizer();
		}
	};

	abstract Schemizer getInstance(SchemizeForm schema);
}
