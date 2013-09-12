package com.github.buchio.Astah2Smc;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.ParseException;

import com.github.buchio.Astah2Smc.AstahFileToHash;
import com.github.buchio.Astah2Smc.AstahHashToSmcFiles;

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
