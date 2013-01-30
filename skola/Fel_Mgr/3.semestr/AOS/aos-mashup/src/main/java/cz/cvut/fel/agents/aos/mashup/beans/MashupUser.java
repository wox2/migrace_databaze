package cz.cvut.fel.agents.aos.mashup.beans;

public class MashupUser {
    
    private String name;
    private int goals;

    public MashupUser() {}

    public MashupUser(String name, int goals) {
        this.name = name;
        this.goals = goals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }
    
}
