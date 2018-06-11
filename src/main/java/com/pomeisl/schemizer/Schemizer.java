package com.pomeisl.schemizer;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class Schemizer {

	private String json;

	public String schmatize(String json) {
		this.load(json);
		Map<String, Object> skematized = _skematize(this.json, true);
		return GsonUtil.toJson(skematized);
	}

	private void load(String json) {
		this.json = GsonUtil.pretty(json);
	}

	private Map<String, Object> _skematize(String json, boolean includeExample) {
		Map<String, Object> skematized = new LinkedHashMap<>();
		Gson gson = new Gson();
		JsonElement jElement = gson.fromJson(json, JsonElement.class);
		String dataType = GsonUtil.dataType(jElement, null);

		skematized.put("type", dataType);
		switch (dataType) {
		case "object":
			Map<String, String> jsonMap = GsonUtil.toMap(json);
			Map<String, Map<String, Object>> properties = jsonMap.keySet().parallelStream()
					.collect(Collectors.toMap(key -> key, key -> _skematize(jsonMap.get(key), includeExample)));
			skematized.put("properties", properties);
			break;
		case "array":
			List<String> elements = GsonUtil.toList(json);
			String value = elements.get(0);
			Map<String, Object> valueSchema = this._skematize(value, false);
			skematized.put("items", valueSchema);
			if (includeExample) {
				skematized.put("examples", jElement);
			}
			break;
		case "integer":
		case "number":
		case "string":
			if (includeExample) {
				skematized.put("examples", Collections.singletonList(jElement));
			}
			break;
		default:
			break;
		}

		return skematized;
	}

}
