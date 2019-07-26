package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Blog implements Serializable {

    private Integer total;
    private List<Item> items;

    @Data
    public static class Item{
        String title;
        String link;
        String bloggername;
        String description;
        String postdate;
    }
}
