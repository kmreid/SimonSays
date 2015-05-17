package net.kreid.simonsays;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Kevin on 16/05/2015.
 */
public class Sounds
{
    // TODO - make singleton

    private SoundPool _pool;

    Dictionary<SoundType, Integer> _sounds;

    public Sounds(Context cxt)
    {
        _pool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        int sRed = _pool.load(cxt, R.raw.beep1, 1);
        int sBlue = _pool.load(cxt, R.raw.beep2, 1);
        int sGreen = _pool.load(cxt, R.raw.beep3, 1);
        int sYellow = _pool.load(cxt, R.raw.beep4, 1);

        _sounds = new Hashtable<SoundType, Integer>();
        _sounds.put(SoundType.BEEP1, sRed);
        _sounds.put(SoundType.BEEP2, sBlue);
        _sounds.put(SoundType.BEEP3, sGreen);
        _sounds.put(SoundType.BEEP4, sYellow);
    }

    public void PlaySound(SoundType type)
    {
        int sound = _sounds.get(type);
        _pool.play(sound, 1.0f, 1.0f, 0, 0, 1.5f);
    }

    public enum SoundType
    {
        BEEP1, BEEP2, BEEP3, BEEP4
    }
}
