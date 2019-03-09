package Task1;

import Conection.DBConection;
import Model.CatColorsInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

 public class StartFirstTask {

    private static String color="";
    private static String answer="";
    private static ArrayList<CatColorsInfo> allCatsColorInfo=new ArrayList<CatColorsInfo>();
    private static DBConection conection;


    public StartFirstTask() {

        System.out.println("------First Task------");
        conection=new DBConection();
        searchAllColor();
        checkAllColor();
        insertAllColor();


        try {
            conection.getConnection().close();
            System.out.println("Связь с БД разована");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void searchAllColor() {
        Statement statement= null;

        try {
            statement = conection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select distinct color from cats");

            while(resultSet.next()){
                CatColorsInfo catIColorsInfo=new CatColorsInfo();
                catIColorsInfo.setColor(resultSet.getString("color"));
                allCatsColorInfo.add(catIColorsInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void checkAllColor(){
     Statement statement=null;

        try {

            for(int i=0;i<allCatsColorInfo.size();i++) {

                statement = conection.getConnection().createStatement();
                color = allCatsColorInfo.get(i).getColor();
                ResultSet resultSet = statement.executeQuery("select count(color) as x from cats where color = '" + color + "'");

                while (resultSet.next())
                    allCatsColorInfo.get(i).setCount(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    private static void insertAllColor(){
        Statement statement=null;

        try {
            statement=conection.getConnection().createStatement();

            for(int i=0;i<allCatsColorInfo.size();i++) {
                if(i==allCatsColorInfo.size()-1) {
                    answer += allCatsColorInfo.get(i);
                    break;
                }
                answer += allCatsColorInfo.get(i) + ",";
            }

             statement.executeUpdate("insert into cat_colors_info (color, count) values "+answer);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
