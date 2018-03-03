package mygame;

import java.awt.Point;
import java.util.ArrayList;

public class MagicTree {
    private String name;
    private Point position;
    private static ArrayList<MagicTree> magicTreeList=new ArrayList<>();
    
    public MagicTree(String name){
        this.name=name;
        setPosition();//magic trees are placed in the land randomly
    }
    public Point getPosition(){ //return the position of the tree 
        return position;
    }
    public String getName(){//returns the name of the tree
        return name;
    }
    public void setPosition(){//places a tree in the land
        position=LandofMordor.getTreePoint();
        magicTreeList.add(this);
        System.out.println("There is "+getName() + ",magic tree at(" + position.x + "," + position.y + ")..");
        LandofMordor.gridLocation[position.x][position.y]="Magic Tree"; //add magic tree to the grid
    }
    public static boolean checkPoint(Point point){//checks whether any magic tree present at a given point
        for(MagicTree tree:magicTreeList){
            if(tree.position.equals(point)){
                return true;
            }
        }return false;
    }
    public static ArrayList getMagicTree(){//returns the array list which magic trees were tracked
        return magicTreeList;
    }  
}
