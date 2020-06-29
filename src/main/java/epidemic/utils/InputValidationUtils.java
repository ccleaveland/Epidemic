/*
 * Crystal Cleaveland
 * Epidemic Game
 * InputValidationUtils.java - Utility class to validate required user input
 * 28.Jun.2020
 */

package epidemic.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Character.toUpperCase;

public class InputValidationUtils
{
    private static final Scanner in = new Scanner( System.in );

    private InputValidationUtils(){}
    
    public static int validateNum( int lowerLimit, int upperLimit )
    {
        int intInput;
        boolean invalid = true;

        do
        {
            // Retrieve user input
            intInput = retrieveValidNumInput();

            // Verify that number is between the lower limit and upper limit, inclusive
            if( intInput >= lowerLimit && intInput <= upperLimit )
            {
                invalid = false;
            }

            // If invalid true after all comparisons to valid options, print error message
            if( invalid )
            {
                System.out.print( "Invalid input. Try again: " );
            }
        }
        while( invalid );

        return intInput;
    }

    public static char validateAlphaChar( char validOptions[] )
    {
        char charInput;
        boolean invalid = true;

        do
        {
            charInput = toUpperCase( retrieveValidAlphaCharInput() );

            // Compare input value to valid options in the vector
            for( char option : validOptions )
            {
                // If input value matches a valid option, set invalid to false so loop ends
                if( charInput == option )
                {
                    invalid = false;
                    break;
                }
            }

            // If invalid true after all comparisons to valid options, print error message
            if( invalid )
            {
                System.out.print( "Invalid input. Try again: " );
            }
        }
        while( invalid );

        return charInput;
    }

    private static int retrieveValidNumInput()
    {
        int input = 0;
        boolean isNotNumber = true;

        do
        {
            // Retrieve user input
            try
            {
                input = in.nextInt();
                isNotNumber = false;
            }
            // If user entered a non number, try again
            catch( InputMismatchException e )
            {
                System.out.print( "Invalid input. Try again: " );
                in.nextLine();
            }
        }
        while( isNotNumber );

        return input;
    }
    
    private static char retrieveValidAlphaCharInput()
    {
        char input = 0;
        boolean isNotAlphaChar = true;

        do
        {
            // Retrieve user input
            try
            {
                input = in.next().charAt( 0 );
                if( Character.isLetter( input ) )
                {
                    isNotAlphaChar = false;
                }
                else
                {
                    System.out.print( "Invalid input. Try again: " );
                    in.nextLine();
                }
            }
            // If user entered a non number, try again
            catch( InputMismatchException e )
            {
                System.out.print( "Invalid input. Try again: " );
                in.nextLine();
            }
        }
        while( isNotAlphaChar );

        return input;
    }
}