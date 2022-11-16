package edu.cmu.cs214.hw1;

import edu.cmu.cs214.hw1.cli.UI;
import edu.cmu.cs214.hw1.data.CardLoader;
import edu.cmu.cs214.hw1.data.InMemoryCardStore;
import edu.cmu.cs214.hw1.ordering.CardDeck;
import edu.cmu.cs214.hw1.ordering.CardOrganizer;
import edu.cmu.cs214.hw1.ordering.MultiOrganizer;
import edu.cmu.cs214.hw1.ordering.prioritization.CardShuffler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.cmu.cs214.hw1.ordering.prioritization.MostMistakesFirstSorter;
import edu.cmu.cs214.hw1.ordering.prioritization.RecentMistakesFirstSorter;
import edu.cmu.cs214.hw1.ordering.repetition.NonRepeatingCardOrganizer;
import edu.cmu.cs214.hw1.ordering.repetition.RepeatingCardOrganizer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;

import static java.lang.System.exit;


public final class Main {

    private Main() {
        // Disable instantiating this class.
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) throws IOException{
        System.out.println(args);
        int timesUsed = 1;
        String orderUsed = "random";
        boolean learnTitleUsed = false;
        Options options = new Options();

        //add option "--help"
        options.addOption("h", "help", false, "Show this help");

        //add option "--version"
        options.addOption("v", "version", false, "show version number");

        //add option "--order"
        options.addOption("o","order", true, "The type of ordering to use");

        //add option "--repetition"
        options.addOption("r", "repetitions", true, "<num> The number of times to each " +
                "card should be answered");

        //add option "--learn-titles"
        options.addOption("t", "learn-titles", false, "provide definition and title");

        //create a parser
        CommandLineParser parser = new DefaultParser();

        try{
            //parse the command line arguments
            CommandLine cmd = parser.parse( options, args);

            if(cmd.hasOption("help")){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("args", options);
            }
            if(cmd.hasOption("version")){
                System.out.println("1.0");
            }
            if(cmd.hasOption("order")){
                orderUsed = cmd.getOptionValue("order");

                if(!(orderUsed.equals("random")| orderUsed.equals("worst-first") | orderUsed.equals("last-mistakes-first"))){
                    throw new ParseException("the order is invalid");
                }
            }
            if(cmd.hasOption("repetitions")){
                timesUsed = Integer.parseInt(cmd.getOptionValue("repetitions"));

                if(timesUsed < 0){ // if times less than zero, throw exception
                    throw new ParseException("repetition is invalid");
                }
            }
            if(cmd.hasOption("learn-titles")){
                learnTitleUsed = true;
            }
        }
        catch(ParseException exp){
            System.out.println((new StringBuilder().append("Invalid Input: ").append(exp.getMessage())).toString());
            exit(0);
        }

        //set up options, extract command line arguments, fill in the relevant objects based on it.
        InMemoryCardStore cards = (InMemoryCardStore)(new CardLoader().loadCardsFromFile(new File(args[0]), learnTitleUsed));

        CardDeck cardDeck = new CardDeck(cards.getAllCards(), new NonRepeatingCardOrganizer());
        List<CardOrganizer> list = new ArrayList<>();

        if(timesUsed != 1){
            switch (orderUsed) {
                case "worst-first" -> {
                    list.add(new MostMistakesFirstSorter());
                    list.add(new RepeatingCardOrganizer(timesUsed));
                }
                case "last-mistakes-first" -> {
                    list.add(new RecentMistakesFirstSorter());
                    list.add(new RepeatingCardOrganizer(timesUsed));
                }
                default -> {
                    list.add(new CardShuffler());
                    list.add(new RepeatingCardOrganizer(timesUsed));
                }
            }
            CardOrganizer multiOrganizer = new MultiOrganizer(list);
            cardDeck = new CardDeck(cards.getAllCards(), multiOrganizer);
        }
        new UI().studyCards(cardDeck);
    }

}
