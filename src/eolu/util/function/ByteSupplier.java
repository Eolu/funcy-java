/*
 * Copyright 2019 Griffin O'Neill
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
 * This is a functional interface which supplies a byte.
 */
@FunctionalInterface
public interface ByteSupplier extends Supplier<Byte> {
    
    /**
     * @return The byte value of this supplier.
     */
    byte getAsByte();
    
    /**
     * @see java.util.function.Supplier#get()
     */
    @Override
    default Byte get() {
        return getAsByte();
    }
    
    /**
     * Lift a supplier.
     * 
     * @param <R> The return type.
     * @param functor The function to use in lifting.
     * @return A supplier that passes the result of fn through a functor to produce
     *         a lifted supplier.
     */
    default <R> Supplier<R> lift(Function<Byte, R> functor) {
        return () -> functor.apply(getAsByte());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default <T> BooleanSupplier lift(Predicate<Byte> functor) {
        return () -> functor.test(getAsByte());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default DoubleSupplier lift(ToDoubleFunction<Byte> functor) {
        return () -> functor.applyAsDouble(getAsByte());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default IntSupplier lift(ToIntFunction<Byte> functor) {
        return () -> functor.applyAsInt(getAsByte());
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default LongSupplier lift(ToLongFunction<Byte> functor) {
        return () -> functor.applyAsLong(getAsByte());
    }
}
