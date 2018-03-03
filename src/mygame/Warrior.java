package mygame;

import java.awt.Point;
import java.util.ArrayList;


public abstract class Warrior extends Inhabitants{
    
    private WalkingStick walkingStick;
    protected boolean mobility;
    protected boolean mortality;
    private static int noOfWarriors=0;//keeps track about the no of warrior objects created
    protected static ArrayList<Warrior> warriors=new ArrayList<>();
    protected Point lastPosition;
    static boolean endState=false;
    
    
    public Warrior(String name,int age){
        //each warrior object is given a name, age when it is created.
        super(name,age);
        mobility=true;//important because when the walking stick is stolen object becomes immovable
        walkingStick=new WalkingStick();
        mortality=true;//if a warrior eats fruits it becomes immortal
        noOfWarriors++;//increaments the no of warrior objects made
        setPosition();//warriors are placed in the land randomly
    }
    
    public void walk(){//makes the warrior move
        if (mobility){
            ArrayList<Point> movePossible=LandofMordor.possibleMoves(lastPosition);

            double shortestDistance=10;
            for (Point point:movePossible){//finds the shortest distance to mount doom and moves to that location 
                if (point.distance(5, 5)<shortestDistance){
                    shortestDistance=point.distance(5, 5);
                    if (LandofMordor.gridLocation[lastPosition.x][lastPosition.y]=="Normal Warrior") { //checks if the last location contains only warrior
                        LandofMordor.gridLocation[lastPosition.x][lastPosition.y]=null; //then making the point in the grid null
                    } else {
                        if( LandofMordor.gridLocation[lastPosition.x][lastPosition.y]!=null){ //if the point is not null and other object is present then remove only the warrior from the position
                        LandofMordor.gridLocation[lastPosition.x][lastPosition.y]=LandofMordor.gridLocation[lastPosition.x][lastPosition.y].replaceFirst("Normal Warrior", "");
                        }
                    }
                    lastPosition=point;
                }
            }
            if((lastPosition.x!=(5))  ||  (lastPosition.y!=(5))){
                printMove();
            }else{
                endState=true;
                printWin(); 
                for(Warrior warrior:warriors){
                    warrior.mobility=false;
                }             
            }
            ArrayList<MagicTree> magicTreeList=MagicTree.getMagicTree();
            for(MagicTree magicTree:magicTreeList){//if the moved point has a magic tree warrior eats a fruit
                if (magicTree.getPosition().equals(lastPosition)){
                    this.eat(magicTree);
                    mortality=false;
                }    
            } 
            ArrayList<Monster> monsterList=Monster.getMonsterList();
            for(Monster monster:monsterList){
                if (monster.getPosition().equals(lastPosition)){//if the moved point bears a monster warrior's stick is stolen
                    monster.steal(this);
                    System.out.println(monster.getName()+"stole the walking stick of "+this.getName());
                    
                }
                if((monster.getPosition().equals(lastPosition)) && (monster instanceof NonInnocentMonster)){//if the moved point bears a non innocent monster warrior is being killed
                    NonInnocentMonster badMonster=(NonInnocentMonster)monster;
                    Warrior killedWarrior=badMonster.kill(this);
                        if(killedWarrior==this){
                        warriors.remove(killedWarrior);
                        }
                }
                }
        }
    }

    public WalkingStick loseStick(){//when a monster steal warriors walking stick warrior becomes immobile
        mobility=false;
        WalkingStick tempWalkingStick=walkingStick;
        walkingStick=null;
        return tempWalkingStick;
    }
    
    public boolean getMortality(){//returns whether the warrior is mortal
        return mortality;
    }
    public boolean getMobility(){//returns whether the warrior can walk
        return mobility;
    }
 
    public static int getNo(){//returns the no of warriors in the land
        return noOfWarriors;
    }
    
    public static void setNoofWarriors(int no){//changes the no of warriors in the land when a warrior is killed
        noOfWarriors=no;
    }
    
    public void setPosition(){//places a warrior in the land
        lastPosition = LandofMordor.getWarriorPoint();
        warriors.add(this);
        printPositionBegining();   
    }
    
    public Point getLastPosition(){//return the position of the warrior
        return lastPosition;
    }
    
    public static boolean checkPoint(Point point){//checks whether any warrior present at a given point
        for(Warrior warrior:warriors){
            if (warrior.lastPosition.equals(point)){
                return true;
            }
        }
        return false;
    }
    
    public static ArrayList<Warrior> getWarriorList(){//returns the array list which warriors were tracked
        return warriors;
    }
    
    public abstract void printPositionBegining();//prints the position of the warrior when created
    public abstract void printMove();//prints where the warrior moved
    public abstract void printWin();//prints when warrior reaches mount doom
    public abstract void eat(MagicTree magicTree);
    public abstract void sleep();
    public abstract void drink();
}
    


