/*

 Copyright (c) 1998-2024, Carlos Amengual.

 SPDX-License-Identifier: BSD-3-Clause

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */

package io.sf.carte.doc.xml.dtd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.Reader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.EntityResolver2;

public class StackedEntityResolverTest {

	private static EntityResolver2 stackedResolver;

	@BeforeAll
	public static void classFixture() {
		EntityResolver2 firstResolver = new NullEntityResolver();
		EntityResolver2 fallbackResolver = new DefaultEntityResolver();
		stackedResolver = new StackedEntityResolver(firstResolver, fallbackResolver);
	}

	@Test
	public void getExternalSubsetStringString() throws SAXException, IOException {
		InputSource isrc = stackedResolver.getExternalSubset("html", null);
		assertNotNull(isrc);
		assertNull(isrc.getPublicId());
		assertNull(isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void getExternalSubsetStringStringUnknownSubset() throws SAXException, IOException {
		InputSource isrc = stackedResolver.getExternalSubset("foo", null);
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
		InputSource isrc = stackedResolver.resolveEntity(DocumentTypeDeclaration.XHTML1_TRA_PUBLICID,
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
		InputSource isrc = stackedResolver.resolveEntity(null,
				"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd");
		assertNotNull(isrc);
		assertEquals("-//W3C//DTD XHTML 1.0 Transitional//EN", isrc.getPublicId());
		assertEquals("http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd", isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void resolveEntityStringStringXHTML11() throws SAXException, IOException {
		InputSource isrc = stackedResolver.resolveEntity("-//W3C//DTD XHTML 1.1//EN", null);
		assertNotNull(isrc);
		assertEquals("-//W3C//DTD XHTML 1.1//EN", isrc.getPublicId());
		assertEquals("http://www.w3.org/MarkUp/DTD/xhtml11.dtd", isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void resolveEntityStringStringXHTML11Meta() throws SAXException, IOException {
		InputSource isrc = stackedResolver.resolveEntity(null, "http://www.w3.org/MarkUp/DTD/xhtml-meta-1.mod");
		assertNotNull(isrc);
		assertEquals("http://www.w3.org/MarkUp/DTD/xhtml-meta-1.mod", isrc.getSystemId());
		Reader re = isrc.getCharacterStream();
		assertNotNull(re);
		re.close();
	}

	@Test
	public void resolveEntityStringStringRemoteDisallow() throws SAXException, IOException {
		try {
			stackedResolver.resolveEntity("-//OASIS//DTD DocBook XML V4.5//EN",
					"http://www.oasis-open.org/docbook/xml/4.5/docbookx.dtd");
			fail("Must throw exception");
		} catch (SAXException e) {
		} catch (IOException e) {
			fail("Should throw SAXException, not IOException");
		}
	}

	@Test
	public void resolveEntityStringStringRemoteDisallowConstructor1Arg() throws SAXException, IOException {
		try {
			stackedResolver.resolveEntity("-//W3C//DTD SVG 0.9//EN",
					"https://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd");
			fail("Must throw exception");
		} catch (SAXException e) {
		} catch (IOException e) {
			fail("Should throw SAXException, not IOException");
		}
	}

	@Test
	public void resolveEntityStringStringBadSystemId() throws SAXException, IOException {
		try {
			stackedResolver.resolveEntity(null, "foo:");
			fail("Must throw exception");
		} catch (SAXException e) {
			fail("Should throw IOException, not SAXException");
		} catch (IOException e) {
		}
	}

	@Test
	public void resolveNonexistent() {
		InputSource isrc = null;
		try {
			isrc = stackedResolver.resolveEntity("hi", null);
		} catch (Exception e) {
			fail("Should return null, not thow an Exception" + e.getLocalizedMessage());
		}
		assertNull(isrc);
	}

}
