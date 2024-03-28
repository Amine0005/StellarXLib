package it.vamxneedev.stellarxlib.api;

import it.vamxneedev.stellarxlib.enums.DatabaseType;

import java.sql.Connection;

public interface Database {

    /**
     * <p>Returns the type of database being used.</p>
     * <p>This function retrieves the type of database employed within the application or system.
     * It can be useful for determining whether the application is utilizing MySQL, PostgreSQL, SQLite,
     * MongoDB, or any other supported database type.</p>
     *
     * <p>The returned database type can be utilized for various purposes such as configuring
     * database-specific operations or performing compatibility checks with different database systems.</p>
     * <p>It's important to note that the returned database type may dictate specific behaviors or
     * optimizations in the application logic depending on the database technology being used.</p>
     */
    DatabaseType getDatabaseType();

    /**
    * <p>data values are different depend on type of SQL connection.</p>
    */
    Connection createConnection(String... data);
    Connection getConnection();
    Boolean isConnected();
    void closeConnection();

}
