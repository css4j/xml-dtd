# xml-dtd version 4.1.0 Release Notes

### August 19, 2022

<br/>

## Highlights

### Use the HTML5 entities as fallback if no subset is found in getExternalSubset

See issue #3.

### Do not assume a specific SVG version for <svg> in getExternalSubset

See issue #5.

### Support the DTD for SVG 1.0 in DefaultEntityResolver

See issue #6.

## Detail of changes

- Use the HTML5 entities as fallback if no subset is found in getExternalSubset (#3)
- Do not assume a specific SVG version for <svg> in getExternalSubset (#5)
- Support the DTD for SVG 1.0 in DefaultEntityResolver (#6)
- ContentModel: more efficient use of collections.
- Make inner class static in EntityFinder.
- DefaultEntityResolver: formatting.
- A few javadoc improvements.
- Gradle: upgrade wrapper to 7.5.1.
- Bump year to 2022 in copyright headers.
