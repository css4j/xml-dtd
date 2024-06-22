/*

 Copyright (c) 2005-2024, Carlos Amengual.

 SPDX-License-Identifier: BSD-3-Clause

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */

package io.sf.carte.doc.xml.dtd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import org.xml.sax.ext.EntityResolver2;

public class EntityFinderTest {

	private static EntityResolver2 resolver;
	private static EntityFinder finder;

	@BeforeAll
	public static void classFixture() {
		resolver = new DefaultEntityResolver();
		finder = new EntityFinder(resolver);
	}

	@Test
	public void testFindEntities() throws SAXException, IOException {
		HashMap<Integer, String> cp2e = new HashMap<>();
		cp2e.put(237, null);
		Reader re = resolver.resolveEntity("-//W3C//DTD XHTML 1.0 Transitional//EN",
				"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd").getCharacterStream();
		assertEquals(1, finder.findEntities(cp2e, re));
		re.close();
		assertEquals("iacute", cp2e.get(237));
	}

}
