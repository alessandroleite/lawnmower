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
package exercise.lawnmower.domain;

import static exercise.lawnmower.utils.Assert.*;
import static java.lang.String.*;

public class Dimension
{
    private final int _rows;
    private final int _columns;

    public Dimension(int rows, int cols, boolean isZeroBased)
    {
        isTrue(rows > 0 && cols > 0, format("Invalid dimension definition [%s, %s]", rows, cols));

        _rows = isZeroBased ? rows : rows + 1;
        _columns = isZeroBased ?  cols : cols + 1;
    }
    
    public Dimension(int rows, int cols)
    {
        this(rows, cols, false);
    }

    /**
     * @return the rows
     */
    public int getRows()
    {
        return _rows;
    }

    /**
     * @return the columns
     */
    public int getColumns()
    {
        return _columns;
    }

    @Override
    public String toString()
    {
        return String.format("[%s,%s]", _rows, _columns);
    }

    @Override
    public int hashCode()
    {
        return (_rows + _columns) * 13;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null || !this.getClass().equals(obj.getClass()))
        {
            return false;
        }

        Dimension other = (Dimension) obj;

        return _rows == other._rows && _columns == other._columns;
    }

    public boolean isIn(int x, int y)
    {
        return (x >= 0 && x < _rows) && (y >= 0 && y < _columns);
    }
}
