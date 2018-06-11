package com.pomeisl.schemizer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class GsonUtil {

	static Map<String, String> toMap(String json) {
		JsonParser parser = new JsonParser();
		JsonObject jdata = parser.parse(json).getAsJsonObject();
		Map<String, String> outMap = jdata.keySet().parallelStream()
				.collect(Collectors.toMap(key -> key, key -> jdata.get(key).toString()));
		return outMap;
	}

	static List<String> toList(String json) {
		JsonParser parser = new JsonParser();
		JsonArray jdata = parser.parse(json).getAsJsonArray();
		List<String> list = StreamSupport.stream(jdata.spliterator(), true).parallel()
				.map(jelement -> jelement.toString()).collect(Collectors.toList());
		return list;
	}

	static String toJson(Map<String, Object> map) {
		Gson gson = new Gson();
		String serialized = gson.toJson(map, Map.class);
		return serialized;
	}

	static String pretty(String json) {
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(json).getAsJsonObject();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String str = gson.toJson(obj);
		return str;
	}

	static String dataType(JsonElement je, String ident) {
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
