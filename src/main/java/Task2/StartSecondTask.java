package Task2;

import Conection.DBConection;
import Model.CatStat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StartSecondTask {


    private static int countTail = 0;
    private static int countWhiskers = 0;
    private static DBConection conection;
    private static CatStat catStat = new CatStat();

    public StartSecondTask() {

        System.out.println("------Second Task------");
        conection=new DBConection();
        searchCount();
        searchLengthMean();
        searchLengthMedian();
        searchLengthMode();
        insertAllStat();


        try {
            conection.getConnection().close();
            System.out.println("Связь с БД разорвана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void searchCount() {
        Statement statement = null;

        try {
            statement = conection.getConnection().createStatement();
            ResultSet resultSet;

            resultSet = statement.executeQuery("select count(tail_length) as x from cats");
            while (resultSet.next())
                countTail = resultSet.getInt(1);

            resultSet = statement.executeQuery("select count(whiskers_length) as x from cats");
            while (resultSet.next())
                countWhiskers = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void searchLengthMean() {
        Statement statement = null;

        try {
            statement = conection.getConnection().createStatement();
            ResultSet resultSet;

            resultSet = statement.executeQuery("select sum(tail_length) from cats");
            while (resultSet.next())
                catStat.setTailLengthMean((double)resultSet.getInt(1) / countTail);

            resultSet = statement.executeQuery("select sum(whiskers_length) from cats");
            while (resultSet.next())
                catStat.setWhiskersLengthMean((double)resultSet.getInt(1) / countWhiskers);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void searchLengthMedian() {
        Statement statement = null;
        ArrayList<Integer> list = new ArrayList<Integer>();

        try {
            statement = conection.getConnection().createStatement();
            ResultSet resultSet;

            resultSet = statement.executeQuery("select distinct tail_length from cats order by tail_length");
            while (resultSet.next()) {
                list.add(resultSet.getInt("tail_length"));
            }
            catStat.setTailLengthMedian(median(list));
            list.removeAll(list);


            resultSet = statement.executeQuery("select distinct whiskers_length from cats order by whiskers_length");
            while (resultSet.next()) {
                list.add(resultSet.getInt("whiskers_length"));
            }
            catStat.setWhiskersLengthMedian(median(list));
            list.removeAll(list);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static double median(ArrayList<Integer> list) {
        double median;

        if (list.size() % 2 != 0)
            median = list.get(list.size() / 2);
        else {
            median = (double)(list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
        }

        return median;
    }

    private static void searchLengthMode() {
        Statement statement = null;

        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            statement = conection.getConnection().createStatement();
            ResultSet resultSet;

            resultSet = statement.executeQuery("select tail_length from cats order by tail_length");
            while (resultSet.next())
                list.add(resultSet.getInt("tail_length"));
            catStat.setTailLengthMode(mod(list));
            list.removeAll(list);


            resultSet=statement.executeQuery("select whiskers_length from cats order by whiskers_length");
            while(resultSet.next())
                list.add(resultSet.getInt("whiskers_length"));
            catStat.setWhiskersLengthMode(mod(list));
            list.removeAll(list);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static ArrayList<Integer> mod(ArrayList<Integer> list) {

        ArrayList<Integer> modValues = new ArrayList<Integer>();
        int val = 0;
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                if (count > val) {
                    modValues.removeAll(modValues);
                    modValues.add(list.get(i));
                }
                break;
            }
            if (list.get(i) == list.get(i + 1))
                    count++;
            else if (count > val) {
                    modValues.removeAll(modValues);
                    modValues.add(list.get(i));
                    val = count;
                    count=0;
            }else if(count == val){
                    modValues.add(list.get(i));
                    count=0;
            }else count=0;

            }
            return modValues;

    }

    private static void insertAllStat(){
        Statement statement=null;

        try {
            statement=conection.getConnection().createStatement();
            statement.executeUpdate("insert into cats_stat "+
                    "(tail_length_mean,tail_length_median,tail_length_mode,whiskers_length_mean,whiskers_length_median,whiskers_length_mode)"+
                    " values "+catStat.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}