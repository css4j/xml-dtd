/*

 Copyright (c) 1998-2023, Carlos Amengual.

 SPDX-License-Identifier: BSD-3-Clause

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */

package io.sf.carte.doc.xml.dtd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DefaultEntityResolverTest {
	private static DefaultEntityResolver resolver;

	@BeforeClass
	public static void classFixture() {
		resolver = new DefaultEntityResolver();
	}

	@Test
	public void getExternalSubsetStringString() throws SAXException, IOException {
		InputSource isrc = resolver.getExternalSubset("html", null);
		assertNotNull(isrc);
		assertNull(isrc.getPublicId());
		assertNull(isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
		// SVG
		isrc = resolver.getExternalSubset("svg", null);
		assertNotNull(isrc);
		assertNull(isrc.getPublicId());
		assertNull(isrc.getSystemId());
		re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void getExternalSubsetStringStringUnknownSubset() throws SAXException, IOException {
		InputSource isrc = resolver.getExternalSubset("foo", null);
		assertNotNull(isrc);
		assertNull(isrc.getPublicId());
		assertNull(isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		char[] cbuf = new char[40];
		try {
			re.read(cbuf);
		} finally {
			re.close();
		}
		String sbuf = new String(cbuf);
		assertEquals("<!ENTITY Tab \"&#x9;\"><!ENTITY NewLine \"&", sbuf);
	}

	@Test
	public void resolveEntityStringString() throws SAXException, IOException {
		InputSource isrc = resolver.resolveEntity(DocumentTypeDeclaration.XHTML1_TRA_PUBLICID,
				"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd");
		assertNotNull(isrc);
		assertNotNull(isrc.getPublicId());
		assertEquals(DocumentTypeDeclaration.XHTML1_TRA_PUBLICID, isrc.getPublicId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void resolveEntityStringString2() throws SAXException, IOException {
		InputSource isrc = resolver.resolveEntity(null, "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd");
		assertNotNull(isrc);
		assertEquals("-//W3C//DTD XHTML 1.0 Transitional//EN", isrc.getPublicId());
		assertEquals("http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd", isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void resolveEntityStringStringXHTML11() throws SAXException, IOException {
		InputSource isrc = resolver.resolveEntity("-//W3C//DTD XHTML 1.1//EN", null);
		assertNotNull(isrc);
		assertEquals("-//W3C//DTD XHTML 1.1//EN", isrc.getPublicId());
		assertEquals("http://www.w3.org/MarkUp/DTD/xhtml11.dtd", isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void resolveEntityStringStringXHTML11SystemID() throws Exception {
		String xhtml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" "
			+ "\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n"
			+ "<html><body><p>hello</p></body></html>";

		DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docb = dbFac.newDocumentBuilder();
		docb.setEntityResolver(new DefaultEntityResolver());
		InputSource source = new InputSource(new StringReader(xhtml));
		Document doc = docb.parse(source);
		assertNotNull(doc);
	}

	@Test
	public void resolveEntityStringStringXHTML11Meta() throws SAXException, IOException {
		InputSource isrc = resolver.resolveEntity(null, "http://www.w3.org/MarkUp/DTD/xhtml-meta-1.mod");
		assertNotNull(isrc);
		assertEquals("http://www.w3.org/MarkUp/DTD/xhtml-meta-1.mod", isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void resolveEntityStringStringSVG11() throws SAXException, IOException {
		InputSource isrc = resolver.resolveEntity("-//W3C//DTD SVG 1.1//EN", null);
		assertNotNull(isrc);
		assertEquals("-//W3C//DTD SVG 1.1//EN", isrc.getPublicId());
		assertEquals("http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd", isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void resolveEntityStringStringSVG10() throws SAXException, IOException {
		InputSource isrc = resolver.resolveEntity("-//W3C//DTD SVG 1.0//EN", null);
		assertNotNull(isrc);
		assertEquals("-//W3C//DTD SVG 1.0//EN", isrc.getPublicId());
		assertEquals("http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd",
			isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void resolveEntityStringStringRemoteDisallow() throws SAXException, IOException {
		assertThrows(SAXException.class,
			() -> resolver.resolveEntity("-//OASIS//DTD DocBook XML V4.5//EN",
				"http://www.oasis-open.org/docbook/xml/4.5/docbookx.dtd"));
	}

	@Test
	public void resolveEntityStringStringRemoteDisallowConstructor1Arg()
		throws SAXException, IOException {
		assertThrows(SAXException.class, () -> resolver.resolveEntity("-//W3C//DTD SVG 1.1//EN",
			"https://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd"));
	}

	@Test
	public void resolveEntityStringStringRemote() throws SAXException, IOException {
		if (TestConfig.REMOTE_TESTS) {
			resolver.addHostToWhiteList("www.oasis-open.org");
			InputSource isrc = resolver.resolveEntity("-//OASIS//DTD DocBook XML V4.5//EN",
				"http://www.oasis-open.org/docbook/xml/4.5/docbookx.dtd");
			assertNotNull(isrc);
			assertEquals("-//OASIS//DTD DocBook XML V4.5//EN", isrc.getPublicId());
			assertEquals("http://www.oasis-open.org/docbook/xml/4.5/docbookx.dtd",
				isrc.getSystemId());
			Reader re = isrc.getCharacterStream();
			assertNotNull(re);
			re.close();
			//
			resolver.addHostToWhiteList("css4j.github.io");

			assertThrows(SAXException.class,
				() -> resolver.resolveEntity(null, "https://css4j.github.io/"));

			assertThrows(SAXException.class,
				() -> resolver.resolveEntity(null, "https://css4j.github.io/faq.html"));

			assertThrows(SAXException.class,
				() -> resolver.resolveEntity(null, "https://css4j.github.io/foo/badurl"));

			assertThrows(FileNotFoundException.class,
				() -> resolver.resolveEntity(null, "https://css4j.github.io/foo/badurl.dtd"));
		}
	}

	@Test
	public void parseAndResolveEntity() throws SAXException, IOException {
		DocumentTypeDeclaration dtDecl = DocumentTypeDeclaration.parse(DocumentTypeDeclaration.XHTML1_TRA_DTDECL);
		InputSource isrc = resolver.resolveEntity(dtDecl.getName(), dtDecl.getPublicId(), null, dtDecl.getSystemId());
		assertNotNull(isrc);
		assertNotNull(isrc.getPublicId());
		assertEquals(DocumentTypeDeclaration.XHTML1_TRA_PUBLICID, isrc.getPublicId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void resolveNonexistent() {
		// No SystemId, so it should resolve to null
		InputSource isrc = null;
		try {
			isrc = resolver.resolveEntity("hi", null);
		} catch (Exception e) {
			fail("Should return null, not thow an Exception" + e.getLocalizedMessage());
		}
		assertNull(isrc);
	}

	@Test
	public void resolveJarBomb() {
		assertThrows(SAXException.class, () -> resolver.resolveEntity("-//W3C//DTD FOO 1.0//EN",
			"jar:http://www.example.com/evil.jar!/file.dtd"));
	}

	@Test
	public void testRegisterSystemIdFilename() throws SAXException, IOException {
		assertTrue(resolver.registerSystemIdFilename("http://example.com/dtd/sample.dtd",
				"/io/sf/carte/doc/xml/dtd/sample.dtd"));
		InputSource is = resolver.resolveEntity(null, "http://example.com/dtd/sample.dtd");
		assertNotNull(is);
		is.getCharacterStream().close();
	}

	@Test
	public void testRegisterNonExistentPathFromSubclass() throws SAXException, IOException {
		resolver.registerSystemIdFilename("http://www.example.com/some.dtd", "/dtd/example.dtd");
		assertThrows(SAXException.class,
			() -> resolver.resolveEntity(null, "http://www.example.com/some.dtd"));
		classFixture(); // reset the resolver.
	}

	@Test
	public void testRegisterInvalidPathFromSubclass() throws SAXException, IOException {
		assertThrows(NullPointerException.class,
			() -> resolver.registerSystemIdFilename("http://www.example.com/bad.dtd", null));

		assertThrows(NullPointerException.class,
			() -> resolver.registerSystemIdFilename(null, "/some/path"));

		assertThrows(IllegalArgumentException.class,
			() -> resolver.registerSystemIdFilename("http://www.example.com/bad.dtd", ""));

		assertThrows(IllegalArgumentException.class,
			() -> resolver.registerSystemIdFilename("http://www.example.com/bad.dtd",
				"/path/to/confidential/stuff"));
	}

	@Test
	public void testIsInvalidPath() throws SAXException, IOException {
		assertTrue(resolver.isInvalidPath(new URL("http://dtd.example.com/etc/passwd").getPath()));
		assertTrue(resolver.isInvalidPath(new URL("http://dtd.example.com/etc/passwd#fake.dtd").getPath()));
		assertFalse(resolver.isInvalidPath(new URL("http://foo.example.com/bar.dtd").getPath()));
		assertTrue(resolver.isInvalidPath(new URL("http://dtd.example.com/etc/passwd").getPath()));
	}

	@Test
	public void testIsValidContentType() throws SAXException, IOException {
		assertTrue(resolver.isValidContentType("application/xml-dtd"));
		assertTrue(resolver.isValidContentType("application/xml-external-parsed-entity"));
		assertTrue(resolver.isValidContentType("text/xml-external-parsed-entity"));
		assertFalse(resolver.isValidContentType("text/html"));
		assertFalse(resolver.isValidContentType(null));
	}

}
