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

public enum Orientation
{
    /**
     * North
     */
    N
    {
        @Override
        Orientation turnLeft()
        {
            return W;
        }

        @Override
        Orientation turnRight()
        {
            return E;
        }
    },

    /**
     * East
     */
    E
    {
        @Override
        Orientation turnLeft()
        {
            return N;
        }

        @Override
        Orientation turnRight()
        {
            return S;
        }
    },

    /**
     * West
     */
    W
    {
        @Override
        Orientation turnLeft()
        {
            return S;
        }

        @Override
        Orientation turnRight()
        {
            return N;
        }
    },

    /**
     * South
     */
    S
    {
        @Override
        Orientation turnLeft()
        {
            return E;
        }

        @Override
        Orientation turnRight()
        {
            return W;
        }
    };

    /**
     * Returns a new direction when from this one it turns 90 degrees in the counterclockwise direction.
     * 
     * <p>
     * East and west are at right angles to north and south, with east being in the clockwise direction of rotation 
     * from north and west being directly opposite east.
     * </p>
     * 
     * @return a new direction that when from the current position it is turned 90 degree in the counterclockwise direction.
     */
    abstract Orientation turnLeft();

    /**
     * Returns a new direction when from this one it turns 90 degrees in the clockwise direction.
     * 
     * <p>
     * <strong>East</strong> and <strong>west</strong> are at right angles to <strong>north</strong> and <strong>south</strong>, with <strong>west</strong> being in the clockwise
     * direction of rotation from <strong>south</strong> and <strong>east</strong> being directly opposite.
     * </p>
     * 
     * @return a new direction that when from the current position it is turned 90 degree in the clockwise direction.
     */
    abstract Orientation turnRight();
}
