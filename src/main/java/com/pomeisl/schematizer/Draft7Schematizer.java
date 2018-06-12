package com.pomeisl.schematizer;

import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

class Draft7Schematizer implements Schematizer {

	private String json;

	public Schematizer load(String json) {
		this.json = JsonUtil.pretty(json);
		return this;
	} // load

	public String schematize() {
		Gson gson = new Gson();
		JsonElement jElement = gson.fromJson(this.json, JsonElement.class);
		JsonObject skematized = this._schematize(jElement, true);
		return new Gson().toJson(skematized);
	} // schematize

	private JsonObject _schematize(JsonElement jElement, boolean examples) {
		JsonObject schema = new JsonObject();
		String jElementDataType = JsonUtil.dataType(jElement);

		schema.addProperty("type", jElementDataType);
		switch (jElementDataType) {
		case "object":

			JsonObject jObject = jElement.getAsJsonObject();
			JsonObject properties = new JsonObject();

			Set<String> keyset = jObject.keySet();
			keyset.parallelStream().forEach(key -> {
				JsonElement unschematized = jObject.get(key);
				JsonElement schematized = _schematize(unschematized, examples);
				properties.add(key, schematized);
			});

			schema.add("properties", properties);
			break;
		case "array":
			JsonArray jArray = jElement.getAsJsonArray();
			JsonElement firstJObject = jArray.get(0);
			JsonObject firstJObjectSchema = this._schematize(firstJObject, false);

			JsonObject valueSchemaProperties = null;
			if (examples) { // was an object
				if (firstJObjectSchema.has("properties")) {
					valueSchemaProperties = firstJObjectSchema.get("properties").getAsJsonObject();
					separatingValues(jArray, valueSchemaProperties);
				} else { // was a primitive
					if (!firstJObjectSchema.has("examples")) {
						firstJObjectSchema.add("examples", jArray);
					}
				}
			} // examples

			schema.add("items", firstJObjectSchema);
			break;
		case "integer":
		case "number":
		case "string":
		case "boolean":
			if (examples) {
				JsonArray arr_ = new JsonArray();
				arr_.add(jElement);
				schema.add("examples", arr_);
			}
			break;
		default:
			break;
		}

		return schema;
	} // _schematize

	/**
	 * 
	 * @param jArray
	 * @param properties
	 */
	private void separatingValues(JsonArray jArray, JsonObject properties) {
		
		for (JsonElement JElement : jArray) {
			JsonObject jObject = JElement.getAsJsonObject();
			Set<String> keys = jObject.keySet();
			
			for (String key : keys) {
				JsonElement jInnermost = jObject.get(key);
				JsonObject jObjectDescriptor = properties.get(key).getAsJsonObject();

				if (!jObjectDescriptor.has("examples")) {
					jObjectDescriptor.add("examples", new JsonArray());
				}

				JsonArray examples__ = jObjectDescriptor.get("examples").getAsJsonArray();
				examples__.add(jInnermost);
			}
		}
		
	} // separatingValues

}
