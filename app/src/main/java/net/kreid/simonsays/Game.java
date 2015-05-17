package net.kreid.simonsays;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kevin on 17/05/2015.
 */
public class Game {

    private List<Colour> _playerChoices;
    private List<Colour> _gameChoices;
    private int _stepIndex;

    public Game()
    {
        _playerChoices = new ArrayList<Colour>();
        _gameChoices = new ArrayList<Colour>();
        _stepIndex = 0;
    }

    public void iterate()
    {
        Colour nextColour = randomEnum(Colour.class);
        _gameChoices.add(nextColour);
        _stepIndex = 0;
        _playerChoices = new ArrayList<Colour>();
    }

    public void restart()
    {
        _gameChoices = new ArrayList<Colour>();
        iterate();
    }

    public List<Colour> getGameState()
    {
        return _gameChoices;
    }

    public Boolean makeSelection(Colour choice)
    {
        _playerChoices.add(choice);

        Boolean correct = _gameChoices.get(_stepIndex) == choice;

        if(correct)
        {
            _stepIndex++;
        }

        return correct;
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
