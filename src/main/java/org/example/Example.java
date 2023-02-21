package org.example;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Example {
    public static void main(String[] args) throws IOException {
        Orianna.setRiotAPIKey(get_API());
        Orianna.setDefaultRegion(Region.EUROPE_WEST);

        System.out.println("Welcome to RIOT Terminal");
        Summoner summoner = Orianna.summonerNamed(get_IGN()).get();

        //System.out.println(summoner.getChampionMasteries(Champion.named("Katarina").get()));
        //System.out.println(summoner.getChampionMasteries());
        System.out.println(summoner.getLeaguePositions());


        System.out.println(summoner.getName() + " is level " + summoner.getLevel() + " on the " + summoner.getRegion() + " server.");

        Champions champions = Orianna.getChampions();
        Champion randomChampion = champions.get((int)(Math.random() * champions.size()));
        System.out.println("He enjoys playing champions such as " + randomChampion.getName());



        League challengerLeague = Orianna.challengerLeagueInQueue(Queue.RANKED_SOLO).get();
        Summoner bestNA = challengerLeague.get(0).getSummoner();
        System.out.println("He's not as good as " + bestNA.getName() + " at League, but probably a better Java programmer!");
    }

    private static String get_API() throws IOException {

        String API = (new BufferedReader(new FileReader("src/api.txt"))).readLine();

        System.out.println(API);

        return API;
    }

    private static String get_IGN(){

        Scanner sc = new Scanner(System.in);

        System.out.println("What's your summoner name?");

        return sc.nextLine();



    }



}
