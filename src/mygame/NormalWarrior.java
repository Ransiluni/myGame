package mygame;

public class NormalWarrior extends Warrior{
    
    public NormalWarrior(String name,int age){
        super(name,age);
    }
    
    public void eat(MagicTree magicTree){//how a normal warrior eats
        System.out.println("\t"+this.getName()+" Normal Warrior ate a magic fruit from  "+magicTree.getName()+" and became immortal");
    }
    
    public void sleep(){//how a normal warrior sleeps
        //method to sleep
    }
    
    public void drink(){//how a normal warrior drinks
        //method to drink
    }
    
    public void printPositionBegining(){//prints the position of the warrior when created
        System.out.println(getName() + " Normal Warrior started moving from (" + this.getLastPosition().x + "," + this.getLastPosition().y + ")..");
        LandofMordor.gridLocation[this.getLastPosition().x][this.getLastPosition().y]="Normal Warrior"; //place the warrior in the grid
    }
    
    public void printMove(){//prints where the warrior moved
        System.out.println(getName() + " Normal Warrior moved to (" + this.getLastPosition().x + "," + this.getLastPosition().y + ")..");
        if(LandofMordor.gridLocation[this.getLastPosition().x][this.getLastPosition().y]!=null){ //if warrior isn't null add the warrior to the grid along with the already place object
            LandofMordor.gridLocation[this.getLastPosition().x][this.getLastPosition().y]=LandofMordor.gridLocation[this.getLastPosition().x][this.getLastPosition().y]+"Normal Warrior"; //adding normal warrior to the grid after each move
        }
        else{ //add warrior to the grid after every move
            LandofMordor.gridLocation[this.getLastPosition().x][this.getLastPosition().y]="Normal Warrior";
        }
    }
    
    public void printWin(){//prints when warrior reaches mount doom
        LandofMordor.gridLocation[lastPosition.x][lastPosition.y]="Normal Warrior";
        System.out.println("\t\t"+getName()+" Normal Warrior moved to ( "+lastPosition.x+", "+lastPosition.y+") and won the game..");
    }
}
