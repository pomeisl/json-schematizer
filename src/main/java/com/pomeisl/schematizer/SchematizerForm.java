package com.pomeisl.schematizer;

/**
 * Set of Schematizer Types.
 * @author pomeisl
 *
 */
public enum SchematizerForm {
	DRAFT7 {
		@Override
		Schematizer getInstance() {
			return new Draft7Schematizer();
		}
	};
	
	/**
	 * Expected instance.
	 * @param schema
	 * @return
	 */
	abstract Schematizer getInstance();
}
