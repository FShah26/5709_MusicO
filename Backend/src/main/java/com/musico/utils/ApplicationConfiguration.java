//Author: Simar Saggu
// Created on: 14th July,2021
package com.musico.utils;

import java.util.Map;

public class ApplicationConfiguration {

    static ApplicationConfiguration instance = null;

    public ApplicationConfiguration()
    {
        Map<String, String> environmentMap = System.getenv();
        for (Map.Entry<String, String> entry : environmentMap.entrySet())
        {
            if (entry.getKey().equals("database.url"))
            {
                this.DATABASE_URL = entry.getValue();
            }
            if (entry.getKey().equals("database.username"))
            {
                this.DATABASE_USERNAME = entry.getValue();
            }
            if (entry.getKey().equals("database.password"))
            {
                this.DATABASE_PASSWORD = entry.getValue();
            }
            if (entry.getKey().equals("database.port"))
            {
                this.PORT = entry.getValue();
            }
            if (entry.getKey().equals("database.db"))
            {
                this.DATABASE_NAME = entry.getValue();
            }

        }
    }
    public static ApplicationConfiguration instance()
    {
        if (instance == null)
        {
            instance = new ApplicationConfiguration();
        }
        return instance;
    }

    private String DATABASE_URL;

    private String DATABASE_USERNAME;

    private String DATABASE_PASSWORD;

    private String DATABASE_NAME;

    private String PORT;


    public Integer getPORT() {
        return Integer.valueOf(PORT);
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getDATABASE_NAME() {
        return DATABASE_NAME;
    }

    public void setDATABASE_NAME(String DATABASE_NAME) {
        this.DATABASE_NAME = DATABASE_NAME;
    }

    public String getDATABASE_URL() {
        return DATABASE_URL;
    }

    public void setDATABASE_URL(String DATABASE_URL) {
        this.DATABASE_URL = DATABASE_URL;
    }

    public String getDATABASE_USERNAME() {
        return DATABASE_USERNAME;
    }

    public void setDATABASE_USERNAME(String DATABASE_USERNAME) {
        this.DATABASE_USERNAME = DATABASE_USERNAME;
    }

    public String getDATABASE_PASSWORD() {
        return DATABASE_PASSWORD;
    }

    public void setDATABASE_PASSWORD(String DATABASE_PASSWORD) {
        this.DATABASE_PASSWORD = DATABASE_PASSWORD;
    }


}
