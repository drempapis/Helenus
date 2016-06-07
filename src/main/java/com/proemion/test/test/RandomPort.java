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

package com.proemion.test.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Reserves a random port on the loopback interface.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public final class RandomPort {

    /**
     * Ctor.
     */
    private RandomPort() {
        //Utility only.
    }

    /**
     * Reserves a random port on the loopback interface.
     * @return Port
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    public static int reserve() {
        synchronized (RandomPort.class) {
            try (ServerSocket socket = new ServerSocket()) {
                socket.setReuseAddress(true);
                socket.bind(
                    new InetSocketAddress(InetAddress.getLoopbackAddress(), 0)
                );
                return socket.getLocalPort();
            } catch (final IOException ex) {
                throw new IllegalStateException(ex);
            }
        }
    }

}
