package Model;

import AllServlet.ServletInsertCat;

import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;

public class Cat {

    private String name;
    private String color;
    private int tail_length;
    private int whiskers_length;
    public static ArrayList<Cat> cats=new ArrayList<Cat>();


    public Cat(String name, String color, int tailLength, int whiskersLength) {
        setName(name);
        setColor(color);
        setTail_length(tailLength);
        setWhiskers_length(whiskersLength);
        cats.add(this);
    }

    public Cat(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.equals(" "))
            this.name = name;
        else System.out.println("Alarm name");
    }

    public String getColor() {
        return color;

    }

    public void setColor(String color) {
        this.color = color;


    }

    public int getTail_length() {
        return tail_length;
    }

    public void setTail_length(int tail_length) {
        if(tail_length>0)
            this.tail_length = tail_length;
        else System.out.println("Alarm tail length");
    }

    public int getWhiskers_length() {
        return whiskers_length;
    }

    public void setWhiskers_length(int whiskers_length) {
            if(whiskers_length>0)
                this.whiskers_length = whiskers_length;
            else System.out.println("Alarm whiskers length");
    }

    public static void freeResourse(){cats.removeAll(cats);}

    public String checkValues(){

        String answer="";

        if(name.isEmpty() || name.startsWith(" "))
            answer+="invalid name\n";

        if(tail_length<=0)
            answer+="invalid tail length\n";

        if(whiskers_length<=0)
            answer+="invalid whiskers length\n";

        return answer;
    }

    @Override
    public String toString() {
        return "('"+name+"','"+getColor()+"',"+tail_length+","+whiskers_length+")";
    }
}
