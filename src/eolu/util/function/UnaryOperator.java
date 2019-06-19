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
 * Represents an operation on a single operand that produces a result of the
 * same type as its operand. This is a specialization of {@code Function} for
 * the case where the operand and result are of the same type.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the operand and result of the operator
 *
 * @see Function
 * @since 1.8
 */
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default UnaryOperator<T> map(UnaryOperator<T> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.apply(apply(t));
    }
    
    /**
     * A method that tells a function to call itself with its own result. This
     * actually imitates recursion using iteration. Don't tell anyone.
     * 
     * @param value The initial value to apply to the function.
     * @param depth The number of times to recurse. A depth of zero or less will
     *            simply return the passed-in value (the function will never be
     *            called).
     * @return The result of the recursive call.
     */
    default T recurse(T value, int depth) {
        for (int i = 0; i < depth; i++)
            value = apply(value);
        return value;
    }
    
    /**
     * A method that tells a function to call itself with its own result. This
     * actually imitates recursion using iteration. Don't tell anyone.
     * 
     * @param value The initial value to apply to the function.
     * @param terminalCondition The condition upon which the "recursion" will
     *            terminate.
     * @return The result of the recursive call.
     */
    default T recurse(T value, Predicate<T> terminalCondition) {
        while (terminalCondition.test(value))
            value = apply(value);
        return value;
    }
    
    /**
     * A method that tells a function to call itself with its own result. This
     * actually imitates recursion using iteration. Don't tell anyone.
     * 
     * @param value The initial value to apply to the function.
     * @param terminalCondition The condition upon which the "recursion" will
     *            terminate.
     * @param maxDepth The maximum number of times to recurse. A depth of zero or
     *            less will simply return the passed-in value (the function will
     *            never be called).
     * @return The result of the recursive call.
     */
    default T recurse(T value, Predicate<T> terminalCondition, int maxDepth) {
        for (int i = 0; i < maxDepth && terminalCondition.test(value); i++)
            value = apply(value);
        return value;
    }
    
    /**
     * A method that creates a function which calls a function with its own result
     * some amount of times.
     * 
     * @param depth The number of times to recurse. A depth of zero or less will
     *            simply return the value passed to {@link Function#apply apply(t)}
     *            (the inner function will never be called).
     * @return A function that will call the given function on its own result a
     *         number of times as specified by the depth parameter.
     */
    default UnaryOperator<T> recursive(int depth) {
        return t -> recurse(t, depth);
    }
    
    /**
     * A method that creates a function which calls a function with its own result
     * until some condition is met.
     * 
     * @param terminalCondition The condition upon which the recursion will
     *            terminate.
     * @return A function that will call the given function on its own result a
     *         number of times as specified by the depth parameter.
     */
    default UnaryOperator<T> recursive(Predicate<T> terminalCondition) {
        Objects.requireNonNull(terminalCondition);
        return t -> recurse(t, terminalCondition);
    }
    
    /**
     * A method that creates a function which calls a function with its own result
     * some amount of times or until some condition is met.
     * 
     * @param terminalCondition The condition upon which the recursion will
     *            terminate.
     * @param maxDepth The maximum number of times to recurse. A depth of zero or
     *            less will simply return the passed-in value (the function will
     *            never be called).
     * @return A function that will call the given function on its own result a
     *         number of times as specified by the depth parameter.
     */
    default UnaryOperator<T> recursive(Predicate<T> terminalCondition, int maxDepth) {
        Objects.requireNonNull(terminalCondition);
        return t -> recurse(t, terminalCondition, maxDepth);
    }
    
    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @param <T> the type of the input and output of the operator
     * @return a unary operator that always returns its input argument
     */
    static <T> UnaryOperator<T> identity() {
        return t -> t;
    }
}
