/**
 * {This app takes a number from 1 to 1000 and compares it to a randomly generated number. User must try to guess appropriate number within 10 tries }
 *
 * Cristobal D'Alessio {dale0106@algonquinlive.com}
 */

package com.algonquincollege.dale0106.guessanumber;


import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    //TODO: create tag and assign it String MainActivity to use for logCat later on
    private static final String TAG = "MainActivity";
    private static final String ABOUT_DIALOG_TAG;

    static {

        ABOUT_DIALOG_TAG = "About Dialog";

    }

    //  TODO: create a public integer to hold the number of guesses the user has in each round
    public int guessCount = 0;

    // TODO: Create the random object
    public final Random rand = new Random();

    //  TODO: create a variable to store my random number
    public int theNumber = rand.nextInt(1000) + 1;


    public void resetGame() {

        Toast.makeText(getApplicationContext(), "Game has been reset :) New random number generated", Toast.LENGTH_SHORT).show();
        guessCount = 0;
        theNumber = rand.nextInt(1000) + 1;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: reference the  check button view by its id
        Button checkButton = (Button) findViewById(R.id.button2);

        //TODO: reference the  reset button view by its id
        Button resetButton = (Button) findViewById(R.id.button);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                resetGame();
                //Calls resetGame function which will generate a new random number and reset guessCount back to 0

            }
        });

        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                Toast.makeText(getApplicationContext(), "Current random number: " + theNumber, Toast.LENGTH_SHORT).show();

                return false;


            }
        });


        //TODO: Create an on click event listener
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Toast.makeText(getApplicationContext(), "Button Check clicked", Toast.LENGTH_SHORT).show();

                // TODO: reference the view EditText which HOLDS the user's answer
                EditText userGuessText = (EditText) findViewById(R.id.editText);


                // TODO: Get the userGuess input
                int userGuess = Integer.parseInt(userGuessText.getText().toString());
                String userStringGuess = userGuessText.getText().toString();


                //check to see if i can see the random number
                Log.d(TAG, "The secret number is: " + theNumber);
                Log.d(TAG, "User guess is: " + userGuess);

                // TODO: validate user input in case user leaves text field blank
                if (userStringGuess.isEmpty()) {

                    userGuessText.setError("You must enter a value!!!!");
                    userGuessText.requestFocus();
                    return;


                }

                if (userGuess == 0) {

                    userGuessText.setError("You must enter a value between 1 and 1000");
                    userGuessText.requestFocus();
                    return;

                }

                if (userGuess > 1000) {

                    userGuessText.setError("You must enter a value between 1 and 1000");

                }


                if (userGuess < theNumber) {


                    Toast.makeText(getApplicationContext(), "Your guess is too LOW! - Number of current tries: " + guessCount, Toast.LENGTH_SHORT).show();
                    guessCount++;
                    //increment every time user gets it wrong


                }


                if (userGuess > theNumber) {

                    Toast.makeText(getApplicationContext(), "Your guess is too HIGH! - Number of current tries:" + guessCount, Toast.LENGTH_SHORT).show();
                    guessCount++;
                    // Increment guessCount every time user gets answer wrong

                }


                if (guessCount > 10) {

                    // toast tells user to restart the application
                    Toast.makeText(getApplicationContext(), "No more guesses! Press reset button to retry ", Toast.LENGTH_SHORT).show();


                }


                if (userGuess == theNumber) {


                    // create if statement to check guessCount number and decide what toast message to output depending on the user's amount of tries


                    if (guessCount <= 5) {

                        Toast.makeText(getApplicationContext(), "Superior win! Press reset button to continue", Toast.LENGTH_SHORT).show();
                        //resetGame();


                    }
                    if (guessCount >= 6 && guessCount < 10) {

                        Toast.makeText(getApplicationContext(), "Excellent win! Press reset button to continue", Toast.LENGTH_SHORT).show();
                        //resetGame();


                    }
                }


            }

        });


    }

    // TODO: add this method to create the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // TODO: add this method to handle when the user selects a menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogueFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
