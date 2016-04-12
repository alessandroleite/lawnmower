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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static exercise.lawnmower.utils.Assert.*;
import static java.util.Objects.*;

public class Control
{
    public enum Command
    {
        /**
         * Turn right
         */
        D, 
        
        /**
         * Turn left
         */
        G, 
        
        /**
         * Go forward
         */
        A;
    }
    
    private final Lawnmower     _lawnmower;
    private final Ground        _ground;
    
    private final AtomicBoolean _isRunning = new AtomicBoolean(false);
    
    private final List<Command> _scheduled = new ArrayList<>();
    private final List<Command> _history = new ArrayList<Command>();

    public Control(Lawnmower lawnmower, Ground ground)
    {
        _lawnmower = requireNonNull(lawnmower);
        _lawnmower.setControl(this);
        _ground = requireNonNull(ground);
    }

    public Control schedule(Command ... commands)
    {
        noNullElements(commands, "The array must have non-null elements");
        
        if (!_isRunning.get())
        {
            _scheduled.addAll(Arrays.asList(commands));
        }
        
        return this;
    }
    
    public Control scheduleAndPlay(Command ... commands) throws InterruptedException
    {
        noNullElements(commands, "The array must have non-null elements");
        
        if (!_isRunning.get())
        {
            _scheduled.addAll(Arrays.asList(commands));
            
            play();
        }
        
        return this;
    }

    public Control play() throws InterruptedException
    {
        _ground.acquire();
        
        if (_isRunning.compareAndSet(false, true))
        {
            try
            {
                _scheduled.forEach(this::execute);
                _history.addAll(_scheduled);
                _scheduled.clear();
            }
            finally
            {
                _isRunning.compareAndSet(true, false);
                _ground.release();
            }
        }
        
        return this;
    }
    
    private void execute(Command command)
    {
        switch (command)
        {
        case A:
            _lawnmower.move();
            break;
        case D:
            _lawnmower.turnRight();
            break;
        case G:
            _lawnmower.turnLeft();
            break;
        default:
            throw new IllegalStateException();
        }
    }

    public void putAt(Position position)
    {
        _ground.set(position.getX(), position.getY(), _lawnmower);
    }
    

    public boolean isIn(int x, int y)
    {
        return _ground.isIn(x, y);
    }

    /**
     * @return the lawnmower
     */
    public Lawnmower getLawnmower()
    {
        return _lawnmower;
    }

    /**
     * @return the ground
     */
    public Ground getGround()
    {
        return _ground;
    }
}
