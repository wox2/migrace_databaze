package model;

import java.sql.SQLException;

/*******************************************************************************
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public interface Conservable {


    public void update() throws SQLException;


    public void insert() throws SQLException;


    public void delete() throws SQLException;
    

    
}
