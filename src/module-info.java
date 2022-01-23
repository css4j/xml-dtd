/*
 *
 * Copyright (c) 2005-2022, Carlos Amengual.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 *
 * Licensed under a BSD-style License. You can find the license here:
 * https://css4j.github.io/LICENSE.txt
 *
 */
module io.sf.carte.xml.dtd {
	exports io.sf.carte.doc.xml.dtd;

	requires static io.sf.carte.util;
	requires static io.sf.carte.tokenproducer;
	requires static io.sf.jclf.text;
	requires transitive java.xml;
}
