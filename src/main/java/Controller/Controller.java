package Controller;

import Conection.DBConection;
import Model.Cat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Controller {


    private static DBConection dbConection=new DBConection();
    private static Gson json= new GsonBuilder().disableHtmlEscaping().create();
    private static ArrayList<String> answer=new ArrayList<String>();

    private Controller(){}

    public static ArrayList<String> catsList(){
        Cat cat;
        Cat.freeResourse();
        answer.removeAll(answer);

        System.out.println("ListAnswer"+answer);

        try {
            Statement statement=dbConection.getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery("select * from cats");

            while(resultSet.next()){
                cat=new Cat(resultSet.getString("name"),resultSet.getString("color"),
                        resultSet.getInt("tail_length"),resultSet.getInt("whiskers_length"));
                System.out.println(json.toJson(cat));
            }

            for(int i=0;i<Cat.cats.size();i++){
                answer.add("\n"+json.toJson(Cat.cats.get(i)));
            }

            answer.add("\n");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answer;
    }

    public static ArrayList<String> catsListOrder(String attribute,String order){
        Cat cat;
        Cat.freeResourse();
        answer.removeAll(answer);

        System.out.println("orderAnswer="+answer);

        if(order.equals("asc")|| order.equals("desc")) {

            try {
                Statement statement = dbConection.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("select * from cats order by "+attribute+" "+order);

                while(resultSet.next()) {
                    cat = new Cat(resultSet.getString("name"), resultSet.getString("color"),
                            resultSet.getInt("tail_length"), resultSet.getInt("whiskers_length"));
                }

                for(int i=0;i<Cat.cats.size();i++){
                    answer.add("\n"+json.toJson(Cat.cats.get(i)));
                }
                answer.add("\n");

                statement.close();
            } catch (SQLException e) {
                answer.add("\nincorrectly entered 'attribute'\n enter request again\n");
                e.printStackTrace();
            }

        }else answer.add("\n incorrectly entered 'order'\n");


        return answer;
    }

    public static ArrayList<String> catsListLimit(String offSet, String limit){
        Cat cat;
        Cat.freeResourse();
        answer.removeAll(answer);


        if(Integer.valueOf(offSet)>=0 && Integer.valueOf(limit)>0) {
            try {
                Statement statement = dbConection.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("select * from cats limit " + limit + " offset " + offSet);

                while (resultSet.next()) {
                    cat = new Cat(resultSet.getString("name"), resultSet.getString("color"),
                            resultSet.getInt("tail_length"), resultSet.getInt("whiskers_length"));
                }

                for (int i = 0; i < Cat.cats.size(); i++) {
                    answer.add("\n" + json.toJson(Cat.cats.get(i)));
                }
                answer.add("\n");

                statement.close();

            } catch (SQLException e) {
                answer.add("\nsql query error\n enter request again\n");
                e.printStackTrace();
            }
        }else answer.add("\nincorrectly entered 'offset' or 'limit'\n enter request again\n");

        return answer;
    }

    public static ArrayList<String> catsListAllRequest(String attribute,String order,String offSet, String limit) {
        Cat cat;
        Cat.freeResourse();
        answer.removeAll(answer);


        if(Integer.valueOf(offSet)>=0 && Integer.valueOf(limit)>0 && (order.equals("asc")|| order.equals("desc"))) {
            try {
                Statement statement = dbConection.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("select * from cats order by "+attribute+" "+order+" limit " + limit + " offset " + offSet);

                while (resultSet.next()) {
                    cat = new Cat(resultSet.getString("name"), resultSet.getString("color"),
                            resultSet.getInt("tail_length"), resultSet.getInt("whiskers_length"));
                }

                for (int i = 0; i < Cat.cats.size(); i++) {
                    answer.add("\n" + json.toJson(Cat.cats.get(i)));
                }
                answer.add("\n");

                statement.close();

            } catch (SQLException e) {
                answer.add("\nsql query error\n enter request again\n");
                e.printStackTrace();
            }
        }else answer.add("\nincorrectly entered parameters\n enter request again\n");
        return answer;
    }

    public static String insertCat(Cat cat){
        Statement statement= null;
        String answer;

        try {
            statement = dbConection.getConnection().createStatement();
            statement.executeUpdate("insert into cats (name,color,tail_length,whiskers_length) values "+cat.toString());
            answer="\ndata recorded";

        } catch (SQLException e) {
            //если не корректный sql запрос или кот с таким именем уже есть
            answer="\none of two errors:\n 1)this name alreade exists \n 2)SQL query syntax error \nenter request again\n";
            e.printStackTrace();
        }

        return answer;
    }
}
