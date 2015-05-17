package net.kreid.simonsays;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

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

                Boolean correct = game.makeSelection(Game.Colour.RED);

                if(correct)
                {
                    playGameState();
                }
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

                Boolean correct = game.makeSelection(Game.Colour.GREEN);

                if(correct)
                {
                    playGameState();
                }
            }
        });

        final Button blueButton = (Button) findViewById(R.id.button_blue);
        blueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(allowInput == false) return;

                MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP3);
                blueButton.setBackgroundResource(R.drawable.btn_blue_pressed);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        blueButton.setBackgroundResource(R.drawable.btn_blue);
                    }
                }, 1000);

                Boolean correct = game.makeSelection(Game.Colour.BLUE);

                if(correct)
                {
                    playGameState();
                }
            }
        });

        final Button yellowButton = (Button) findViewById(R.id.button_yellow);
        yellowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(allowInput == false) return;

                MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP4);
                yellowButton.setBackgroundResource(R.drawable.btn_yellow_pressed);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        yellowButton.setBackgroundResource(R.drawable.btn_yellow);
                    }
                }, 500);

                Boolean correct = game.makeSelection(Game.Colour.YELLOW);

                if(correct)
                {
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        public void run() {
                            playGameState();
                        }
                    }, 2000);

                }
            }
        });
    }

    private void playGameState()
    {
        allowInput = false;

        List<Game.Colour> gameState = game.getGameState();
        final Button redButton = (Button) findViewById(R.id.button_red);
        final Button greenButton = (Button) findViewById(R.id.button_green);
        final Button blueButton = (Button) findViewById(R.id.button_blue);
        final Button yellowButton = (Button) findViewById(R.id.button_yellow);

        for(int i = 0; i < gameState.size(); i++)
        {
            switch (gameState.get(i))
            {
                case RED:
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP1);
                            redButton.setBackgroundResource(R.drawable.btn_red);
                        }
                    }, 500);

                    break;
                case GREEN:
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        public void run() {
                            MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP2);
                            greenButton.setBackgroundResource(R.drawable.btn_green);
                        }
                    }, 500);
                    break;
                case BLUE:
                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        public void run() {
                            MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP3);
                            blueButton.setBackgroundResource(R.drawable.btn_blue);
                        }
                    }, 500);
                    break;
                case YELLOW:
                    Handler handler4 = new Handler();
                    handler4.postDelayed(new Runnable() {
                        public void run() {
                            MainActivity.sounds.PlaySound(Sounds.SoundType.BEEP4);
                            yellowButton.setBackgroundResource(R.drawable.btn_yellow);
                        }
                    }, 500);
                    break;
            }
        }

        allowInput = true;
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
