import java.sql.*;

public class SQLiteTest {

    private static Connection con;
    private static boolean hasData = false;

    public ResultSet display(){
        if(con == null){
            getConnection();
        }

        Statement state = null;
        try {
            state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT * FROM sprites WHERE id=(SELECT max(id) from sprites)");
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:Sprite.db");
            initialise();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void initialise() {
        if(!hasData){
            hasData = true;
            Statement state = null;
            try {
                state = con.createStatement();
                ResultSet result = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='sprites'");
                if(!result.next()){
                    System.out.println("Building a sprites table");

                    Statement state2 = con.createStatement();
                    state2.execute("CREATE TABLE sprites (id INTEGER  PRIMARY KEY, " +
                            "x_coordinate INTEGER ," + "y_coordinate INTEGER, " + "x_coordenete_speed INTEGER, " + "y_coordenete_speed INTEGER)"+";");
                }else{
                    Statement prep = con.createStatement();
                    prep.execute("DELETE FROM sprites");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void addsprite(int x, int y, int dx,int dy){
        if(con == null){
            getConnection();
        }


        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO sprites VALUES(?,?,?,?,?);");
            preparedStatement.setInt(2,x);
            preparedStatement.setInt(3,y);
            preparedStatement.setInt(4,dx);
            preparedStatement.setInt(5,dy);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
