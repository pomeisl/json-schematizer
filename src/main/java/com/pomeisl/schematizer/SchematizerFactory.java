package com.pomeisl.schematizer;

/**
 * 
 * @author pomeisl
 *
 */
public class SchematizerFactory {

	/**
	 * Proper implementation.
	 * 
	 * @param schema
	 * @return
	 */
	public static Schematizer getInstance(SchematizerForm schema) {
		return schema.getInstance();
	}

}
