package mygame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class LandofMordor {
    static String gridLocation[][]=new String[11][11];
    
//    public static void setGrid(String name,Point point){
//        for(int x=0;x<gridLocation.length;x++){
//            for(int y=0;y<gridLocation[x].length;y++){
//                for(Warrior warrior:Warrior.getWarriorList()){
//                    if(warrior.getLastPosition().x.equals(x)&& warrior.getLastPosition().x.equals(y)){
//                        
//                    }
//                }
//            }
//        }
//        gridLocation[point.x][point.y]=name;
//    }
    
    
    public static Point getWarriorPoint(){//places a warrior in a suitable, available position randomly
        Random random=new Random();
        Point warriorPoint= new Point();
            do {
                warriorPoint.x = random.nextInt(10);
                warriorPoint.y = 0;
            } while (Warrior.checkPoint(warriorPoint)|| Monster.checkPoint(warriorPoint) || MagicTree.checkPoint(warriorPoint));
            return warriorPoint; 
    }
    public static Point getMonsterPoint(){//places a monster in a suitable, available position randomly
        Random random=new Random();
        Point monsterPoint= new Point();
            do {
                monsterPoint.x = random.nextInt(10);
                monsterPoint.y = (random.nextInt(10));
            } while ((monsterPoint.x == 5 && monsterPoint.y == 5) || Monster.checkPoint(monsterPoint) || MagicTree.checkPoint(monsterPoint));
            return monsterPoint;
    } 
    public static Point getTreePoint(){//places a magic tree in a suitable, available position randomly
        Random random=new Random();
        Point magicTreePoint= new Point();
            do {
                magicTreePoint.x = random.nextInt(10);
                magicTreePoint.y = (random.nextInt(10));
            } while ((magicTreePoint.x == 5 && magicTreePoint.y == 5) || MagicTree.checkPoint(magicTreePoint) || Monster.checkPoint(magicTreePoint) );
            return magicTreePoint;
    }
    
    public static ArrayList possibleMoves(Point point){//returns the possible moves for the warrior
        ArrayList<Point> possibleMove=new ArrayList<>();
        ArrayList<Point> validMove=new ArrayList<>();
        possibleMove.add(new Point(point.x, point.y+1));
        possibleMove.add(new Point(point.x, point.y-1));
        possibleMove.add(new Point(point.x+1, point.y));
        possibleMove.add(new Point(point.x-1, point.y));
        for(Point pointValid:possibleMove){
            if (!(Warrior.checkPoint(pointValid)) && (pointValid.x>=0) && (pointValid.x<11) && (pointValid.y>=0) && (pointValid.y<11)){
                validMove.add(pointValid);
            }
        }return validMove;
        
    }
    
    
    public static void startWalk(){//begins the game 
        int mobileWorriors=Warrior.getNo();
        while(true){//if a warrior reaches mount doom
            if(Warrior.endState){
                break;
            }
            if (mobileWorriors==0){//if warriors become immobile
                System.out.println("Warrior failed to reach Mount Doom!!!");
                break;
            }else{
                mobileWorriors=0; 
                for (int m = 0; m < Warrior.warriors.size(); m++) {
                    if(Warrior.warriors.isEmpty()){//when a warrior is killed the name is removed from the list. Checks whether all the warriors are dead...
                        mobileWorriors=0;
                        break;
                    }else{
                        if( Warrior.warriors.get(m).getMobility()){//checks whether even one monster can walk
                            mobileWorriors++;
                        }
                        Warrior.warriors.get(m).walk();
                    }
                }
            }
        }
    }
    
    public static void printTheGrid(){
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print("("+LandofMordor.gridLocation[i][j]+"),");
            }
            System.out.println("");
        }
    }


    
    
    
}
