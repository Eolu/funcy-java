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

/**
 * Represents an operation upon two {@code int}-valued operands and producing an
 * {@code int}-valued result. This is the primitive type specialization of
 * {@link BinaryOperator} for {@code int}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #applyAsInt(int, int)}.
 *
 * @see BinaryOperator
 * @see IntUnaryOperator
 * @since 1.8
 */
@FunctionalInterface
public interface IntBinaryOperator extends BinaryOperator<Integer>, java.util.function.IntBinaryOperator {
    
    /**
     * Applies this operator to the given operands.
     *
     * @param left the first operand
     * @param right the second operand
     * @return the operator result
     */
    @Override
    int applyAsInt(int left, int right);
    
    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    @Override
    default Integer apply(Integer t, Integer u) {
        return applyAsInt(t.intValue(), u.intValue());
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    default IntSupplier applyPartial(int t, int u) {
        return () -> applyAsInt(t, u);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the function argument
     * @return the function result
     */
    default IntUnaryOperator applyPartialL(int t) {
        return u -> applyAsInt(t, u);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param u the function argument
     * @return the function result
     */
    default IntUnaryOperator applyPartialR(int u) {
        return t -> applyAsInt(t, u);
    }
    
    /**
     * Consume a function.
     * 
     * @param consumer The consumer to use in consuming.
     * @return A Consumer which passes it's argument to this function and then
     *         passes the result into the given consumer.
     */
    default ObjIntConsumer<Integer> consume(IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.accept(applyAsInt(t, u));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default IntBinaryOperator map(IntUnaryOperator functor) {
        Objects.requireNonNull(functor);
        return (t, u) -> functor.applyAsInt(applyAsInt(t, u));
    }
}
