# json-schematizer

Json schema generator for java developers.

### Example

```java
Schematizer schematizer = SchematizerFactory.getInstance(SchematizerForm.DRAFT7);
schematizer.load(json);
String scheme = schematizer.schematize();
```

### Input
```json
{
  "first_name": "Ulrich",
  "last_name": "Muldoon",
  "email": "umuldoon0@baidu.com",
  "gender": "Male",
  "location": {
    "city": "Lipka",
    "country": "Poland"
  },
  "cars": [
    {
      "car": "Sentra",
      "make": "Nissan",
      "year": 1992
    },
    {
      "car": "Q5",
      "make": "Audi",
      "year": 2011
    }
  ]
}
```

## Output
```json
{
  "type": "object",
  "properties": {
    "gender": {
      "type": "string",
      "examples": [
        "Male"
      ]
    },
    "last_name": {
      "type": "string",
      "examples": [
        "Muldoon"
      ]
    },
    "email": {
      "type": "string",
      "examples": [
        "umuldoon0@baidu.com"
      ]
    },
    "first_name": {
      "type": "string",
      "examples": [
        "Ulrich"
      ]
    },
    "cars": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "make": {
            "type": "string",
            "examples": [
              "Nissan"
            ]
          },
          "year": {
            "type": "integer",
            "examples": [
              1992
            ]
          },
          "car": {
            "type": "string",
            "examples": [
              "Sentra"
            ]
          }
        }
      }
    },
    "location": {
      "type": "object",
      "properties": {
        "country": {
          "type": "string",
          "examples": [
            "Poland"
          ]
        }
      }
    }
  }
}
```
