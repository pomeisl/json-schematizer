package com.pomeisl.schemizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class JsonUtil {

	protected static String pretty(String json) {
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(json).getAsJsonObject();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String str = gson.toJson(obj);
		return str;
	}

	protected static String dataType(JsonElement je) {
		if (je.isJsonNull())
			return "null";

		if (je.isJsonPrimitive()) {
			if (je.getAsJsonPrimitive().isBoolean())
				return "boolean";
			if (je.getAsJsonPrimitive().isString())
				return "string";
			if (je.getAsJsonPrimitive().isNumber()) {
				String value = je.getAsString();
				if (!value.contains(".")) {
					return "integer";
				} else {
					return "number";
				}
			}
		}

		if (je.isJsonArray()) {
			return "array";
		}

		if (je.isJsonObject()) {
			return "object";
		}
		return "";

	}
}
