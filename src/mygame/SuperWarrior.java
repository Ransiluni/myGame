package mygame;

import java.awt.Point;
import java.util.ArrayList;
public class SuperWarrior extends Warrior {
    private boolean binocular;
    
    public SuperWarrior(String name,int age){
        super(name,age);
        binocular=true;
        
    }public void eat(MagicTree magicTree){//how a super warrior eats
        System.out.println("\t"+this.getName()+" Super Warrior ate a magic fruit from  "+magicTree.getName()+" and became immortal");
    }
    public void sleep(){//how a super warrior sleeps
        //method to sleep
    }
    public void drink(){//how a super warrior drinks
        //method to drink
    }
    public void printPositionBegining(){//prints the position of the warrior when created
        System.out.println(getName() + " Super Warrior started moving from (" + this.getLastPosition().x + "," + this.getLastPosition().y + ")..");
        LandofMordor.gridLocation[this.getLastPosition().x][this.getLastPosition().y]="Super Warrior"; //place the warrior in the grid
    }
     public void printMove(){//prints where the warrior moved
        System.out.println(getName() + " Super Warrior moved to (" + this.getLastPosition().x + "," + this.getLastPosition().y + ")..");
        if(LandofMordor.gridLocation[this.getLastPosition().x][this.getLastPosition().y]!=null){ //add warrior to the grid along with the already place object
            LandofMordor.gridLocation[this.getLastPosition().x][this.getLastPosition().y]=LandofMordor.gridLocation[this.getLastPosition().x][this.getLastPosition().y]+"Super Warrior"; //adding super warrior tot he grid after each move
        }
        else{
            LandofMordor.gridLocation[this.getLastPosition().x][this.getLastPosition().y]="Super Warrior"; //placing warrior in the grid after every move
        }
     }
    public void printWin(){//prints when warrior reaches mount doom
        LandofMordor.gridLocation[lastPosition.x][lastPosition.y]="Super Warrior";
        System.out.println("\t\t"+getName()+" Super Warrior moved to ( "+lastPosition.x+", "+lastPosition.y+") and won the game..");
    }
    public void walk(){//makes the warrior move
        if (mobility){
            ArrayList<Point> movePossible=LandofMordor.possibleMoves(lastPosition);
            ArrayList<Point> movePossibleFinal=new ArrayList<>();
            ArrayList<MagicTree> magicTreeList=MagicTree.getMagicTree();
            

            if(mortality){//mortal super warrior checks for a magic tree
                for(Point moveToMagicTree:movePossible){
                    for(MagicTree magicTree:magicTreeList){
                        if (magicTree.getPosition().equals(moveToMagicTree)){
                            movePossibleFinal.add(moveToMagicTree);
                        } 
                    }
                }
            }
            
            double shortestDistance=10;
            //gives priority for a magic tree location
            if(!movePossibleFinal.isEmpty()){
                for (Point point:movePossibleFinal){
                    if (point.distance(5, 5)<shortestDistance){
                        shortestDistance=point.distance(5, 5);
                        if (LandofMordor.gridLocation[this.lastPosition.x][this.lastPosition.y]=="Super Warrior") {
                        LandofMordor.gridLocation[lastPosition.x][lastPosition.y]=null;
                    } else {
                            if( LandofMordor.gridLocation[lastPosition.x][lastPosition.y]!=null){
                        LandofMordor.gridLocation[lastPosition.x][lastPosition.y]=LandofMordor.gridLocation[lastPosition.x][lastPosition.y].replaceFirst("Super Warrior", "");
                    }}
                        lastPosition=point;
                    }
                }
            }
            else{
                //if there is no magic tree in the four possible points the super warrior walks like the normal warrior
                for (Point point:movePossible){
                    if (point.distance(5, 5)<shortestDistance){
                        shortestDistance=point.distance(5, 5);
                        if (LandofMordor.gridLocation[this.lastPosition.x][this.lastPosition.y]=="Super Warrior") { //checks if the last location contains only warrior
                        LandofMordor.gridLocation[lastPosition.x][lastPosition.y]=null;
                    } else {
                            if( LandofMordor.gridLocation[lastPosition.x][lastPosition.y]!=null){ //if the point is not null and other object is present then remove only the warrior from the position
                        LandofMordor.gridLocation[lastPosition.x][lastPosition.y]=LandofMordor.gridLocation[lastPosition.x][lastPosition.y].replaceFirst("Super Warrior", "");
                    }}
                        lastPosition=point;
                    }
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
            if(mortality){//if the moved point has a magic tree warrior eats a fruit
                for(MagicTree magicTree:magicTreeList){
                    if (magicTree.getPosition().equals(lastPosition)){
                        this.eat(magicTree);
                        mortality=false;
                    } 
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
}
