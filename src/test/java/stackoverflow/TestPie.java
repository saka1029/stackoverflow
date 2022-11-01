package stackoverflow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestPie {

    Connection connection;

    @Test
    public void testCreate() throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            connection = c;
            System.out.println(listMutualFollowers(1));
        }
//            Statement st = connection.createStatement();
//            st.execute("drop table if exists followers");
//            st.execute("create table followers (id_user int, id_follower int)");
//            st.executeUpdate("insert into followers values (1, 2)");
//            st.executeUpdate("insert into followers values (1, 3)");
//            st.executeUpdate("insert into followers values (1, 4)");
//            st.executeUpdate("insert into followers values (3, 1)");
    }

    public List<Integer> listMutualFollowers(int id_user) {
        List<Integer> result = new ArrayList<Integer>();
        String query = 
            "select id_follower from followers a"
            + "  where id_user = ? and id_user in"
            + "    (select id_follower from followers b"
            + "        where b.id_user = a.id_follower)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id_user);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    result.add(rs.getInt(1));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
