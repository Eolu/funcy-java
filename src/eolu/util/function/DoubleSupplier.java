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

import java.util.Objects;

import eolu.util.incomplete.DoublePredicate;
import eolu.util.incomplete.DoubleToIntFunction;
import eolu.util.incomplete.DoubleToLongFunction;
import eolu.util.incomplete.DoubleUnaryOperator;
import eolu.util.incomplete.IntSupplier;
import eolu.util.incomplete.LongSupplier;

/**
 * Represents a supplier of {@code double}-valued results. This is the
 * {@code double}-producing primitive specialization of {@link Supplier}.
 *
 * <p>
 * There is no requirement that a distinct result be returned each time the
 * supplier is invoked.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #getAsDouble()}.
 *
 * @see Supplier
 * @since 1.8
 */
@FunctionalInterface
public interface DoubleSupplier extends Supplier<Double> {
    
    /**
     * Gets a result.
     *
     * @return a result
     */
    double getAsDouble();
    
    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    default Double get() {
        return getAsDouble();
    }
    
    /**
     * Lift a supplier.
     * 
     * @param <R> The new return type.
     * @param fn The supplier to lift.
     * @param functor The function to use in lifting.
     * @return A supplier that passes the result of fn through a functor to produce
     *         a lifted supplier.
     */
    default <R> Supplier<R> map(DoubleFunction<R> functor) {
        Objects.requireNonNull(functor);
        return () -> functor.apply(getAsDouble());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default BooleanSupplier map(DoublePredicate functor) {
        Objects.requireNonNull(functor);
        return () -> functor.test(getAsDouble());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default DoubleSupplier map(DoubleUnaryOperator functor) {
        Objects.requireNonNull(functor);
        return () -> functor.applyAsDouble(getAsDouble());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default IntSupplier map(DoubleToIntFunction functor) {
        Objects.requireNonNull(functor);
        return () -> functor.applyAsInt(getAsDouble());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default LongSupplier map(DoubleToLongFunction functor) {
        Objects.requireNonNull(functor);
        return () -> functor.applyAsLong(getAsDouble());
    }
}