package org.sample.dao;

import org.sample.model.HotelDeals;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanshika on 2/10/16.
 */

@Repository
public class HotelDealsDaoImpl implements HotelDealsDao {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://mysql21735-hanshika.cloud.cms500.com/sampleWebAppDB";
    static final String DB_URL = "jdbc:mysql://localhost:3306/sampleWebAppDB";

    //  Database credentials
    static final String USER = "root";
//    static final String PASS = "LKRhsc05849";
    static final String PASS = "12345";

    public List<HotelDeals> getAllHotelDeals(){
        List<HotelDeals> hotelDealsList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM hotel_deals";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                HotelDeals hotelDeals = new HotelDeals();
                hotelDeals.setId(rs.getInt("id"));
                hotelDeals.setName(rs.getString("name"));
                hotelDeals.setImage(rs.getString("image"));
                hotelDeals.setRating(rs.getFloat("rating"));
                hotelDeals.setLink(rs.getString("link"));
                hotelDeals.setActualPrice(rs.getFloat("actual_price"));
                hotelDeals.setDiscount(rs.getInt("discount"));
                hotelDeals.setLocation(rs.getString("location"));
                hotelDealsList.add(hotelDeals);
            }
            rs.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return hotelDealsList;
    }
}
