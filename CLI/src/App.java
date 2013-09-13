// Copyright (c) 2013 Yukio Obuchi
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.github.buchio.Astah2Smc;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.ParseException;

import com.github.buchio.Astah2Smc.Common.AstahFileToHash;
import com.github.buchio.Astah2Smc.Common.AstahHashToSmcFiles;

/**
 * Astah2Smc
 *
 */
public class App 
{
    public static String mBaseDir;

    public static void main( String[] args )
    {
        Options options = new Options();
        options.addOption( "b", true, "Base Directory" );
        CommandLineParser parser = new BasicParser();
        CommandLine commandLine;
        
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("引数解析エラー");
            return;
        }
        String baseDir = System.getProperty("user.dir");
        if (commandLine.hasOption("b")) {
            baseDir =  commandLine.getOptionValue("b");
        }
        mBaseDir = baseDir;
        args = commandLine.getArgs();
        
        try {
            (new AstahHashToSmcFiles( mBaseDir )).outputSmcFiles( ((new AstahFileToHash( args[0] )).getHash()).get( "classes" ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
