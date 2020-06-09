package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Main4Activity extends AppCompatActivity {

    /* Hint:
        1. This creates the Whack-A-Mole layout and starts a countdown to ready the user
        2. The game difficulty is based on the selected level
        3. The levels are with the following difficulties.
            a. Level 1 will have a new mole at each 10000ms.
                - i.e. level 1 - 10000ms
                       level 2 - 9000ms
                       level 3 - 8000ms
                       ...
                       level 10 - 1000ms
            b. Each level up will shorten the time to next mole by 100ms with level 10 as 1000 second per mole.
            c. For level 1 ~ 5, there is only 1 mole.
            d. For level 6 ~ 10, there are 2 moles.
            e. Each location of the mole is randomised.
        4. There is an option return to the login page.
     */
    private static final String FILENAME = "Main4Activity.java";
    private static final String TAG = "Whack-A-Mole3.0!";
    TextView textScore2;
    int advancedScore;
    Button btn_tl;
    Button btn_tm;
    Button btn_tr;
    Button btn_ml;
    Button btn_mm;
    Button btn_mr;
    Button btn_bl;
    Button btn_bm;
    Button btn_br;
    CountDownTimer tCountdown;
    CountDownTimer mCountdown;

    private void readyTimer(){
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
        tCountdown = new CountDownTimer(10000,1000){
            @Override
            public void onTick(long l) {
                Toast.makeText(Main4Activity.this, "Get ready in " + l / 1000 + "seconds.", Toast.LENGTH_SHORT).show();
                Log.v(TAG,"Ready CountDown!" + l / 1000);

            }

            @Override
            public void onFinish() {
                Toast.makeText(Main4Activity.this,"Go!",Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Ready CountDown Complete!");
                tCountdown.cancel();
                placeMoleTimer();

            }
        };
        tCountdown.start();
    }
    private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
        mCountdown = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {
                setNewMole();
                Log.v(TAG, "New Mole Location!");

            }

            @Override
            public void onFinish() {
                mCountdown.start();
            }
        };
        mCountdown.start();
    }
    private static final int[] BUTTON_IDS = {
            /* HINT:
                Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
                You may use if you wish to change or remove to suit your codes.*/
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares level difficulty.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
            It also prepares the back button and updates the user score to the database
            if the back button is selected.
         */
        btn_tl = findViewById(R.id.btn_tl);
        btn_tm = findViewById(R.id.btn_tm);
        btn_tr = findViewById(R.id.btn_tr);
        btn_ml = findViewById(R.id.btn_ml);
        btn_mm = findViewById(R.id.btn_mm);
        btn_mr = findViewById(R.id.btn_mr);
        btn_bl = findViewById(R.id.btn_bl);
        btn_bm = findViewById(R.id.btn_bm);
        btn_br = findViewById(R.id.btn_br);
        textScore2 = findViewById(R.id.textScore2);

        btn_tl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_tl);
            }
        });
        btn_tm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_tm);
            }
        });
        btn_tr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_tr);
            }
        });
        btn_ml.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_ml);
            }
        });
        btn_mm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_mm);
            }
        });
        btn_mr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_mr);
            }
        });
        btn_bl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_bl);
            }
        });
        btn_tl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_bm);
            }
        });
        btn_tl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_br);
            }
        });

        Log.v(TAG, "Current User Score: " + String.valueOf(advancedScore));

        for(final int id : BUTTON_IDS){
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
        }
    }
    @Override
    protected void onStart(){
        super.onStart();

        Intent receivingEnd = getIntent();
        int message = receivingEnd.getIntExtra("score",0);
        advancedScore = message;
        textScore2.setText(String.valueOf(message));
        readyTimer();
    }
    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, FILENAME + ": Hit, score added!");
            Log.v(TAG, FILENAME + ": Missed, point deducted!");
            belongs here.
        */
        if (checkButton.getText() == "*"){
            advancedScore++;
            Log.v(TAG, "Hit Score added!");
        }
        else{
            advancedScore--;
            Log.v(TAG, "Missed, score deducted!");
        }
        textScore2.setText(Integer.toString(advancedScore));

    }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole. Adds additional mole if the level difficulty is from 6 to 10.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);

        btn_tl.setText("O");
        btn_tm.setText("O");
        btn_tr.setText("O");
        btn_ml.setText("O");
        btn_mm.setText("O");
        btn_mr.setText("O");
        btn_bl.setText("O");
        btn_bm.setText("O");
        btn_br.setText("O");
        if (randomLocation == 0){
            btn_tl.setText("*");
        }
        else if (randomLocation == 1){
            btn_tm.setText("*");
        }
        else if (randomLocation == 2){
            btn_tr.setText("*");
        }
        else if (randomLocation == 3){
            btn_ml.setText("*");
        }
        else if (randomLocation == 4){
            btn_mm.setText("*");
        }
        else if (randomLocation == 5){
            btn_mr.setText("*");
        }
        else if (randomLocation == 6){
            btn_bl.setText("*");
        }
        else if (randomLocation == 7){
            btn_bm.setText("*");
        }
        else if (randomLocation == 8){
            btn_br.setText("*");
        }

    }

    private void updateUserScore()
    {

     /* Hint:
        This updates the user score to the database if needed. Also stops the timers.
        Log.v(TAG, FILENAME + ": Update User Score...");
      */
     /* newMolePlaceTimer.cancel();
        readyTimer.cancel(); */
    }

}
