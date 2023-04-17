# xml-dtd version 4.2 Release Notes

### April 17, 2023

<br/>

## Highlights

This release is the exact same as 4.1.1, except that it depends on Tokenproducer 2.0.1.

### Upgrade to Tokenproducer 2.0.1

Tokenproducer 2.0.1 introduces a new base interface but otherwise is the same as 1.2
(and is source-level compatible with it). Unfortunately the software compiled with 1.2
cannot figure out that the old `TokenHandler` interface inherits from the new `TokenHandler2`,
so anything compiled with 1.x is incompatible at runtime with 2.x.

If you upgrade to css4j 4.0.1, make sure to upgrade to `xml-dtd` 4.2 as well.

<br/>

## Detail of changes

- Bump copyright year to 2023.
- Tests: convert the tests to JUnit 5.
- Tests: add a jar bomb test.
- Upgrade to TokenProducer 2.0.1
- Upgrade Gradle wrapper to 8.1.
