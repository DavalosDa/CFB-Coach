/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CFBsimPack;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Class for the safety player. One on field.
 * @author Achi
 */
public class PlayerS extends Player {
    
    //public String name;
    //Overall rating, combination of other ratings
    //public int ratOvr;
    //Potential, affects how much he gets better in offseason
    //public int ratPot;
    //FootIQ, affects how smart he plays and how involved he is in each play
    //public int ratFootIQ;
    //CBCov affects how good he is at covering the pass
    public int ratSCov;
    //CBSpd affects how good he is at not letting up deep passes
    public int ratSSpd;
    //CBTkl affects how good he is at tackling
    public int ratSTkl;

    //Stats
    public int statsTackles;
    public int statsSacks;
    public int statsFumbles;
    public int statsInts;

    public int careerTackles;
    public int careerSacks;
    public int careerFumbles;
    public int careerInts;
    
    //public Vector ratingsVector;
    
    public PlayerS( String nm, Team t, int yr, int pot, int iq, int cov, int spd, int tkl, boolean rs, int dur ) {
        team = t;
        name = nm;
        year = yr;
        gamesPlayed = 0;
        isInjured = false;
        ratOvr = (cov*2 + spd + tkl)/4;
        ratPot = pot;
        ratFootIQ = iq;
        ratDur = dur;
        ratSCov = cov;
        ratSSpd = spd;
        ratSTkl = tkl;
        isRedshirt = rs;
        if (isRedshirt) year = 0;
        position = "S";

        cost = (int)(Math.pow((float)ratOvr - 55,2)/3.5) + 125 + (int)(Math.random()*100) - 50;
        
        ratingsVector = new Vector();
        ratingsVector.addElement(name+" ("+getYrStr()+")");
        ratingsVector.addElement(ratOvr+" (+"+ratImprovement+")");
        ratingsVector.addElement(ratPot);
        ratingsVector.addElement(ratFootIQ);
        ratingsVector.addElement(ratSCov);
        ratingsVector.addElement(ratSSpd);
        ratingsVector.addElement(ratSTkl);

        wonHeisman = false;
        wonAllAmerican = false;
        wonAllConference = false;
        statsWins = 0;

        careerGamesPlayed = 0;
        careerHeismans = 0;
        careerAllAmerican = 0;
        careerAllConference = 0;
        careerWins = 0;

        statsTackles = 0;
        statsSacks = 0;
        statsFumbles = 0;
        statsInts = 0;

        careerTackles = 0;
        careerSacks = 0;
        careerFumbles = 0;
        careerInts = 0;
    }

    public PlayerS( String nm, Team t, int yr, int pot, int iq, int cov, int spd, int tkl, boolean rs, int dur,
                    int cGamesPlayed, int cTackles, int cSacks, int cFumbles, int cInts, int cHeismans, int cAA, int cAC, int cWins ) {
        team = t;
        name = nm;
        year = yr;
        gamesPlayed = 0;
        isInjured = false;
        ratOvr = (cov*2 + spd + tkl)/4;
        ratPot = pot;
        ratFootIQ = iq;
        ratDur = dur;
        ratSCov = cov;
        ratSSpd = spd;
        ratSTkl = tkl;
        isRedshirt = rs;
        if (isRedshirt) year = 0;
        position = "S";

        cost = (int)(Math.pow((float)ratOvr - 55,2)/3.5) + 125 + (int)(Math.random()*100) - 50;

        ratingsVector = new Vector();
        ratingsVector.addElement(name+" ("+getYrStr()+")");
        ratingsVector.addElement(ratOvr+" (+"+ratImprovement+")");
        ratingsVector.addElement(ratPot);
        ratingsVector.addElement(ratFootIQ);
        ratingsVector.addElement(ratSCov);
        ratingsVector.addElement(ratSSpd);
        ratingsVector.addElement(ratSTkl);

        wonHeisman = false;
        wonAllAmerican = false;
        wonAllConference = false;
        statsWins = 0;

        careerGamesPlayed = cGamesPlayed;
        careerHeismans = cHeismans;
        careerAllAmerican = cAA;
        careerAllConference = cAC;
        careerWins = cWins;

        statsTackles = 0;
        statsSacks = 0;
        statsFumbles = 0;
        statsInts = 0;

        careerTackles = cTackles;
        careerSacks = cSacks;
        careerFumbles = cFumbles;
        careerInts = cInts;

    }
    
    public PlayerS( String nm, int yr, int stars, Team t ) {
        name = nm;
        year = yr;
        team = t;
        gamesPlayed = 0;
        isInjured = false;
        ratPot = (int) (50 + 50*Math.random());
        ratFootIQ = (int) (50 + 50*Math.random());
        ratDur = (int) (50 + 50*Math.random());
        ratSCov = (int) (60 + year*5 + stars*5 - 25*Math.random());
        ratSSpd = (int) (60 + year*5 + stars*5 - 25*Math.random());
        ratSTkl = (int) (60 + year*5 + stars*5 - 25*Math.random());
        ratOvr = (ratSCov*2 + ratSSpd + ratSTkl)/4;
        position = "S";

        cost = (int)(Math.pow((float)ratOvr - 55,2)/3.5) + 125 + (int)(Math.random()*100) - 50;
        
        ratingsVector = new Vector();
        ratingsVector.addElement(name+" ("+getYrStr()+")");
        ratingsVector.addElement(ratOvr+" (+"+ratImprovement+")");
        ratingsVector.addElement(ratPot);
        ratingsVector.addElement(ratFootIQ);
        ratingsVector.addElement(ratSCov);
        ratingsVector.addElement(ratSSpd);
        ratingsVector.addElement(ratSTkl);

        wonHeisman = false;
        wonAllAmerican = false;
        wonAllConference = false;
        statsWins = 0;

        careerGamesPlayed = 0;
        careerHeismans = 0;
        careerAllAmerican = 0;
        careerAllConference = 0;
        careerWins = 0;

        statsTackles = 0;
        statsSacks = 0;
        statsFumbles = 0;
        statsInts = 0;

        careerTackles = 0;
        careerSacks = 0;
        careerFumbles = 0;
        careerInts = 0;
    }

    public Vector getStatsVector() {
        Vector v = new Vector(4);
        v.add(statsTackles);
        v.add(statsSacks);
        v.add(statsFumbles);
        v.add(statsInts);
        return v;
    }

    public Vector getRatingsVector() {
        ratingsVector = new Vector();
        ratingsVector.addElement(name+" ("+getYrStr()+")");
        ratingsVector.addElement(ratOvr+" (+"+ratImprovement+")");
        ratingsVector.addElement(ratPot);
        ratingsVector.addElement(ratFootIQ);
        ratingsVector.addElement(ratSCov);
        ratingsVector.addElement(ratSSpd);
        ratingsVector.addElement(ratSTkl);
        return ratingsVector;
    }
    
    @Override
    public void advanceSeason() {
        year++;
        int oldOvr = ratOvr;
        if (wonAllConference) ratPot++;
        if (wonAllAmerican) ratPot++;
        if (year > 2 && gamesPlayed < 4) ratPot -= (int)Math.random()*15;

        ratFootIQ += (int)(Math.random()*(ratPot + gamesPlayed - 35))/10;
        ratSCov += (int)(Math.random()*(ratPot + gamesPlayed - 35))/10;
        ratSSpd += (int)(Math.random()*(ratPot + gamesPlayed - 35))/10;
        ratSTkl += (int)(Math.random()*(ratPot + gamesPlayed - 35))/10;
        if ( Math.random()*100 < ratPot ) {
            //breakthrough
            ratSCov += (int)(Math.random()*(ratPot + gamesPlayed - 40))/10;
            ratSSpd += (int)(Math.random()*(ratPot + gamesPlayed - 40))/10;
            ratSTkl += (int)(Math.random()*(ratPot + gamesPlayed - 40))/10;
        }
        ratOvr = (ratSCov*2 + ratSSpd + ratSTkl)/4;
        ratImprovement = ratOvr - oldOvr;

        careerGamesPlayed += gamesPlayed;
        careerWins += statsWins;

        careerTackles += statsTackles;
        careerSacks += statsSacks;
        careerFumbles += statsFumbles;
        careerInts += statsInts;

        statsTackles = 0;
        statsSacks = 0;
        statsFumbles = 0;
        statsInts = 0;

        if (wonHeisman) careerHeismans++;
        if (wonAllAmerican) careerAllAmerican++;
        if (wonAllConference) careerAllConference++;
    }

    @Override
    public int getHeismanScore() {
        return statsTackles*35 + statsSacks*250 + statsFumbles*500 + statsInts*500 + 12*ratOvr - (int)(4*team.teamOppYards) - (int)(5*team.teamOppPoints);
    }
    @Override
    public ArrayList<String> getDetailStatsList(int games) {
        ArrayList<String> pStats = new ArrayList<>();
        pStats.add("Tackles: " + (statsTackles) + " >Sacks: " + (statsSacks));
        pStats.add("Fumbles: " + (statsFumbles) + " >Interceptions: " + (statsInts));
        pStats.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed-statsWins) + ")" + ">Durability: " + getLetterGrade(ratDur));
        pStats.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Coverage: " + getLetterGrade(ratSCov));
        pStats.add("Speed: " + getLetterGrade(ratSSpd) + ">Tackling: " + getLetterGrade(ratSTkl));
        pStats.add(" > ");
        return pStats;
    }

    @Override
    public ArrayList<String> getDetailAllStatsList(int games) {
        ArrayList<String> pStats = new ArrayList<>();
        pStats.add("Tackles: " + (statsTackles) + " >Sacks: " + (statsSacks));
        pStats.add("Fumbles: " + (statsFumbles) + " >Interceptions: " + (statsInts));
        pStats.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed-statsWins) + ")" + ">Durability: " + getLetterGrade(ratDur));
        pStats.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Coverage: " + getLetterGrade(ratSCov));
        pStats.add("Speed: " + getLetterGrade(ratSSpd) + ">Tackling: " + getLetterGrade(ratSTkl));
        pStats.add("[B]CAREER STATS:");
        pStats.addAll(getCareerStatsList());
        return pStats;
    }

    @Override
    public ArrayList<String> getCareerStatsList() {
        ArrayList<String> pStats = new ArrayList<>();
        pStats.add("Tackles: " + (statsTackles + careerTackles) + " >Sacks: " + (statsSacks + careerSacks));
        pStats.add("Fumbles: " + (statsFumbles + careerFumbles) + " >Interceptions: " + (statsInts + careerInts));
        pStats.addAll(super.getCareerStatsList());
        return pStats;
    }


    @Override
    public String getInfoForLineup() {
        if (injury != null) return getInitialName() + " [" + getYrStr() + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " " + injury.toString();
        return getInitialName() + " [" + getYrStr() + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" +
                getLetterGrade(ratSCov) + ", " + getLetterGrade(ratSSpd) + ", " + getLetterGrade(ratSTkl) + ")";
    }
    
}
