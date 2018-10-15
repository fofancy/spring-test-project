package com.andrejeu.springtestproject.model;

import lombok.Data;

/**
 * It is a class which is used for interaction between client side
 * and server side of an application.
 *
 * Although in this project it looks a little redundant, usually it is a good practice
 * for client-side application development.
 * */
@Data
public class PictureDto {
    private byte[] image;
    private Category category;
    private String title;
    private String description;

    /**
     * Default constructor
     * */
    public PictureDto() {

    }

    /**
     * Constructor for converting from {@link com.andrejeu.springtestproject.model.Picture} object instance
     * to Picture Dto.
     *
     * @param picture {@link com.andrejeu.springtestproject.model.Picture} for converting.
     * */
    public PictureDto(Picture picture) {
        image = picture.getImage();
        category = picture.getCategory();
        title = picture.getTitle();
        description = picture.getDescription();
    }
}
