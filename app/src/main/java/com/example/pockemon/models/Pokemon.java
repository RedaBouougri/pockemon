package com.example.pockemon.models;

public class Pokemon {

    private String name;
    private String url;

    private String number;

    private String weight;

    private String height;

    private String gameIndex;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(String gameIndex) {
        this.gameIndex = gameIndex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {

        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
