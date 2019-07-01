package com.example.createFileBatch.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Customer {

        private String name;
        private String age;
        private Address address;

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address=" + address +
                '}';
    }

    @Builder
    public Customer(String name, String age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
