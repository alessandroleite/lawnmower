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

import java.awt.Point;
import java.util.Objects;

import static java.util.Objects.*;

/**
 * Immutable class representing a position in a plan. 
 */
public final class Position
{
    private final Orientation _orientation;
    private final Point       _coordinate;

    public Position(final Point coordinate, final Orientation orientation)
    {
        _coordinate = requireNonNull(coordinate);
        _orientation = requireNonNull(orientation);
    }
    
    public Position(int x, int y, Orientation orientation)
    {
        this(new Point(x, y), orientation);
    }

    public static Position newPosition(Point coordinate, Orientation direction)
    {
        return new Position(coordinate, direction);
    }
    
    public static Position newPosition(int x, int y, Orientation direction)
    {
        return new Position(x, y, direction);
    }

    /**
     * @return the orientation
     */
    public Orientation getOrientation()
    {
        return _orientation;
    }

    /**
     * Returns the coordinate of this {@link Position}. It is important to highlight 
     * that this method returns a copy of the {@link Point}, since by default, {@link Point} is a
     * mutable class.
     * 
     * @return the coordinate
     */
    public Point getCoordinate()
    {
        return new Point(_coordinate);
    }
    
    /**
     * Returns the X coordinate of this {@link Position}.
     * @return the X coordinate of this {@link Position}.
     */
    public int getX()
    {
        return (int) _coordinate.getX();
    }
    
    /**
     * Returns the Y coordinate of this {@link Position}.
     * @return the Y coordinate of this {@link Position}.
     */
    public int getY()
    {
        return (int) _coordinate.getY();
    }
    
    @Override
    public String toString()
    {
        return String.format("%s %s %s", getX(), getY(), _orientation);
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
        
        Position other = (Position) obj;
        return Objects.equals(getCoordinate(), other.getCoordinate()) && 
               Objects.equals(getOrientation(), other.getOrientation());
    }
    
    @Override
    public int hashCode()
    {
       return (_orientation.hashCode() + _coordinate.hashCode()) * 17;
    }

    public boolean isEqualsTo(int x, int y)
    {
        return getX() == x && getY() == y;
    }
}
