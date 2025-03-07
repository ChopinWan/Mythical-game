import java.io.*;
import java.util.Random;
import java.util.Scanner;

class Creature 
{
    String name;
    int hunger;
    int anger;
    int killer;
} // END class Creature

class creatureGame
{
    // Returns the name of the given creature.
    public static String getName(Creature c) {
        return c.name;
    } // END getName

    // Returns the hunger level of the given creature.
    public static int getHunger(Creature c) {
        return c.hunger;
    } // END getHunger

    // Returns the anger level of the given creature.
    public static int getAnger(Creature c) {
        return c.anger;
    } // END getAnger

    // Returns the killer score of the given creature.
    public static int getKiller(Creature c) {
        return c.killer;
    } // END getKiller

    // Sets the name of the given creature and returns the updated creature.
    public static Creature setName (Creature c, String nm) {
        c.name = nm;
        return c;
    } // END setName

    // Sets the hunger level of the given creature and returns the updated creature.
    public static Creature setHunger (Creature c, int hun) {
        c.hunger = hun;
        return c;
    } // END setHunger

    // Sets the anger level of the given creature and returns the updated creature.
    public static Creature setAnger (Creature c, int ang) {
        c.anger = ang;
        return c;
    } // END setAnger

    // Sets the killer score of the given creature and returns the updated creature.
    public static Creature setKiller (Creature c, int kil) {
        c.killer = kil;
        return c;
    } // END setKiller
    
    public static void main (String [] a) throws IOException
    {
        Creature creature = setCreatureDetails();
        outputAll(creature);
        return;
    } // END main
    
   public static String inputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    } // END inputString

    public static int inputInt(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    } // END inputInt

    public static Creature setCreatureDetails ()
    {
        Creature creature = new Creature();
        creature.name = "";
        creature.hunger = 0;
        creature.anger = 0;
        creature.killer = 0;

        return creature;
    } // END setCreatureDetails

    // Checks if the name inputted by the user is one of the options presented, if not it will repeat the users input question
    public static boolean validatingName(Creature creature, String [] name_choices)
    {
        for(int i = 0; i < name_choices.length; i++) {
            if(getName(creature).equals(name_choices[i])) {
                return true;
            }
        }
        System.out.println("Sorry that wasn't an option, try again.");
            return false;
    } // END validatingName
    
    // Introduces creatures and asks user to state the name of the mythical creature they chose.
    public static void introduction(Creature creature)
    {
        System.out.println("Welcome to your personal mythical creature. Here you will care for your creature, keep track of their health and many more things.");
        System.out.println();

        String[] name_choices = {"Minotaur", "Unicorn", "Dragon", "Griffith", "Leprechaun"};
        setName(creature, inputString("But first can you name your mythical creature of choice (options being: Minotaur, Unicorn, Dragon, Griffith, Leprechaun)"));
        boolean validName = validatingName(creature, name_choices);
        
        
        while(!validName) {
        setName(creature, inputString("But first can you name your mythical creature of choice (options being: Minotaur, Unicorn, Dragon, Griffith, Leprechaun)"));
        validName = validatingName(creature, name_choices);
        }
        
        System.out.println("What a great choice! The " + getName(creature) + " is an amazing creature.");
        System.out.println();
        return;
    } // END introduction

    // Picks a random number from 1-5 and prints this as the hunger rating.
    public static Creature hungerBar(Creature c)
    {
        Random random = new Random();
        setHunger(c, random.nextInt(5) + 1);
        System.out.println("Your " + getName(c) + "'s hunger rating is at " + getHunger(c) + "/5");
        return c;
    } // END hungerBar
   
    // Picks a random number from 1-5 and prints this as the anger rating.
    public static Creature angerBar(Creature c)
    {
        Random random = new Random();
        setAnger(c, random.nextInt(5) + 1);
        System.out.println("Your " + getName(c) + "'s anger rating is at " + getAnger(c) + "/5");
        return c;
    } // END angerBar

    
    // Creates a killer score which is a combination of hunger and anger, depending on the killer score tells the state of the creature.
    public static void killerBar(Creature creature)
    {
        setKiller(creature, getHunger(creature) + getAnger(creature));
        System.out.println("Your " + getName(creature) + "'s killer rating is at " + getKiller(creature) + "/10");
            
        if (getKiller(creature) >= 0 && getKiller(creature) <= 4)
        {
            System.out.println("So your " + getName(creature) + " is peaceful");
        }
        else if (getKiller(creature) > 4 && getKiller(creature) <= 6)
        {
            System.out.println("So your " + getName(creature) + " is slightly agitated");
        }
        else if (getKiller(creature) > 6 && getKiller(creature) <= 8)
        {
            System.out.println("So your " + getName(creature) + " is aggressive");
        }
        else if (getKiller(creature) > 8 && getKiller(creature) <= 10)
        {
            System.out.println("So your " + getName(creature) + " is rabid");
        }
        return;
    } // END killerBar

    // This method gives multiple actions for you to interact with the mythical creature and based on the actions the hunger and anger are changed.
    public static void actionsOnPet(Creature creature, String []name_choices, String []food_choices, String []item_choices, int roundNum)
    {
        String action = inputString("Round " + roundNum + ": What do you want to do with your pet? (options: feed, give item, pet)");

            if (action.equals("feed"))
            {
                String food = inputString("What would you like to feed your creature? (berries, grass, lamb, fish, mushrooms)");

                boolean validFood = false;
                for (int j = 0; j < name_choices.length; j++) { 
                    if (getName(creature).equals(name_choices[j]) && food.equals(food_choices[j])) {
                    setHunger(creature, Math.max(0, getHunger(creature) - 3));
                    validFood = true;
                    }
                }
                if (!validFood){
                    setHunger(creature, Math.max(0, getHunger(creature) + 2));
                }
            }
            else if (action.equals("give item"))
            {
                String item = inputString("What item would you like to give to your creature? (axe, magical mirror, obsidian claws, golden feathers, gold bar)");

                boolean validItem = false;
                for (int j = 0; j < item_choices.length; j++) {
                    if (getName(creature).equals(name_choices[j]) && item.equals(item_choices[j])) {
                    setAnger(creature, Math.max(0, getAnger(creature) - 3));
                    validItem = true;
                    }
                }
                if (!validItem){
                    setAnger(creature, Math.max(0, getAnger(creature) + 2));
                }
            }
            else if (action.equals("pet"))
            {
                setAnger(creature, Math.max(0, getAnger(creature) + 2));
            }
        return;
    } // END actionsOnPet

    // Asks user if they'd like to play the game again.
    public static String continuation (String continue_game)
    {
        continue_game = inputString("Would you like to play again (Y/N)?");
        System.out.println();
        return continue_game.toUpperCase();
    } // END continuation

    // Asks user if they'd like to load a new or old file.
    public static String start (String start_game)
    {
        start_game = inputString("Would you like to start a new game, or load the previous file? (new/load)");
        System.out.println();
        return start_game;
    } // END start

    // If user ends the game with a peaceful state, they gain 2 points. If they end on slighty adgitated they score 1 point, otherwise no points.
    public static int currentScore (Creature creature, int score)
    {
        if (getKiller(creature) >= 0 && getKiller(creature) <= 4)
        {
            score += 2;
        }
        else if (getKiller(creature) > 4 && getKiller(creature) <= 6)
        {
            score += 1;
        }
        return score;
    } // END currentScore

    // Once user decides to not loop game anymore, a message is printed to show how many points they scored.
    public static void endGame (int score)
    {
        if(score == 1)
        {
            System.out.println("Game Over. Well done you scored 1 point!");
        }
        if(score == 0)
        {
            System.out.println("Game Over. Unfortunately you scored 0 points...");
        }
        else
        {
            System.out.println("Game Over. Well done you scored " + score +  " points!");
        }
        return;
    } // END endGame

    // Saves all the info each time a round is played.
    public static void saveGame (Creature creature, int roundNum, int score, String fileName) throws IOException
    {
        PrintWriter writer = new PrintWriter (new FileWriter(fileName));
        writer.println(roundNum);
        writer.println(score);
        writer.println(getName(creature));
        writer.println(getHunger(creature));
        writer.println(getAnger(creature));
        writer.println(getKiller(creature));

        writer.close();
        return;
    } // END saveGame

    // This loads a previous save file of the game.
    public static int[] loadGame (Creature creature, int roundNum, int score, String fileName) throws IOException
    {
        int [] results = new int [2];
        BufferedReader reader = new BufferedReader (new FileReader(fileName));
        
        results[0] = Integer.parseInt(reader.readLine());
        results[1] = Integer.parseInt(reader.readLine());
        setName(creature, reader.readLine());
        setHunger(creature, Integer.parseInt(reader.readLine()));
        setAnger(creature, Integer.parseInt(reader.readLine()));
        setKiller(creature, Integer.parseInt(reader.readLine()));
        
        reader.close();
        return results;
    } // END loadGame

    // This loads a new save file of the game.
    public static int[] newGame (Creature creature, int roundNum, int score, String fileName) throws IOException
    {
        int [] newResults = new int [2];
    
        PrintWriter writer = new PrintWriter (new FileWriter(fileName));
        writer.println(newResults[0] = 1);
        writer.println(newResults[1] = 0);
        writer.println(setName(creature, ""));
        writer.println(setHunger(creature, 0));
        writer.println(setAnger(creature, 0));
        writer.println(setKiller(creature, 0));
        
        writer.close();
        return newResults;
    } // END newGame

    // This method calls the other methods and holds the loop for other methods, with an if statement within the loop. 
    public static void outputAll(Creature creature) throws IOException
    {
        String fileName = "game_save.txt";
        String continue_game = "Y";
        String start_game = "n";
        int roundNum = 1;
        int score = 0;

        while (!start_game.equals("new") && !start_game.equals("load")) {
            start_game = start(start_game);
            if (start_game.equals("new")) {
                int [] newResults = newGame(creature, roundNum, score, fileName);
                roundNum = newResults [0];
                score = newResults[1];
    
                introduction(creature);
            }
            
            else if (start_game.equals("load")) {
                int [] results = loadGame(creature, roundNum, score, fileName);
                roundNum = results [0];
                score = results[1];
            }
            else {
                System.out.println("Incorrect input, try again new or load game?");
            }
        }
            
        String [] name_choices = {"Minotaur", "Unicorn", "Dragon", "Griffith", "Leprechaun"};
        String [] food_choices = {"berries","grass","lamb", "fish", "mushrooms"};
        String [] item_choices = {"axe", "magical mirror", "obsidian claws", "golden feathers", "gold bar"};
        
        while (continue_game.equals("Y"))
        {
            hungerBar(creature);
            angerBar(creature);
            killerBar(creature);
            
            actionsOnPet(creature, name_choices, food_choices, item_choices, roundNum);

            setHunger(creature, Math.max(0, Math.min(5, getHunger(creature))));
            setAnger(creature, Math.max(0, Math.min(5, getAnger(creature))));
            
            killerBar(creature);
            System.out.println();
            score = currentScore(creature, score);
            roundNum ++;
            saveGame(creature, roundNum, score, fileName);
            
            continue_game = continuation(continue_game);
        }

        endGame(score);
    } // END outputAll
    
} // class END creature