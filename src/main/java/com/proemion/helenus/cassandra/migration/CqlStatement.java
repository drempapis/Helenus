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
package com.proemion.helenus.cassandra.migration;

/**
 * Cql Statement.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public interface CqlStatement {

    /**
     * Returns the CQL to run.
     * @return CQL statement to run.
     */
    String migrate();

    /**
     * Returns the CQL to revert this statement.
     * @return Reverse CQL statement
     */
    String revert();

    /**
     * Default CQL statement implementation.
     */
    class Default implements CqlStatement {

        /**
         * CQL in the migrate direction.
         */
        private final String migration;

        /**
         * CQL in the revert direction.
         */
        private final String reversal;

        /**
         * Ctor.
         * @param upwards CQL in the migrate direction
         * @param downwards CQL in the revert direction
         */
        public Default(final String upwards, final String downwards) {
            this.migration = upwards;
            this.reversal = downwards;
        }

        @Override
        public String migrate() {
            return this.migration;
        }

        @Override
        public String revert() {
            return this.reversal;
        }
    }
}
