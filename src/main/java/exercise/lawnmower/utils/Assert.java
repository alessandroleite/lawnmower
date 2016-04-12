/**
 *     Copyright (C) 2016 All Rights Reserved to Authors and Contributors
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License,
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package exercise.lawnmower.utils;

/**
 * Assertion utility class that helps on validating arguments. It is useful for identifying programmer errors early and clearly at runtime.
 *
 */
public class Assert
{
    private Assert()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Assert a boolean expression, throwing {@code IllegalArgumentException} if the test result is {@code false}.
     * 
     * @param expression a boolean expression
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if expression is {@code false}
     */
    public static void isTrue(boolean expression, String message)
    {
        if (!expression)
        {
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Assert that an object is not {@code null} .
     * 
     * @param object the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static void notNull(Object object, String message)
    {
        if (object == null)
        {
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * Assert that an array has no null elements.
     * Note: Does not complain if the array is empty!
     * <pre class="code">Assert.noNullElements(array, "The array must have non-null elements");</pre>
     * @param array the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array contains a {@code null} element
     */
    public static void noNullElements(Object[] array, String message)
    {
        if (array != null)
        {
            for (Object element : array)
            {
                if (element == null)
                {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

}
