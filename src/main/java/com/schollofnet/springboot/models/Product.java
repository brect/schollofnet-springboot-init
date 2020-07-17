package com.schollofnet.springboot.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Cant not be empty")
    @NotBlank(message = "Cant not be blank")
    @Size(min = 4, max = 255)
    private String name;


    @NotEmpty(message = "Cant not be empty")
    @Min(value = 0)
    @Max(value = 1000)
    private Integer qtd;

    private Date dateCreated;

    public Product() {
    }

    public Product(String name, Integer qtd) {
        this.name = name;
        this.qtd = qtd;
    }


    @PrePersist
    public void onPrePersist() {
        if (this.dateCreated == null) {
            this.dateCreated = new Date();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qtd=" + qtd +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
