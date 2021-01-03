package Model;

public class Admin {
    String name;
    String email;
    String password;
    String contact;
    String website;
    String address;
    String type;
    String description;
    String valid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public Admin(String name, String email, String password, String contact, String website, String address, String type, String description, String valid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.website = website;
        this.address = address;
        this.type = type;
        this.description = description;
        this.valid = valid;
    }

    public Admin() {
    }
}
