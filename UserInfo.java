public class UserInfo {
    String login;
    String carBrand;
    String carModel;
    String insurance;
    float price;

    public UserInfo(String login, String carBrand, String carModel, String insurance, float price) {
        this.login = login;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.insurance = insurance;
        this.price = price;
    }

    @Override
    public String toString() {
        return login;
    }

    public String getLogin() {
        return login;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getInsurance() {
        return insurance;
    }

    public float getPrice() {
        return price;
    }
}
