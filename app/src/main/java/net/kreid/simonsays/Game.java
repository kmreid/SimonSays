package net.kreid.simonsays;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kevin on 17/05/2015.
 */
public class Game
{
    private List<Colour> _gameChoices;
    private int _stepIndex;
    private int _score;

    public Game()
    {
        _gameChoices = new ArrayList<Colour>();
        _stepIndex = 0;
        _score = 0;
    }

    public void iterate()
    {
        Colour nextColour = randomEnum(Colour.class);
        _gameChoices.add(nextColour);
        _stepIndex = 0;
    }

    public void restart()
    {
        _gameChoices = new ArrayList<Colour>();
        _score = 0;
        iterate();
    }

    public List<Colour> getGameState()
    {
        return _gameChoices;
    }

    public int getScore()
    {
        return _score;
    }

    public Boolean makeSelection(Colour choice)
    {
        Boolean correctColour = _gameChoices.get(_score) == choice;

        if(_score+1 == _gameChoices.size() && correctColour)
        {
            _score++;
            return true;
        }

        return false;
    }

    public enum Colour
    {
        RED,
        GREEN,
        YELLOW,
        BLUE
    }

    private static <T extends Enum<?>> T randomEnum(Class<T> enumClass)
    {
        Random random = new Random();
        int x = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[x];
    }
}
