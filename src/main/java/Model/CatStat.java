package Model;

import java.util.ArrayList;

public class CatStat {


    private double tailLengthMean=0;
    private double tailLengthMedian=0;
    private ArrayList<Integer> tailLengthMode;
    private double whiskersLengthMean=0;
    private double whiskersLengthMedian=0;
    private ArrayList<Integer> whiskersLengthMode;


    public CatStat(){}


    public double getTailLengthMean() {
        return tailLengthMean;
    }

    public void setTailLengthMean(double tailLengthMean) {
        this.tailLengthMean =(Double)Math.floor(tailLengthMean*10)/10.0;
    }

    public double getTailLengthMedian() {
        return tailLengthMedian;
    }

    public void setTailLengthMedian(double tailLengthMedian) {
        this.tailLengthMedian =(Double)Math.floor(tailLengthMedian*10)/10.0;
    }

    public ArrayList<Integer> getTailLengthMode() {
        return tailLengthMode;
    }

    public void setTailLengthMode(ArrayList<Integer> tailLengthMode) {
        this.tailLengthMode =tailLengthMode;
    }

    public double getWhiskersLengthMean() {
        return whiskersLengthMean;
    }

    public void setWhiskersLengthMean(double whiskersLengthMean) {
        this.whiskersLengthMean = (Double)Math.floor(whiskersLengthMean*10)/10.0;
    }

    public double getWhiskersLengthMedian() {
        return whiskersLengthMedian;
    }

    public void setWhiskersLengthMedian(double whiskersLengthMedian) {
        this.whiskersLengthMedian = (Double)Math.floor(whiskersLengthMedian*10)/10.0;
    }

    public ArrayList<Integer> getWhiskersLengthMode() {
        return whiskersLengthMode;
    }

    public void setWhiskersLengthMode(ArrayList<Integer> whiskersLengthMode) {
        this.whiskersLengthMode = whiskersLengthMode;
    }


    @Override
    public String toString() {
        return "("+tailLengthMean+","+tailLengthMedian+",array"+tailLengthMode+","+
                whiskersLengthMean+","+whiskersLengthMedian+",array"+whiskersLengthMode+")";
    }
}
