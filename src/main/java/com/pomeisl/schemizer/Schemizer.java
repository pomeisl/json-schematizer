package com.pomeisl.schemizer;

public interface Schemizer {
	Schemizer load(String json);

	String schmaize();
}