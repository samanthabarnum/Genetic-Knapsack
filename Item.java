//Samantha Barnum
//CS 1181L-07
//Project 1
//2/3/22

public class Item {

    private String itemName;
    private double weight;
    private int value;
    private boolean isIncluded;

    public Item(String name, double weight, int value){
        //initializes the item's fields to the values that are passed in, the included field is initialized to false
        this.itemName = name;
        this.weight = weight;
        this.value = value;
        isIncluded = false;
    }

    public Item(Item other){
        //initializes the item's fields to be the same as the other's item's
        this.itemName = other.itemName;
        this.weight = other.weight;
        this.value = other.value;
        this.isIncluded = other.isIncluded;
    }

    public double getWeight(){
        //getter for the item’s fields
        return weight;
    }

    public int getValue(){
      //getter for the item’s fields
        return value;
    }

    public boolean isIncluded(){
      //getter for the item’s fields
        return isIncluded;
    }

    public void setIncluded(boolean included){
        //setter for the item's included field
        this.isIncluded = included;
    }

    public String toString(){
        //displays the item in the form <name> (<weight> lbs, $value>)
       String returnString = ("<"+ this.itemName +"> (<" + this.weight + "> lbs, $" + value + ">)\n");
        return returnString;
    }
}