/*
 * Copyright 2018 Griffin O'Neill
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
package eolu.util.wip;

import java.util.List;
import java.util.Map;

import eolu.util.function.DoubleBinaryOperator;
import eolu.util.function.DoubleSupplier;
import eolu.util.function.DoubleUnaryOperator;
import eolu.util.function.Function;
import eolu.util.function.IntBinaryOperator;
import eolu.util.function.IntFunction;
import eolu.util.function.IntToDoubleFunction;
import eolu.util.function.IntToLongFunction;
import eolu.util.function.IntUnaryOperator;
import eolu.util.function.LongBinaryOperator;
import eolu.util.function.LongUnaryOperator;

/**
 * This class contains various functions of different utility.
 * 
 * @formatter:off
 */
public class Functions {
    
    /**
     * @see {@link Math#abs(double)}
     */
    public static final DoubleUnaryOperator ABS_DOUBLE = Math::abs;
    
    /**
     * @see {@link Math#abs(int)}
     */
    public static final IntUnaryOperator ABS_INT = Math::abs;
    
    /**
     * @see {@link Math#abs(long)}
     */
    public static final LongUnaryOperator ABS_LONG = Math::abs;
    
    /**
     * @see {@link Math#random()}
     */
    public static final DoubleSupplier RANDOM = Math::random;
    
    /**
     * @see {@link Math#pow()}
     */
    public static final DoubleBinaryOperator POW_DOUBLE = Math::pow;
    
    /**
     * Functional version of a double operator.
     */
    public static final DoubleBinaryOperator 
    ADD_DOUBLE      = (a, b) -> a + b,
    SUBTRACT_DOUBLE = (a, b) -> a - b,
    MULTIPLY_DOUBLE = (a, b) -> a * b,
    DIVIDE_DOUBLE   = (a, b) -> a / b,
    MOD_DOUBLE      = (a, b) -> a % b;
    
    /**
     * Functional version of an int operator.
     */
    public static final IntBinaryOperator 
    ADD_INT      = (a, b) -> a + b,
    SUBTRACT_INT = (a, b) -> a - b,
    MULTIPLY_INT = (a, b) -> a * b,
    DIVIDE_INT   = (a, b) -> a / b,
    MOD_INT      = (a, b) -> a % b;
    
    /**
     * Functional version of a long operator.
     */
    public static final LongBinaryOperator 
    ADD_LONG      = (a, b) -> a + b,
    SUBTRACT_LONG = (a, b) -> a - b,
    MULTIPLY_LONG = (a, b) -> a * b,
    DIVIDE_LONG   = (a, b) -> a / b,
    MOD_LONG      = (a, b) -> a % b;
    
    // @formatter:on
    
    /**
     * @param arr The array to create an access function for.
     * @return A function which gets the value at some index in the given array.
     */
    public static IntUnaryOperator arrayGet(int[] arr) {
        return i -> arr[i];
    }
    
    /**
     * @param arr The array to create an access function for.
     * @return A function which gets the value at some index in the given array.
     */
    public static IntToDoubleFunction arrayGet(double[] arr) {
        return i -> arr[i];
    }
    
    /**
     * @param arr The array to create an access function for.
     * @return A function which gets the value at some index in the given array.
     */
    public static IntToLongFunction arrayGet(long[] arr) {
        return i -> arr[i];
    }
    
    /**
     * @param <T> The type of object contained by the array.
     * @param arr The array to create an access function for.
     * @return A function which gets the value at some index in the given array.
     */
    public static <T> IntFunction<T> arrayGet(T[] arr) {
        return i -> arr[i];
    }
    
    /**
     * @param list The list to create an access function for.
     * @return A function which gets the value at some index in the given list.
     */
    public static <T> IntFunction<T> listGet(List<T> list) {
        return list::get;
    }
    
    /**
     * @param map The map to create an access function for.
     * @return A function which gets a value mapped to some key in the given map.
     */
    public static <K, V> Function<K, V> mapGet(Map<K, V> map) {
        return map::get;
    }
}
