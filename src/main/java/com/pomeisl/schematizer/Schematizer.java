package com.pomeisl.schematizer;

/**
 * 
 * @author pomeisl
 *
 */
public interface Schematizer {
	Schematizer load(String json);

	String schematize();
}