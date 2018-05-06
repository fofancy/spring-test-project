package com.andrejeu.testtaskossystem.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Orm entity class for pictures. Table name is "picture"
 *
 * */
@Entity
@Table(name = "picture")
@Data
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PICT_SEQ")
    @SequenceGenerator(sequenceName = "picture_seq", allocationSize = 1, name = "PICT_SEQ")
    @Column(name = "pctr_id")
    private Long id;

    @Lob
    @Column(name = "pctr_image")
    private byte[] image;

    @Column(name = "pctr_category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "pctr_title")
    private String title;

    @Column(name = "pctr_description")
    private String description;

    /**
     * Default constructor
     * */
    public Picture() {

    }

    /**
     * Constructor for converting from {@link com.andrejeu.testtaskossystem.model.PictureDto} object instance
     * to Picture entity. Also doesn't take id value because it db staff.
     *
     * @param picture {@link com.andrejeu.testtaskossystem.model.PictureDto} for converting.
     * */
    public Picture(PictureDto picture) {
        image = picture.getImage();
        category = picture.getCategory();
        title = picture.getTitle();
        description = picture.getDescription();
    }
}
