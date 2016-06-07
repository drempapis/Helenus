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

package com.proemion.test.cli;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link App}.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public final class AppTest {

    /**
     * JUnit rule for expected exception.
     */
    @Rule
    public final transient ExpectedException thrown = ExpectedException.none();

    /**
     * Main can throw on missing Cassandra host.
     * @throws Exception On failure
     */
    @Test
    public void mainThrowsOnMissingCassandraHost() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Argument cassandra_host missing!");
        App.main();
    }

    /**
     * Main can throw on missing migrations directory.
     * @throws Exception On failure
     */
    @Test
    public void mainThrowsOnMissingMigrationsDir() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Argument migration_package missing!");
        App.main("--cassandra_host", "somehost");
    }

}
