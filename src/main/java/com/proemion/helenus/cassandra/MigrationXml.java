/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Proemion GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.proemion.helenus.cassandra;

import com.jcabi.xml.XML;
import lombok.EqualsAndHashCode;

/**
 * Migration read from XML file.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode
public final class MigrationXml implements Migration {

    /**
     * Xml from which to read.
     */
    private final XML data;

    /**
     * Ctor.
     * @param xml Xml to read
     */
    public MigrationXml(final XML xml) {
        this.data = xml;
    }

    @Override
    public void run() {
        //missing implementation.
    }

    @Override
    public Long identifier() {
        return Long.valueOf(
            this.data.xpath("/migration/identifier/text()").iterator().next()
        );
    }

    @Override
    public int compareTo(final Migration other) {
        return this.identifier().compareTo(other.identifier());
    }
}