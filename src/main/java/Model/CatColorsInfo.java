package Model;

 public class CatColorsInfo {

    private String color="";
    private int count=0;


    public CatColorsInfo(){}

    public void setCount(int count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "('"+color+"',"+count+")" ;
    }
}
