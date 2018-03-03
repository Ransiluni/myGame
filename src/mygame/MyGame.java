
package mygame;


public class MyGame {
    
    public static void main(String[] args) {
        
        new NonInnocentMonster("NM-Zip", 24); 
        new NonInnocentMonster("NM-Zup", 21);
        new InnocentMonster("IM-Eps", 20);
        new InnocentMonster("IM-Teps", 18);
        new MagicTree("Golden");
        new MagicTree("Bronze");
        new MagicTree("Silver");
        new MagicTree("Platinum");
        
        
        new NormalWarrior("N-Ran",21);
        new NormalWarrior("N-Rosh",23);
        new SuperWarrior("S-Ruw",19);
        new SuperWarrior("S-Ras",12);
        
        Inhabitants.printAll(); //prints all the inhabitant as list
        LandofMordor.startWalk();
        LandofMordor.printTheGrid(); //prints the grid if necessary
       
        
 
        
    }}

