package CFBsimPack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.lang.StringBuilder;

/**
 * Class for conferences, which each have 10 teams.
 * @author Achi
 */
public class Conference {

    public String confName;
    public int confPrestige;

    public ArrayList<Team> confTeams;
    public boolean evenYear;

    public League league;

    private Game ccg;

    public int week;
    public int robinWeek;
    int teamCount = 12;

    public String allConfStr;
    public ArrayList<Player> allConfPlayers;

    /**
     * Sets up Conference with empty list of teams.
     * @param name
     * @param league
     */
    public Conference( String name, League league ) {
        confName = name;
        confPrestige = 75;
        confTeams = new ArrayList<Team>();
        this.league = league;
        week = 0;
        robinWeek = 0;
        allConfPlayers = new ArrayList<Player>();
    }

    /**
     * Sets up schedule for in-conference games using round robin scheduling.
     */
    public void setUpSchedule() {
        //schedule in conf matchups
        robinWeek = 0;
        evenYear = (league.leagueHistory.size()%2==0);


        // Set up int arrays for each team's home/away rotation.
        // Theoretically every year you should change off between having 5 home games and 4 away games in conference
        if (league.leagueHistory.size() == 0 || confTeams.get(0).evenYearHomeOpp == null) {
            int[][] evenHomeGames = new int[12][];
            evenHomeGames[0] = new int[]{11, 7, 3, 10, 6};
            evenHomeGames[1] = new int[]{8, 4, 0, 9};
            evenHomeGames[2] = new int[]{9, 5, 1, 8, 10};
            evenHomeGames[3] = new int[]{6, 2, 10, 7};
            evenHomeGames[4] = new int[]{7, 3, 11, 6};
            evenHomeGames[5] = new int[]{4, 0, 8, 11};
            evenHomeGames[6] = new int[]{5, 1, 9, 2};
            evenHomeGames[7] = new int[]{2, 10, 6, 5, 1};
            evenHomeGames[8] = new int[]{3, 11, 7, 0};
            evenHomeGames[9] = new int[]{0, 8, 4, 3, 5};
            evenHomeGames[10] = new int[]{1, 9, 5, 4};
            evenHomeGames[11] = new int[]{10, 6, 2, 1, 3};


            for (int x = 0; x < evenHomeGames.length; x++) {
                StringBuilder sb = new StringBuilder();
                for (int y = 0; y < evenHomeGames[x].length; y++) {
                   sb.append(confTeams.get(evenHomeGames[x][y]).abbr + ",");
                }
                confTeams.get(x).evenYearHomeOpp = sb.toString();
            }
        }
        for (int r = 0; r < 9; ++r) {
            for (int g = 0; g < ((teamCount+1)/2); ++g) {
                Team a = confTeams.get((robinWeek + g) % (teamCount-1));
                Team b;
                if ( g == 0 ) {
                    b = confTeams.get((teamCount-1));
                } else {
                    b = confTeams.get(((teamCount-1) - g + robinWeek) % (teamCount-1));
                }

                Game gm;

                // Check whether it's an even year and if team B appears in team A's even year home game list, or if it's not an even year and team A appears in team B's list

                if ( (evenYear && a.evenYearHomeOpp.contains(b.abbr)) || (evenYear && !b.evenYearHomeOpp.contains(a.abbr)) || (!evenYear && !a.evenYearHomeOpp.contains((b.abbr))) || (!evenYear && b.evenYearHomeOpp.contains((a.abbr))) ) {
                    if (a.rivalTeam.equals(b.abbr)) {
                        gm = new Game( a, b, "Rivalry Game");}
                    else {
                        gm = new Game(a, b, "Conference");
                    }
                }

                // Basically check all the reverse scenarios above, anything that would cause B to be the home team.
                else if((evenYear && b.evenYearHomeOpp.contains(a.abbr) || (evenYear && !a.evenYearHomeOpp.contains(b.abbr)) || (!evenYear && a.evenYearHomeOpp.contains((b.abbr))) || (!evenYear && !b.evenYearHomeOpp.contains(a.abbr)))) {
                        if (a.rivalTeam.equals(b.abbr)) {
                            gm = new Game( b, a, "Rivalry Game");}
                        else {
                            gm = new Game(b, a, "Conference");
                        }
                }
                else{ // I'm 99.9% sure all scenarios and possibilities are covered above, but lets not break the game if I'm wrong
                        if (a.rivalTeam.equals(b.abbr)) {
                            gm = new Game(b, a, "Rivalry Game");
                        }
                        else {
                            gm = new Game(b, a, "Conference");
                        }
                }
                a.gameSchedule.add(gm);
                b.gameSchedule.add(gm);

                if (a.userControlled == true) System.out.println("User Controlled Home Schedule: " + a.evenYearHomeOpp);
            }
            robinWeek++;
        }


    }

    /**
     * Sets up schedule for OOC games, which are played in 3 weeks.
     */
    public void setUpOOCSchedule() {
        //schedule OOC games
        int confNum = -1;
        if ( league.conferences.get(0 + league.randconf).confName.equals(confName) ) {
            confNum = 0 + league.randconf;
        } else if ( league.conferences.get(1 + 2*league.randconf).confName.equals(confName) ) {
            confNum = 1 + 2*league.randconf;
        } else if ( league.conferences.get(2 + 3*league.randconf).confName.equals(confName) ) {
            confNum = 2 + 3*league.randconf;
        } else if ( league.conferences.get(3 + 4*league.randconf).confName.equals(confName) ) {
            confNum = 3 + 4*league.randconf;
        } else if ( league.conferences.get(4 + 5*league.randconf).confName.equals(confName) ) {
            confNum = 4 + 5*league.randconf;
        }

        if ( confNum != -1 && league.randconf == 1) {
            for ( int offsetOOC = 5; offsetOOC < 11; ++offsetOOC ) {
                ArrayList<Team> availTeams = new ArrayList<Team>();
                int selConf = confNum + offsetOOC*2;
                if (selConf == 11) selConf = 0;
                if (selConf == 13) selConf = 2;
                if (selConf == 15) selConf = 4;
                if (selConf == 17) selConf = 6;
                if (selConf == 19) selConf = 8;
                if (selConf == 21) selConf = 0;
                if (selConf == 23) selConf = 2;
                if (selConf == 25) selConf = 4;
                if (selConf == 27) selConf = 6;
                if (selConf == 29) selConf = 8;
                if (selConf == 31) selConf = 0;
                if (selConf == 33) selConf = 2;
                if (selConf == 35) selConf = 4;
                if (selConf == 37) selConf = 6;
                if (selConf == 39) selConf = 8;
                scheduleOOC(offsetOOC, selConf, availTeams);
            }
        }  else if(confNum != -1 && league.randconf == 0) {
            for ( int offsetOOC = 5; offsetOOC < 11; ++offsetOOC ) {
                ArrayList<Team> availTeams = new ArrayList<Team>();
                int selConf = confNum + offsetOOC;
                if (selConf == 10) selConf = 5;
                if (selConf == 11) selConf = 6;
                if (selConf == 12) selConf = 7;
                if (selConf == 13) selConf = 8;
                if (selConf == 14) selConf = 9;
                scheduleOOC(offsetOOC, selConf, availTeams);
            }
        }

    }

    public void scheduleOOC(int offsetOOC,int selConf, ArrayList<Team> availTeams) {
        for (int i = 0; i < teamCount; ++i) {
            availTeams.add( league.conferences.get(selConf).confTeams.get(i) );
        }
        for (int i = 0; i < teamCount; ++i) {
            int selTeam = (int)( availTeams.size() * Math.random() );
            Team a = confTeams.get(i);
            Team b = availTeams.get( selTeam );

            Game gm;
            if ( Math.random() > 0.5 ) {
                gm = new Game( a, b, a.conference + " vs " + b.conference );
            } else {
                gm = new Game( b, a, b.conference + " vs " + a.conference );
            }

            if ( offsetOOC == league.randgm) {
                a.gameOOCSchedule0 = gm;
                b.gameOOCSchedule0 = gm;
                availTeams.remove(selTeam);
            } else if ( offsetOOC == league.randgm + 1) {
                a.gameOOCSchedule1 = gm;
                b.gameOOCSchedule1 = gm;
                availTeams.remove(selTeam);
            } else if ( offsetOOC == league.randgm + 2) {
                a.gameOOCSchedule2 = gm;
                b.gameOOCSchedule2 = gm;
                availTeams.remove(selTeam);
            }
        }
    }

    /**
     * Inserts the OOC games in the 1st, 5th, and 10th weeks. changed to 1, 2, 3 weeks
     */
    public void insertOOCSchedule() {
        for (int i = 0; i < confTeams.size(); ++i) {
            confTeams.get(i).gameSchedule.add(0, confTeams.get(i).gameOOCSchedule0);
            confTeams.get(i).gameSchedule.add(1, confTeams.get(i).gameOOCSchedule1);
            confTeams.get(i).gameSchedule.add(2, confTeams.get(i).gameOOCSchedule2);
        }
    }

    /**
     * Plays week for each team. If CCG week, play the CCG.
     */
    public void playWeek() {
        if ( week == 12 ) {
            playConfChamp();
        } else {
            for ( int i = 0; i < confTeams.size(); ++i ) {
                confTeams.get(i).gameSchedule.get(week).playGame();
            }
            if ( week == 11 ) schedConfChamp();
            week++;
        }
    }

    /**
     * Schedule the CCG based on team rankings.
     */
    public void schedConfChamp() {
        // Play CCG between top 2 teams
        for ( int i = 0; i < confTeams.size(); ++i ) {
            confTeams.get(i).updatePollScore();
        }
        Collections.sort( confTeams, new TeamCompConfWins() );

        int winsFirst = confTeams.get(0).getConfWins();
        Team t = confTeams.get(0);
        int i = 0;
        ArrayList<Team> teamTB = new ArrayList<>();
        while (t.getConfWins() == winsFirst) {
            teamTB.add(t);
            ++i;
            t = confTeams.get(i);
        }
        if (teamTB.size() > 2) {
            // ugh 3 way tiebreaker
            Collections.sort(teamTB, new TeamCompPoll());
            for (int j = 0; j < teamTB.size(); ++j) {
                confTeams.set(j, teamTB.get(j));
            }

        }

        int winsSecond = confTeams.get(1).getConfWins();
        t = confTeams.get(1);
        i = 1;
        teamTB.clear();
        while (t.getConfWins() == winsSecond) {
            teamTB.add(t);
            ++i;
            t = confTeams.get(i);
        }
        if (teamTB.size() > 2) {
            // ugh 3 way tiebreaker
            Collections.sort(teamTB, new TeamCompPoll());
            for (int j = 0; j < teamTB.size(); ++j) {
                confTeams.set(1+j, teamTB.get(j));
            }

        }

        ccg = new Game ( confTeams.get(0), confTeams.get(1), confName + " CCG" );
        confTeams.get(0).gameSchedule.add(ccg);
        confTeams.get(1).gameSchedule.add(ccg);
    }

    /**
     * Play the CCG. Add the "CC" tag to the winner.
     */
    public void playConfChamp() {
        // Play CCG between top 2 teams
        ccg.playGame();
        if ( ccg.homeScore > ccg.awayScore ) {
            confTeams.get(0).confChampion = "CC";
            confTeams.get(0).totalCCs++;
            confTeams.get(1).totalCCLosses++;
            league.newsStories.get(13).add(
                    ccg.homeTeam.name + " wins the " + confName +"!>" +
                    ccg.homeTeam.strRep() + " took care of business in the conference championship against " + ccg.awayTeam.strRep() +
                    ", winning at home with a score of " + ccg.homeScore + " to " + ccg.awayScore + "."
            );
        } else {
            confTeams.get(1).confChampion = "CC";
            confTeams.get(1).totalCCs++;
            confTeams.get(0).totalCCLosses++;
            league.newsStories.get(13).add(
                    ccg.awayTeam.name + " wins the " + confName +"!>" +
                            ccg.awayTeam.strRep() + " surprised many in the conference championship against " + ccg.homeTeam.strRep() +
                            ", winning on the road with a score of " + ccg.awayScore + " to " + ccg.homeScore + "."
            );
        }
        Collections.sort(confTeams, new TeamCompPoll());
    }

    /**
     * String of who is playing in the CCG and the result if available.
     * @return conf champ summary
     */
    public String getCCGStr() {
        if (ccg == null) {
            // Give prediction, find top 2 teams
            Team team1 = null, team2 = null;
            int score1 = 0, score2 = 0;
            for (int i = confTeams.size()-1; i >= 0; --i) { //count backwards so higher ranked teams are predicted
                Team t = confTeams.get(i);
                if (t.getConfWins() >= score1) {
                    score2 = score1;
                    score1 = t.getConfWins();
                    team2 = team1;
                    team1 = t;
                } else if (t.getConfWins() > score2) {
                    score2 = t.getConfWins();
                    team2 = t;
                }
            }
            return confName + " Conference Championship:\n\t\t" +
                    team1.strRep() + " vs " + team2.strRep();
        } else {
            if (!ccg.hasPlayed) {
                return confName + " Conference Championship:\n\t\t" +
                        ccg.homeTeam.strRep() + " vs " + ccg.awayTeam.strRep();
            } else {
                StringBuilder sb = new StringBuilder();
                Team winner, loser;
                sb.append(confName + " Conference Championship:\n");
                if (ccg.homeScore > ccg.awayScore) {
                    winner = ccg.homeTeam;
                    loser = ccg.awayTeam;
                    sb.append(winner.strRep() + " W ");
                    sb.append(ccg.homeScore + "-" + ccg.awayScore + " ");
                    sb.append("vs " + loser.strRep());
                    return sb.toString();
                } else {
                    winner = ccg.awayTeam;
                    loser = ccg.homeTeam;
                    sb.append(winner.strRep() + " W ");
                    sb.append(ccg.awayScore + "-" + ccg.homeScore + " ");
                    sb.append("at " + loser.strRep());
                    return sb.toString();
                }
            }
        }
    }

    /**
     * Get the allConfPlayers by sorting all the conf's players by their Heisman score
     * Should be only called after week 13
     */
    public ArrayList<Player> getAllConfPlayers() {
        if (allConfPlayers.isEmpty()) {
            ArrayList<PlayerQB> qbs = new ArrayList<>();
            ArrayList<PlayerRB> rbs = new ArrayList<>();
            ArrayList<PlayerWR> wrs = new ArrayList<>();
            ArrayList<PlayerTE> tes = new ArrayList<>();
            ArrayList<PlayerOL> ols = new ArrayList<>();
            ArrayList<PlayerK> ks = new ArrayList<>();
            ArrayList<PlayerDL> dls = new ArrayList<>();
            ArrayList<PlayerLB> lbs = new ArrayList<>();
            ArrayList<PlayerCB> cbs = new ArrayList<>();
            ArrayList<PlayerS> ss = new ArrayList<>();



            for (Team t : confTeams) {
                qbs.addAll(t.teamQBs);
                rbs.addAll(t.teamRBs);
                wrs.addAll(t.teamWRs);
                tes.addAll(t.teamTEs);
                ols.addAll(t.teamOLs);
                ks.addAll(t.teamKs);
                dls.addAll(t.teamDLs);
                lbs.addAll(t.teamLBs);
                cbs.addAll(t.teamCBs);
                ss.addAll(t.teamSs);

            }

            Collections.sort(qbs, new PlayerHeismanComp());
            Collections.sort(rbs, new PlayerHeismanComp());
            Collections.sort(wrs, new PlayerHeismanComp());
            Collections.sort(tes, new PlayerHeismanComp());
            Collections.sort(ols, new PlayerHeismanComp());
            Collections.sort(ks, new PlayerHeismanComp());
            Collections.sort(dls, new PlayerHeismanComp());
            Collections.sort(lbs, new PlayerHeismanComp());
            Collections.sort(cbs, new PlayerHeismanComp());
            Collections.sort(ss, new PlayerHeismanComp());

            allConfPlayers.add(qbs.get(0));
            qbs.get(0).wonAllConference = true;
            allConfPlayers.add(rbs.get(0));
            rbs.get(0).wonAllConference = true;
            allConfPlayers.add(rbs.get(1));
            rbs.get(1).wonAllConference = true;
            for (int i = 0; i < 3; ++i) {
                allConfPlayers.add(wrs.get(i));
                wrs.get(i).wonAllConference = true;
            }
            allConfPlayers.add(tes.get(0));
            tes.get(0).wonAllConference = true;

            for (int i = 0; i < 5; ++i) {
                allConfPlayers.add(ols.get(i));
                ols.get(i).wonAllConference = true;
            }

            allConfPlayers.add(ks.get(0));
            ks.get(0).wonAllConference = true;

            for (int i = 0; i < 4; ++i) {
                allConfPlayers.add(dls.get(i));
               dls.get(i).wonAllConference = true;
            }
            for (int i = 0; i < 3; ++i) {
                allConfPlayers.add(lbs.get(i));
                lbs.get(i).wonAllConference = true;
            }
            for (int i = 0; i < 3; ++i) {
                allConfPlayers.add(cbs.get(i));
                cbs.get(i).wonAllConference = true;
            }
            allConfPlayers.add(ss.get(0));
            ss.get(0).wonAllConference = true;
        }

        return allConfPlayers;
    }

}

class TeamCompConfWins implements Comparator<Team> {
    @Override
    public int compare( Team a, Team b ) {
        if (a.confChampion.equals("CC")) return -1;
        else if (b.confChampion.equals("CC")) return 1;
        else if (a.getConfWins() > b.getConfWins()) {
            return -1;
        } else if (a.getConfWins() == b.getConfWins()) {
            //check for h2h tiebreaker
            if (a.gameWinsAgainst.contains(b)) {
                return -1;
            } else if (b.gameWinsAgainst.contains(a)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }
}
