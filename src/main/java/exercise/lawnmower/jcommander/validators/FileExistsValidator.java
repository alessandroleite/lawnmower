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
package exercise.lawnmower.jcommander.validators;

import java.io.File;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * This validator makes sure that a parameter's value is a {@link File} and that it exists and can be read.
 */
public class FileExistsValidator implements IParameterValidator
{
    @Override
    public void validate(String name, String value) throws ParameterException
    {
        final File f = new File(value);

        if (!f.exists())
        {
            throw new ParameterException(String.format("Invalid parameter [%s]. File [%s] does not exist", name, value));
        }

        if (!f.canRead())
        {
            throw new ParameterException(String.format("Invalid parameter [%s]. File [%s] cannot be read", name, value));
        }
    }
}
