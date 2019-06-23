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
package eolu.util.function;

/**
 * This class contains various functions of different utility.
 * 
 * @formatter:off
 */
public class Functions {
        
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
     * @see {@link Math#round(double)}
     */
    public static final DoubleToLongFunction ROUND_DOUBLE = Math::round;
    
    /**
     * Cast an int to a long.
     */
    public static final IntToLongFunction CAST_INT_TO_LONG = i -> (long) i;
    
    /**
     * Cast an int to a double.
     */
    public static final IntToDoubleFunction CAST_INT_TO_DOUBLE = i -> (double) i;
    
    /**
     * Cast a long to an int. (Warning: this is a narrowing conversion).
     */
    public static final LongToIntFunction CAST_LONG_TO_INT = l -> (int) l;
    
    /**
     * Cast a long to a double. (Warning: this is a narrowing conversion).
     */
    public static final LongToDoubleFunction CAST_LONG_TO_DOUBLE = l -> (double) l;
    
    /**
     * Cast a double to a long. Fractional values will be truncated.
     */
    public static final DoubleToLongFunction CAST_DOUBLE_TO_LONG = d -> (long) d;
    
    /**
     * Cast a double to an int. Fractional values will be truncated.
     */
    public static final DoubleToIntFunction CAST_DOUBLE_TO_INT = d -> (int) d;
    
    // @formatter:on
}
