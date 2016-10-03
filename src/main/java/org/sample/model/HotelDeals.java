package org.sample.model;

/**
 * Created by hanshika on 2/10/16.
 */
public class HotelDeals {
    int id;
    String name;
    String image;
    float rating;
    String link;
    float actualPrice;
    long discount;
    String location;

    public HotelDeals() {
    }

    public HotelDeals(int id, String name, String image, float rating, String link, float actualPrice, long discount, String location) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.rating = rating;
        this.link = link;
        this.actualPrice = actualPrice;
        this.discount = discount;
        this.location = location;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public float getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(float actualPrice) {
        this.actualPrice = actualPrice;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "HotelDeals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", link='" + link + '\'' +
                ", actualPrice=" + actualPrice +
                ", discount=" + discount +
                ", location='" + location + '\'' +
                '}';
    }
}
