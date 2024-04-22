# AWS::IAM::Role Policy validation

method to verify the input JSON data.

## Dependencies
- junit
- org.json
- everit-json-schema

## Build
Program was write with `Open JDK 21` and build with `gradle 8.2`

## Struct
```java
public class PolicyJSONValidator {
    public static boolean validate(String pathToInputFile) {
        /* returns false if input file contains "*", else true */
    }
    
    /* ... */
}
```
Schema to validate JSON input is in `src/main/resources` directory.

## Tests
4 unit tests have been written using junit framework.

