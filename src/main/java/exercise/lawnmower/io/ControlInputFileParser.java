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
package exercise.lawnmower.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exercise.lawnmower.domain.Control;
import exercise.lawnmower.domain.Ground;
import exercise.lawnmower.domain.Lawnmower;
import exercise.lawnmower.domain.Orientation;
import exercise.lawnmower.domain.Control.Command;
import static exercise.lawnmower.utils.Assert.*;
import static java.lang.Integer.*;

public class ControlInputFileParser
{
    public static String[] parserEnvironmentSetupFile(File f) throws IOException
    {
        List<String> lines = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(f)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line.trim());
            }
        }
        
        return lines.toArray(new String[lines.size()]);
    }
    
    public static List<Control> parse (File f) throws IOException
    {
        final List<Control> result = new ArrayList<>();
        final String[] lines = parserEnvironmentSetupFile(f);
        
        if (lines.length > 0)
        {
            String[] dimensions = lines[0].split(" ");
            
            final Ground g = new Ground(parseInt(dimensions[0].trim()), parseInt(dimensions[1].trim()));
            
            for (int i = 1; i < lines.length; i += 2)
            {
                String[] position = lines[i].split(" ");
                isTrue(position.length == 3, "A lawn mower position must comprise two 3 parts: coordinates (x, y) and the orientation");
                
                Lawnmower lawnmower = new Lawnmower(parseInt(position[0]), parseInt(position[1]), Orientation.valueOf(position[2].toUpperCase()));
                result.add(new Control(lawnmower, g).schedule(parseCommands(lines[i + 1])));
            }
        }
        
        return result;
    }

    private static Command[] parseCommands(String line)
    {
        Command[] commands = new Command[line.length()];
        
        for (int i = 0; i < line.length(); i++)
        {
            commands[i] = Command.valueOf(line.substring(i, i + 1));
        }
        
        return commands;
    }
}
