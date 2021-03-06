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
package zone.lamprey.function;

import java.util.Objects;

/**
 * Represents a supplier of {@code long}-valued results. This is the
 * {@code long}-producing primitive specialization of {@link Supplier}.
 *
 * <p>
 * There is no requirement that a distinct result be returned each time the
 * supplier is invoked.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #getAsLong()}.
 *
 * @see Supplier
 * @since 1.8
 */
@FunctionalInterface
public interface LongSupplier extends Supplier<Long>, java.util.function.LongSupplier {
    
    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    long getAsLong();
    
    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    default Long get() {
        return getAsLong();
    }
    
    /**
     * Consume a supplier.
     * 
     * @param consumer The consumer to use in consuming.
     * @return A Runnable which passes the result of this supplier into the given
     *         consumer when run.
     */
    default Runnable consume(LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return () -> consumer.accept(getAsLong());
    }
    
    /**
     * Lift a supplier.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default LongSupplier map(LongUnaryOperator functor) {
        Objects.requireNonNull(functor);
        return () -> functor.applyAsLong(getAsLong());
    }
    
    /**
     * Lift a supplier.
     * 
     * @param <R> The new return type.
     * @param functor The function to use in lifting.
     * @return A supplier that passes the result of fn through a functor to produce
     *         a lifted supplier.
     */
    default <R> Supplier<R> mapToObj(LongFunction<R> functor) {
        Objects.requireNonNull(functor);
        return () -> functor.apply(getAsLong());
    }
    
    /**
     * Lift a supplier.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default BooleanSupplier mapToPredicate(LongPredicate functor) {
        Objects.requireNonNull(functor);
        return () -> functor.test(getAsLong());
    }
    
    /**
     * Lift a supplier.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default IntSupplier mapToInt(LongToIntFunction functor) {
        Objects.requireNonNull(functor);
        return () -> functor.applyAsInt(getAsLong());
    }
    
    /**
     * Lift a supplier.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default DoubleSupplier mapToDouble(LongToDoubleFunction functor) {
        Objects.requireNonNull(functor);
        return () -> functor.applyAsDouble(getAsLong());
    }
}
