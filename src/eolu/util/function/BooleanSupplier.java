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

import eolu.util.incomplete.DoubleSupplier;
import eolu.util.incomplete.Function;
import eolu.util.incomplete.IntSupplier;
import eolu.util.incomplete.LongSupplier;
import eolu.util.incomplete.Predicate;
import eolu.util.incomplete.Supplier;
import eolu.util.incomplete.ToDoubleFunction;
import eolu.util.incomplete.ToIntFunction;
import eolu.util.incomplete.ToLongFunction;

/**
 * Represents a supplier of {@code boolean}-valued results. This is the
 * {@code boolean}-producing primitive specialization of {@link Supplier}.
 *
 * <p>
 * There is no requirement that a new or distinct result be returned each time
 * the supplier is invoked.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #getAsBoolean()}.
 *
 * @see Supplier
 * @since 1.8
 */
@FunctionalInterface
public interface BooleanSupplier extends Supplier<Boolean> {
    
    /**
     * Gets a result.
     *
     * @return a result
     */
    boolean getAsBoolean();
    
    /**
     * @see java.util.function.Supplier#get()
     */
    @Override
    default Boolean get() {
        return getAsBoolean();
    }
    
    /**
     * Lift a supplier.
     * 
     * @param <R> The return type.
     * @param functor The function to use in lifting.
     * @return A supplier that passes the result of fn through a functor to produce
     *         a lifted supplier.
     */
    default <R> Supplier<R> lift(Function<Boolean, R> functor) {
        return () -> functor.apply(getAsBoolean());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default <T> BooleanSupplier lift(Predicate<Boolean> functor) {
        return () -> functor.test(getAsBoolean());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default DoubleSupplier lift(ToDoubleFunction<Boolean> functor) {
        return () -> functor.applyAsDouble(getAsBoolean());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default IntSupplier lift(ToIntFunction<Boolean> functor) {
        return () -> functor.applyAsInt(getAsBoolean());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default LongSupplier lift(ToLongFunction<Boolean> functor) {
        return () -> functor.applyAsLong(getAsBoolean());
    }
}
