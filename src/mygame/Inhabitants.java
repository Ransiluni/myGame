package mygame;

import java.util.ArrayList;

public  abstract class Inhabitants {
  private String name;
  private int age;
  private static ArrayList<String> objectTrack=new ArrayList(); 

  
  
    public Inhabitants(String name,int age){
        this.name=name;
        this.age=age;
        String className=this.getClass().getSimpleName()+" : "+name;
        objectTrack.add(className); // adds all the inhabitants to one data structure
    }
    public String getName(){ //return the name of inhabitant
        return name;
    }
    public static void printAll(){ //print all the names of the inhabitants
        System.out.println("Inhabitants:");
        System.out.println("\t"+objectTrack);
    }
    
}
