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
      "year": 1000
    }
  ],
  "hobbies": [
    "travel",
    "cycling"
  ]
}
```

## Output
```json
{
  "type": "object",
  "properties": {
    "last_name": {
      "type": "string",
      "examples": [
        "Muldoon"
      ]
    },
    "hobbies": {
      "type": "array",
      "items": {
        "type": "string",
        "examples": [
          "travel",
          "cycling"
        ]
      }
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
    "location": {
      "type": "object",
      "properties": {
        "country": {
          "type": "string",
          "examples": [
            "Poland"
          ]
        },
        "city": {
          "type": "string",
          "examples": [
            "Lipka"
          ]
        }
      }
    },
    "gender": {
      "type": "string",
      "examples": [
        "Male"
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
              "Nissan",
              "Audi"
            ]
          },
          "year": {
            "type": "integer",
            "examples": [
              1992,
              1000
            ]
          },
          "car": {
            "type": "string",
            "examples": [
              "Sentra",
              "Q5"
            ]
          }
        }
      }
    }
  }
}
```
