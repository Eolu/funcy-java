/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published by
 * the Free Software Foundation. Oracle designates this particular file as
 * subject to the "Classpath" exception as provided by Oracle in the LICENSE
 * file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License version 2 for more
 * details (a copy is included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version 2
 * along with this work; if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA or
 * visit www.oracle.com if you need additional information or have any
 * questions.
 */
package eolu.util.function;

/**
 * Represents a function that accepts an int-valued argument and produces a
 * long-valued result. This is the {@code int}-to-{@code long} primitive
 * specialization for {@link Function}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #applyAsLong(int)}.
 *
 * @see Function
 * @since 1.8
 */
@FunctionalInterface
public interface IntToLongFunction extends IntFunction<Long>, ToLongFunction<Integer>, java.util.function.IntToLongFunction {
    
    /**
     * Cast an int to a long.
     */
    public static final IntToLongFunction CAST = i -> (long) i;
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    long applyAsLong(int value);
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default long applyAsLong(Integer value) {
        return applyAsLong(value.intValue());
    }
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default Long apply(int value) {
        return applyAsLong(value);
    }
    
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Long apply(Integer t) {
        return applyAsLong(t.intValue());
    }
}
