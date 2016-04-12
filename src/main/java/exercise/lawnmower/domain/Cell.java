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

import java.util.ArrayList;
import java.util.List;

import static exercise.lawnmower.utils.Assert.*;

public class Cell
{
    private final int _row;
    private final int _col;

    private final List<Lawnmower> _lawnmowers = new ArrayList<>();

    /**
     * Create a new Cell object with in the given row and column.
     * 
     * @param row the row's number. It must be greater than zero
     * @param col the column's number. It must be greater than zero
     */
    public Cell(int row, int col)
    {
        _row = row;
        _col = col;

        isTrue(row >= 0 && col >= 0, "the row and col number must be greater than zero");
    }

    public void add(Lawnmower lawnmower)
    {
        _lawnmowers.add(lawnmower);
    }

    /**
     * Returns the {@link Lawnmower} at the specific position in the {@link Cell}.
     * 
     * @param index index of the {@link Lawnmower} to return
     * @return the {@link Lawnmower} at the specified position in this cell
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Lawnmower get(int index)
    {
        return _lawnmowers.get(index);
    }

    /**
     * @return the row
     */
    public int row()
    {
        return _row;
    }

    /**
     * @return the col
     */
    public int col()
    {
        return _col;
    }
    
    @Override
    public String toString()
    {
        return String.format("[%s,%s]", row(), col());
    }
}
