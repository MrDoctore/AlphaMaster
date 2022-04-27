package com.alphaproject.requests.school;

import javax.validation.constraints.NotEmpty;

public class SchoolPostRequestBody {
    @NotEmpty(message = "The name cannot be empty")
    private String name;
    private String address;
    private String email;
    private String logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
