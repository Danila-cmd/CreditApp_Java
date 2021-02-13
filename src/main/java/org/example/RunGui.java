package org.example;


import org.hsqldb.util.DatabaseManagerSwing;

public class RunGui {
    public static void main(String[] args) {
        System.out.println("Launching manager");
        DatabaseManagerSwing.main(new String[]{
                "--url", "jdbc:hsqldb:file:db-data/mydatabase", "--noexit"
        });
    }
}
