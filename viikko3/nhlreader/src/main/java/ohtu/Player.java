
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String team;
    private int goals;
    private int assists;
    private String nationality;

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    public int points() {
        return this.assists + this.goals;
    }
    

    @Override
    public String toString() {
        String nameTeam = String.format("%-25s %5s", name, team);
        String statistics = String.format("%3d + %3d = %5d", goals, assists, points());
        return nameTeam + "\t" + statistics;
    }

    @Override
    public int compareTo(Player o) {
        return o.points() - this.points();
    }
      
}
