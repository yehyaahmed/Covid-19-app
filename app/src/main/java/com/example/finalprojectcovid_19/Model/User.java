package com.example.finalprojectcovid_19.Model;

public class User {

    private String id;
    private String status;
    private String search;

    public User(String id, String status, String search) {
        this.id = id;
        this.status = status;
        this.search = search;
    }



    public User(){

    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
