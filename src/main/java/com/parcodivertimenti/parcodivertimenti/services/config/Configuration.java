package com.parcodivertimenti.parcodivertimenti.services.config;


import java.util.Calendar;
import java.util.logging.Level;

import com.isw.es_07_rubrica.model.dao.DAOFactory;

public class Configuration {

    /* Database Configruation */
    public static final String DAO_IMPL=DAOFactory.MYSQLJDBCIMPL; /*lasciare così*/
    public static final String DATABASE_DRIVER="com.mysql.cj.jdbc.Driver"; /*lasciare così*/
    public static final String SERVER_TIMEZONE=Calendar.getInstance().getTimeZone().getID(); /*lasciare così*/
    public static final String
            DATABASE_URL="jdbc:mysql://localhost/parco_divertimenti?user=root&password=sarA2002&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone="+SERVER_TIMEZONE;

    /* Session Configuration */
    public static final String COOKIE_IMPL=DAOFactory.COOKIEIMPL; /*lasciare così*/

    /* Logger Configuration */
    public static final String GLOBAL_LOGGER_NAME="parco_divertimeti";
    public static final String GLOBAL_LOGGER_FILE="/Users/217347/OneDrive-unife.it/Desktop/parco_divertimenti_log.%g.%u.txt";
    public static final Level GLOBAL_LOGGER_LEVEL=Level.ALL; /*lasciare così*/

}

