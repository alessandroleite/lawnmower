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
import static java.util.Objects.*;

import java.util.concurrent.Semaphore;

/**
 * It is prohibited to two lower mower to perform at the same time in this ground.
 */
public class Ground
{
    private final Cell[][]     _cells;
    private final Dimension    _dimension;
    private final Semaphore    _semaphore = new Semaphore(1);
    
    /** The lock protecting all mutators */
//    private final ReentrantLock _lock = new ReentrantLock();

    /**
     * Creates a new {@link Ground} object with the given number of rows and columns (grids).
     * <p>The rows and columns are 1-based indices by default.</p>
     * 
     * @param rows the number of rows. It must be greater than zero.
     * @param cols the number of columns. It must be greater than zero.
     */
    public Ground(int rows, int cols)
    {
        _dimension = new Dimension(rows, cols);
        _cells = new Cell[_dimension.getRows()][_dimension.getColumns()];

        for (int i = 0; i < _cells.length; i++)
        {
            for (int j = 0; j < _cells[i].length; j++)
            {
                _cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Puts a {@link Lawnmower} in the specific position (x, y).
     *  
     * @param x the x coordinate value
     * @param y the y coordinate value
     * @param l the {@link Lawnmower} to put at the position. It might not be <code>null</code>
     */
    public void set(int x, int y, Lawnmower l)
    {
        isTrue(isIn(x, y), format("Invalid position [%s,%s]", x, y));
        requireNonNull(l, "The lawnmower might not be null");

//        final ReentrantLock lock = this._lock;
//        lock.lock();

//        try
//        {
            if (!l.position().isEqualsTo(x, y))
            {
                l.setPosition(x, y);
            }

            _cells[x][y].add(l);
//        }
//        finally
//        {
//            lock.unlock();
//        }
    }
    
    /**
     * Gets the number of rows.
     * @return the number of rows
     */
    public int getRows()
    {
        return _dimension.getRows();
    }

    /**
     * Gets the number of columns.
     * @return the number of columns
     */
    public int getColumns()
    {
        return _dimension.getColumns();
    }
    
    /**
     * Returns <code>true</code> iff the given point (x, y) is in the ground dimension.
     * 
     * @param x the x value
     * @param y the y value
     * @return <code>true</code> if the given belongs to this {@link Ground} area; otherwise returns <code>false</code>.
     */
    public boolean isIn(int x, int y)
    {
        return _dimension.isIn(x, y);
    }
    
    /**
     * Try to acquire a permit to execute on this {@link Ground}, blocking until it is available, or the threas is interrupted. 
     * Acquires a permit, if there isn't another object running on this ground and returns immediately, reducing the number of available permits by one.
     * 
     * @return the same instance
     * @throws InterruptedException
     */
    public Ground acquire() throws InterruptedException
    {
        _semaphore.acquire();
        return this;
    }
    
    /**
     * Releases a permit, allowing other lawn mower to move on the ground.
     * @return the same instance.
     */
    public Ground release()
    {
        _semaphore.release();
        return this;
    }
}
