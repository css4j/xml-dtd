/*
 *
 * Copyright (c) 2005-2025, Carlos Amengual.
 *
 * Licensed under a BSD-style License. You can find the license here:
 * https://css4j.github.io/LICENSE.txt
 *
 */
/*
  SPDX-License-Identifier: BSD-3-Clause OR BSD-2-Clause OR LGPL-2.1-or-later OR
   GPL-2.0-or-later
 */

/**
 * DTD-related helper classes for XML parsing.
 */
module io.sf.carte.xml.dtd {
	exports io.sf.carte.doc.xml.dtd;

	requires static io.sf.carte.util;
	requires static io.sf.carte.tokenproducer;
	requires static io.sf.jclf.text;
	requires transitive java.xml;
}
