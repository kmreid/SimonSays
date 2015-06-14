package net.kreid.simonsays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends Activity {

    protected static final int RESULT_CLOSE_ALL = 0;
    public static Game game;
    public static Sounds sounds;

    private static Boolean allowInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            game = new Game();
            sounds = new Sounds(getApplicationContext());
        }

        setContentView(R.layout.activity_main);

        game.restart();

        allowInput = true;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        updateScore();
        playGameState();

        final Button redButton = (Button) findViewById(R.id.button_red);
        redButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(allowInput == false) return;

                MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP1);
                redButton.setBackgroundResource(R.drawable.btn_red_pressed);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        redButton.setBackgroundResource(R.drawable.btn_red);
                    }
                }, 1000);

                checkSelection(game.makeSelection(Game.Colour.RED));
            }
        });

        final Button greenButton = (Button) findViewById(R.id.button_green);
        greenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(allowInput == false) return;

                MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP2);
                greenButton.setBackgroundResource(R.drawable.btn_green_pressed);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        greenButton.setBackgroundResource(R.drawable.btn_green);
                    }
                }, 1000);

                checkSelection(game.makeSelection(Game.Colour.GREEN));
            }
        });

        final Button blueButton = (Button) findViewById(R.id.button_blue);
        blueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (allowInput == false) return;

                MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP3);
                blueButton.setBackgroundResource(R.drawable.btn_blue_pressed);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        blueButton.setBackgroundResource(R.drawable.btn_blue);
                    }
                }, 1000);

                checkSelection(game.makeSelection(Game.Colour.BLUE));
            }
        });

        final Button yellowButton = (Button) findViewById(R.id.button_yellow);
        yellowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (allowInput == false) return;

                MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP4);
                yellowButton.setBackgroundResource(R.drawable.btn_yellow_pressed);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        yellowButton.setBackgroundResource(R.drawable.btn_yellow);
                    }
                }, 500);

                checkSelection(game.makeSelection(Game.Colour.YELLOW));
            }
        });
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    private void checkSelection(Boolean correct)
    {
        if(correct)
        {
            if(game.getScore() == game.getGameState().size()) {
                setActivityBackgroundColor(Color.GREEN);
                MainActivity.sounds.PlaySound(Sounds.SoundType.WIN);

                new CountDownTimer(1000, 50) {

                    @Override
                    public void onTick(long arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onFinish() {
                        setActivityBackgroundColor(Color.WHITE);
                        resetButtons();
                        game.iterate();
                        updateScore();
                        playGameState();
                    }
                }.start();
            }
        }
        else
        {
            setActivityBackgroundColor(Color.RED);
            MainActivity.sounds.PlaySound(Sounds.SoundType.LOSE);

            new CountDownTimer(1000, 50) {

                @Override
                public void onTick(long arg0) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onFinish() {
                    setActivityBackgroundColor(Color.WHITE);
                    resetButtons();
                    game.restart();
                    updateScore();
                    playGameState();
                }
            }.start();
        }
    }
    private void resetButtons()
    {
                List<Game.Colour> gameState = game.getGameState();
                final Button redButton = (Button) findViewById(R.id.button_red);
                final Button greenButton = (Button) findViewById(R.id.button_green);
                final Button blueButton = (Button) findViewById(R.id.button_blue);
                final Button yellowButton = (Button) findViewById(R.id.button_yellow);

                redButton.setBackgroundResource(R.drawable.btn_red);
                greenButton.setBackgroundResource(R.drawable.btn_green);
                blueButton.setBackgroundResource(R.drawable.btn_blue);
                yellowButton.setBackgroundResource(R.drawable.btn_yellow);
    }

    private void activateButtonForMillis(final Game.Colour colour, final int millis)
    {
        final Button redButton = (Button) findViewById(R.id.button_red);
        final Button greenButton = (Button) findViewById(R.id.button_green);
        final Button blueButton = (Button) findViewById(R.id.button_blue);
        final Button yellowButton = (Button) findViewById(R.id.button_yellow);

        Handler activateHandler = new Handler();
        activateHandler.post(new Runnable() {
            public void run() {
                switch (colour) {
                    case RED:
                        MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP1);
                        redButton.setBackgroundResource(R.drawable.btn_red_pressed);
                        break;
                    case GREEN:
                        MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP2);
                        greenButton.setBackgroundResource(R.drawable.btn_green_pressed);
                        break;
                    case BLUE:
                        MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP3);
                        blueButton.setBackgroundResource(R.drawable.btn_blue_pressed);
                        break;
                    case YELLOW:
                        MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP4);
                        yellowButton.setBackgroundResource(R.drawable.btn_yellow_pressed);
                        break;
                }

                new CountDownTimer(millis, 50) {

                    @Override
                    public void onTick(long arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onFinish() {
                        resetButtons();
                    }
                }.start();
            }
        });
    }

    private void updateScore()
    {
        Handler activateHandler = new Handler();
        activateHandler.post(new Runnable() {
            public void run() {
                int score = game.getScore();
                final TextView scoreText = (TextView) findViewById(R.id.scoreText);
                scoreText.setText(String.valueOf(score));
            }
        });
    }

    private void playGameState()
    {
                final List<Game.Colour> gameState = game.getGameState();
                final Button redButton = (Button) findViewById(R.id.button_red);
                final Button greenButton = (Button) findViewById(R.id.button_green);
                final Button blueButton = (Button) findViewById(R.id.button_blue);
                final Button yellowButton = (Button) findViewById(R.id.button_yellow);

                allowInput = false;

                resetButtons();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final int[] i = {0};
        int timerSize = 1000 * (gameState.size()+1);
        new CountDownTimer(timerSize, 1000) {

            @Override
            public void onTick(long arg0) {
                switch (gameState.get(i[0]))
                {
                    case RED:
                        activateButtonForMillis(Game.Colour.RED, 1000);
                        break;
                    case GREEN:
                        activateButtonForMillis(Game.Colour.GREEN, 1000);
                        break;
                    case BLUE:
                        activateButtonForMillis(Game.Colour.BLUE, 1000);
                        break;
                    case YELLOW:
                        activateButtonForMillis(Game.Colour.YELLOW, 1000);
                        break;
                }

                i[0]++;
            }

            @Override
            public void onFinish() {
                allowInput = true;
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(resultCode)
        {
            case RESULT_CLOSE_ALL:
                setResult(RESULT_CLOSE_ALL);
                finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
