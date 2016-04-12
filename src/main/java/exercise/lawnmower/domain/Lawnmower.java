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

import static exercise.lawnmower.domain.Position.*;
import static java.util.Objects.*;

public class Lawnmower
{
    private volatile Position _position;
    private volatile Control  _control;
    
    public Lawnmower(Position position)
    {
        setPosition(requireNonNull(position));
    }
    
    public Lawnmower(int x, int y, Orientation orientation)
    {
        this(new Position(x,  y, orientation));
    }
    
    public Lawnmower(int x, int y, Orientation orientation, Control control)
    {
        this(x,  y, orientation);
        _control = control;
    }
    
    public Lawnmower setControl(Control control)
    {
        synchronized (this)
        {
            _control = control;
        }
        
        return this;
    }

    protected Lawnmower move()
    {
        int x, y = x = 0;
        
        switch(_position.getOrientation())
        {
        case E:
            x = 1;
            break;
        case W:
            x = -1;
            break;
        case N:
            y = 1;
            break;
        case S:
            y = -1;
            break;
        }
        
        x = _position.getX() + x;
        y = _position.getY() + y;
        
        if (_control.isIn(x,  y))
        {
            _position = newPosition(x, y, _position.getOrientation());
        }
        
        return this;
    }

    protected Lawnmower turnRight()
    {
        _position = newPosition(_position.getCoordinate(), _position.getOrientation().turnRight());
        return this;
    }

    protected Lawnmower turnLeft()
    {
        _position = newPosition(_position.getCoordinate(), _position.getOrientation().turnLeft());
        return this;
    }

    /**
     * @return the current position
     */
    public Position position()
    {
        return _position;
    }
    
    @Override
    public String toString()
    {
        return super.toString();
    }

    /**
     * @return the control
     */
    public Control getControl()
    {
        return _control;
    }

    public Lawnmower setPosition(int x, int y)
    {
        setPosition(newPosition(x,  y, _position.getOrientation()));
        return this;
    }
    
    public Lawnmower setPosition(Position newPosition)
    {
        _position = newPosition;
        
        if (_control != null)
        {
            _control.putAt(newPosition);
        }
        
        return this;
    }
}
