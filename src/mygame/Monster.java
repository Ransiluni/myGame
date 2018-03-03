
package mygame;

import java.awt.Point;
import java.util.ArrayList;


public abstract class Monster extends Inhabitants {
    private static ArrayList<WalkingStick> walkingSticks=new ArrayList<>();
    private static int count;
    private static ArrayList<Monster> monster=new ArrayList<>();
    private Point position;
    public Monster(String name,int age){
        super(name,age);  
        setPosition();//monsters are placed in the land randomly
        
    }
    
    public abstract void printPositionBegining();//prints the position of the monster when created
    
    public Point getPosition(){//returns the position of the monster
        return position;
    }
    public void steal(Warrior warrior){//when a warrior meets a monster warriors stick will be taken
        WalkingStick stoleStick=warrior.loseStick();
        walkingSticks.add(stoleStick);
    }
    
    public void setPosition(){//places a monster in the land 
        position = LandofMordor.getMonsterPoint();
        monster.add(this);
        printPositionBegining();
    }

    public static boolean checkPoint(Point point){//checks whether monsters are present at a given point
        for(Monster monster:monster){
            if (monster.position.equals(point)){
                return true;
            }
        }
        return false;  
    }
    
    public static ArrayList getMonsterList(){//returns the array list which monsters were tracked
        return monster;
    }
}