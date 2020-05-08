/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved. DO NOT
 * ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
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
package zone.lamprey.function;

import java.util.Objects;

/**
 * Represents an operation that accepts an object-valued and a
 * {@code long}-valued argument, and returns no result. This is the
 * {@code (reference, long)} specialization of {@link BiConsumer}. Unlike most
 * other functional interfaces, {@code ObjLongConsumer} is expected to operate
 * via side-effects.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #accept(Object, long)}.
 *
 * @param <T> the type of the object argument to the operation
 *
 * @see BiConsumer
 * @since 1.8
 */
@FunctionalInterface
public interface ObjLongConsumer<T> extends BiConsumer<T, Long>, java.util.function.ObjLongConsumer<T> {
    
    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param value the second input argument
     */
    @Override
    void accept(T t, long value);
    
    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     */
    @Override
    default void accept(T t, Long u) {
        accept(t, u.longValue());
    }
    
    /**
     * Returns a composed {@code BiConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code BiConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    @Override
    default ObjLongConsumer<T> andThen(BiConsumer<? super T, ? super Long> after) {
        Objects.requireNonNull(after);
        
        return (l, r) -> {
            accept(l, r);
            after.accept(l, r);
        };
    }
    
    /**
     * Partially apply a parameter such that a single param consumer becomes a
     * no-param runnable.
     * 
     * @param t The first parameter to apply.
     * @param u The second parameter to apply.
     * @return A partially-applied function.
     */
    default Runnable applyPartial(T t, long u) {
        return () -> accept(t, u);
    }
    
    /**
     * Performs a partial application, resulting in a consumer that calls this with
     * its argument and the argument given here.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default LongConsumer applyPartialL(T t) {
        return u -> accept(t, u);
    }
    
    /**
     * Performs a partial application, resulting in a consumer that calls this with
     * its argument and the argument given here.
     *
     * @param u the function argument
     * @return the function result
     */
    default Consumer<T> applyPartialR(long u) {
        return t -> accept(t, u);
    }
}
