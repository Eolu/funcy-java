/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
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
 * Represents an operation upon three operands of the same type, producing a
 * result of the same type as the operands. This is a specialization of
 * {@link TriFunction} for the case where the operands and the result are all of
 * the same type.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #apply(Object, Object, Object)}.
 *
 * @param <T> the type of the operands and result of the operator
 *
 * @see TryFunction
 * @see BinaryOperator
 * @since 1.8
 */
@FunctionalInterface
public interface TernaryOperator<T> extends TriFunction<T, T, T, T> {
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @param v the third function argument
     * @return the function result
     */
    @Override
    default Supplier<T> applyPartial(T t, T u, T v) {
        return () -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    @Override
    default UnaryOperator<T> applyPartialLM(T t, T u) {
        return v -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param v the third function argument
     * @return the function result
     */
    @Override
    default UnaryOperator<T> applyPartialLR(T t, T v) {
        return u -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param u the second function argument
     * @param v the third function argument
     * @return the function result
     */
    @Override
    default UnaryOperator<T> applyPartialMR(T u, T v) {
        return t -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @return the function result
     */
    @Override
    default BinaryOperator<T> applyPartialL(T t) {
        return (u, v) -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param u the second function argument
     * @return the function result
     */
    @Override
    default BinaryOperator<T> applyPartialM(T u) {
        return (t, v) -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param v the third function argument
     * @return the function result
     */
    @Override
    default BinaryOperator<T> applyPartialR(T v) {
        return (t, u) -> apply(t, u, v);
    }
}
