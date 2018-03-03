package mygame;

public class NonInnocentMonster extends Monster{
    
    
    public NonInnocentMonster(String name,int age){
        super(name,age);
    }
    public Warrior kill(Warrior warrior){//when a warrior meets a non innocent monster warrior is being killed
        boolean mortality=warrior.getMortality();
        if (mortality){
            System.out.println("\tand was killed");
           Warrior.setNoofWarriors(Warrior.getNo()-1) ; 
        }
        else{
            warrior=null;
        }
        return warrior;
    }
    
    public void printPositionBegining(){//prints the position of the monster when created
        System.out.println(getName() + " Non-Innocent monster is at (" + getPosition().x + "," + getPosition().y + ")..");
        LandofMordor.gridLocation[getPosition().x][getPosition().y]="Non-innocent Monster - "+this.getName(); //add non innocent monster to the grid
    } 
}