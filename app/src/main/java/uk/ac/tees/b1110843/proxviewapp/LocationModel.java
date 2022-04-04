package uk.ac.tees.b1110843.proxviewapp;

public class LocationModel {

    int id, drawableId;
    String name;
    String locationType;

    public LocationModel(){ }

    public LocationModel(int id, int drawableId, String name, String locationType){
        this.id = id;
        this.name = name;
        this.drawableId = drawableId;
        this.locationType = locationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getDrawableId() {

        return drawableId;
    }

    public void setDrawableId(int drawableId) {

        this.drawableId = drawableId;
    }
}
