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

import org.junit.Before;
import org.junit.Test;

import exercise.lawnmower.domain.Control;
import exercise.lawnmower.domain.Ground;
import exercise.lawnmower.domain.Lawnmower;
import exercise.lawnmower.domain.Position;
import static exercise.lawnmower.domain.Control.Command.*;
import static exercise.lawnmower.domain.Orientation.*;
import static org.assertj.core.api.Assertions.*;

public class LawnMowerControlTest
{
    private Ground _ground;
    
    @Before
    public void setUp()
    {
        _ground = new Ground(5, 5);
    }
    
    @Test
    public void must_be_at_the_right_top_position() throws InterruptedException
    {
        Control c = new Control(new Lawnmower(5, 5, N), _ground);
        
        c.schedule(A).play();
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 5, N));
        
        c.schedule(D, A).play(); 
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 5, E));

        c.schedule(G, A).play(); 
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 5, N));
    }
    
    @Test
    public void must_be_at_the_left_top_position() throws InterruptedException
    {
        Control c = new Control(new Lawnmower(0, 5, N), _ground);
        
        c.schedule(A).play();
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 5, N));
        
        c.schedule(G, A).play(); 
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 5, W));

        c.schedule(G).play(); 
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 5, S));
    }

    @Test
    public void must_be_at_the_left_bottom_position() throws InterruptedException
    {
        Control c = new Control(new Lawnmower(0, 0, W), _ground);
        
        c.scheduleAndPlay(A);
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 0, W));
        
        c.scheduleAndPlay(G);
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 0, S));
        
        c.scheduleAndPlay(A);
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 0, S));
        
        c.scheduleAndPlay(G);
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(0, 0, E));
    }
    
    @Test
    public void must_be_at_the_right_bottom_position() throws InterruptedException
    {
        Control c = new Control(new Lawnmower(5, 0, S), _ground);
        
        c.scheduleAndPlay(A);
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 0, S));
        
        c.scheduleAndPlay(G);
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 0, E));
        
        c.scheduleAndPlay(G);
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 0, N));
        
        c.scheduleAndPlay(G);
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 0, W));
    }
    
    @Test
    public void must_validate_the_provided_examples() throws InterruptedException
    {
        Control c = new Control(new Lawnmower(1, 2, N), _ground);
        
        c.schedule(G, A, G, A,  G, A, G, A, A).play();
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(1, 3, N));
        
        c = new Control(new Lawnmower(3, 3, E), _ground);
        
        c.schedule(A, A, D, A, A, D, A, D, D, A).play();
        assertThat(c.getLawnmower().position()).isEqualTo(new Position(5, 1, E));
    }
}
