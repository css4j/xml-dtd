/*

 Copyright (c) 1998-2026, Carlos Amengual.

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */
/*
  SPDX-License-Identifier: BSD-3-Clause OR BSD-2-Clause OR LGPL-2.1-or-later OR
   GPL-2.0-with-classpath-exception
 */

package io.sf.carte.doc.xml.dtd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringReader;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.junit.jupiter.api.Test;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Check if dom4j can parse and validate using the resolver.
 */
public class DOM4JResolverTest {

	@Test
	public void read() throws SAXException, DocumentException, IOException {
		SAXReader reader = new SAXReader();
		reader.setEntityResolver(new DefaultEntityResolver());

		StringReader re = new StringReader(
				"<!DOCTYPE html SYSTEM \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
						+ "<html><head/><body><p id=\"para\">a&eacute;i</p></body></html>");
		InputSource is = new InputSource(re);

		org.dom4j.Document document = reader.read(is);

		re.close();

		Element docElm = document.getRootElement();
		Element body = docElm.element("body");
		Element p = body.element("p");
		String text = p.getText();

		assertEquals("ai", text);
	}

	@Test
	public void validate() throws SAXException, DocumentException, IOException {
		SAXValidator validator = new SAXValidator();
		StringReader re = new StringReader(
				"<!DOCTYPE html SYSTEM \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
						+ "<foo>a&eacute;i</foo>");
		InputSource is = new InputSource(re);
		org.dom4j.Document document = new SAXReader().read(is);
		ErrorHandler errorHandler = new ErrorHandler() {

			@Override
			public void warning(SAXParseException exception) throws SAXException {
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				throw exception;
			}

			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				throw exception;
			}

		};
		validator.setErrorHandler(errorHandler);
		validator.getXMLReader().setEntityResolver(new DefaultEntityResolver());
		SAXParseException e = assertThrows(SAXParseException.class,
				() -> validator.validate(document));
		assertEquals(80, e.getColumnNumber());
		re.close();
	}

}
