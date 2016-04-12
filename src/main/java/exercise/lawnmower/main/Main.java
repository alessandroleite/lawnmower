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
package exercise.lawnmower.main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.BooleanConverter;
import com.beust.jcommander.converters.FileConverter;

import exercise.lawnmower.domain.Control;
import exercise.lawnmower.io.ControlInputFileParser;
import exercise.lawnmower.jcommander.validators.FileExistsValidator;

public class Main
{
    private static final transient Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

    public static class Args
    {
        @Parameter(names = { "--input", "--i" }, 
                   required = true, 
                   converter = FileConverter.class, 
                   validateWith = FileExistsValidator.class,
                   description = "The input file")
        private File _input;
        
        @Parameter(names = { "--parallel"}, 
                   required = false, 
                   converter = BooleanConverter.class, 
                   description = "if it must try to execute the lawn mowers in parallel. It does not guarantee the order.")
        private boolean _parallel;
    }

    private Main()
    {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) throws IOException
    {
        Optional<Args> pArgs = parseArgs(args);

        if (pArgs.isPresent())
        {
            List<Control> controls = ControlInputFileParser.parse(pArgs.get()._input);
            
            if (pArgs.get()._parallel)
            {
                controls.parallelStream().forEach(Main::execute);
            }
            else 
            {
                controls.forEach(Main::execute);
            }
        }
    }
    
    private static void execute(Control control)
    {
        try
        {
            control.play();
            System.out.println(control.getLawnmower().position());
        }
        catch (InterruptedException e)
        {
            LOGGER.error("Interruped thread. Error message: {}", e.getMessage(), e);
        }
    }

    /**
     * Returns a non-null {@link Args} instance with the value of the command line parameters.
     * 
     * @param args the application's arguments
     * @return a non-<code>null</code> {@link Args} with the value of the expected application's parameters.
     * @throws ParameterException if there is invalid parameter(s)
     */
    private static Optional<Args> parseArgs(String[] args)
    {
        Optional<Args> result = Optional.empty();
        Args jargs = new Args();
        
        final JCommander commander = new JCommander(jargs);

        try
        {
            commander.parse(args);
            result = Optional.of(jargs);
        }
        catch (ParameterException e)
        {
            LOGGER.error("Invalid parameters. Error message: [{}]", e.getMessage());
            commander.usage();
        }

        return result;
    }
}
