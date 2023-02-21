package org.example;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Example {

    private static Summoner summoner;



    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to RIOT Terminal");
        Orianna.setRiotAPIKey(get_API());
        Orianna.setDefaultRegion(Region.EUROPE_WEST);
        summoner = Summoner.named(get_IGN()).withRegion(Region.EUROPE_WEST).get();
        SummonerID();
        CHAMPION_CARD();
        getIMG();
    }




    private static void getIMG() throws IOException {
        DisplayImage dp = new DisplayImage();
    }

    private static void SummonerID(){
        System.out.println("Name: " + summoner.getName());
        System.out.println("ID: " + summoner.getId());
        System.out.println("Account ID: " + summoner.getAccountId());
        System.out.println("Level: " + summoner.getLevel());
        System.out.println("Last Updated: " + summoner.getUpdated());
        System.out.println("Profile Icon ID: " + summoner.getProfileIcon().getId());
        System.out.println("Profile Icon URL: " + summoner.getProfileIcon().getImage().getURL());
    }

    private static void CHAMPION_CARD(){
        Champion taliyah = Champion.named("Jax").withRegion(Region.EUROPE_WEST).get();
        ChampionMastery cm = summoner.getChampionMastery(taliyah);
        System.out.println("Champion ID: " + cm.getChampion().getId());
        System.out.println("Mastery points: " + cm.getPoints());
        System.out.println("Mastery level: " + cm.getLevel());
        System.out.println("Points until next level: " + cm.getPointsUntilNextLevel());
        ChampionMasteries cms = summoner.getChampionMasteries();
        System.out.println(((ChampionMastery)cms.get(3)).getPoints());
        System.out.println(((ChampionMastery)cms.find(taliyah.getName())).getPoints());
        System.out.println(summoner.getName() + " has mastery level 6 or higher on:");
        SearchableList<ChampionMastery> pro = cms.filter((masteryx) -> {
            return masteryx.getLevel() >= 6;
        });
        Iterator var6 = pro.iterator();

        while(var6.hasNext()) {
            ChampionMastery mastery = (ChampionMastery)var6.next();
            System.out.println(mastery.getChampion().getName());
        }
    }


    public static String get_API() throws IOException {

        String API = (new BufferedReader(new FileReader("src/api.txt"))).readLine();

        System.out.println(API);

        return API;
    }

    private static String get_IGN(){

        Scanner sc = new Scanner(System.in);

        System.out.print("What's your summoner name? ");

        return sc.nextLine();



    }

    public static String get_IMG_URL(){

        return summoner.getProfileIcon().getImage().getURL();
    }



}
