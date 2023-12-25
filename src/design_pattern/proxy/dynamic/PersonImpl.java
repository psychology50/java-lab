package design_pattern.proxy.dynamic;

public class PersonImpl implements Person {
    String name;
    String gender;
    String interests;
    int rating;
    int ratingCount = 0;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name  = name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getInterests() {
        return interests;
    }

    @Override
    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Override
    public int getGeekRating() {
        return ratingCount == 0 ? 0 : rating / ratingCount;
    }

    @Override
    public void setGeekRating(int rating) {
        this.rating += rating;
        ++ratingCount;
    }
}
