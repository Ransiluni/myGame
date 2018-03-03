
package mygame;

public class InnocentMonster extends Monster{
    
    public InnocentMonster(String name,int age){
        super(name,age);
    }
  
    public void printPositionBegining(){ //prints the position of monster when created
        System.out.println(getName() + " Innocent monster is at (" + getPosition().x + "," + getPosition().y + ")..");
        LandofMordor.gridLocation[getPosition().x][getPosition().y]="innocent Monster - "+this.getName(); // add innocent-monster to the grid
    }
}