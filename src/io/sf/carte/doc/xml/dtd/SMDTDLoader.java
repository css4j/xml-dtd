/*

 Copyright (c) 1998-2021, Carlos Amengual.

 SPDX-License-Identifier: BSD-3-Clause

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */

package io.sf.carte.doc.xml.dtd;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.PrivilegedActionException;

import io.sf.carte.doc.xml.dtd.DefaultEntityResolver.DTDLoader;

/**
 * Load DTDs using the privileges granted by a {@code SecurityManager}.
 * <p>
 * This class uses deprecated methods.
 * </p>
 */
class SMDTDLoader extends DTDLoader {

	public SMDTDLoader() {
		super();
	}

	@SuppressWarnings({ "deprecation", "removal" })
	@Override
	void connect(final URLConnection con) throws IOException {
		try {
			java.security.AccessController.doPrivileged(new java.security.PrivilegedExceptionAction<Void>() {
				@Override
				public Void run() throws IOException {
					con.connect();
					return null;
				}
			});
		} catch (PrivilegedActionException e) {
			throw (IOException) e.getException();
		}
	}

	@Override
	Reader loadDTDfromClasspath(final ClassLoader loader, final String dtdFilename) {
		@SuppressWarnings({ "deprecation", "removal" })
		InputStream is = java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<InputStream>() {
			@Override
			public InputStream run() {
				InputStream is;
				if (loader != null) {
					is = loader.getResourceAsStream(dtdFilename);
				} else {
					is = DefaultEntityResolver.class.getResourceAsStream(dtdFilename);
				}
				if (is == null) {
					is = ClassLoader.getSystemResourceAsStream(dtdFilename);
				}
				return is;
			}
		});
		Reader re = null;
		if (is != null) {
			re = new InputStreamReader(is, StandardCharsets.UTF_8);
		}
		return re;
	}

}
