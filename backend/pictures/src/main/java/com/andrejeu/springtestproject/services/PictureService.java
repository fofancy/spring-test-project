package com.andrejeu.springtestproject.services;

import com.andrejeu.springtestproject.data.PictureRepository;
import com.andrejeu.springtestproject.model.Picture;
import com.andrejeu.springtestproject.model.PictureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * Service for manipulations with pictures.
 * */
@Service
public class PictureService {
    /**
     * Repository for interacting with pictures in db
     * */
    @Autowired
    PictureRepository pictureRepository;

    /**
     * Specified default length of a page.
     *
     * TODO: add special property to application.properties
     * */
    private int PAGE_LENGTH = 10;

    /**
     * Returns picture by id and takes it's id as a parameter.
     *
     * @param id the Id of a picture which is searched
     * @return {@link com.andrejeu.springtestproject.model.PictureDto} object instance to controller
     * */
    public PictureDto getPictureById(Long id) {
        return new PictureDto(pictureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wrong picture id")));
    }

    /**
     * Persists picture into database.
     *
     * @param picture {@link com.andrejeu.springtestproject.model.PictureDto} object instance for data persistance
     * */
    public void postPicture(PictureDto picture) {

        pictureRepository.save(new Picture(picture));

        System.out.println(picture.toString());
    }

    /**
     * Updates picture's data in database.
     *
     * @param picture {@link com.andrejeu.springtestproject.model.Picture} object instance for data persistance
     * */
    public void editPicture(Picture picture) {
        pictureRepository.save(picture);
    }

    /**
     * Removes picture from database.
     *
     * @param pictureId picture id for deleting
     * */
    public void removePicture(Long pictureId) {
        pictureRepository.removeById(pictureId);
    }

    /**
     * Returns specified page of pictures
     *
     * @param pageIndex the index of a page which is searched
     *
     * @return specified page of pictures
     *
     * */
    public Iterable<Picture> getPicturesPage(int pageIndex) {
        return pictureRepository.findAll(PageRequest.of(pageIndex, PAGE_LENGTH));
    }

    /**
     * Returns specified page of pictures which title begins with appropriate string
     *
     * @param pageIndex the index of a page which is searched
     * @param titleBeginsWith string from which picture title must begin with
     *
     * @return specified page of pictures which title begins with appropriate string
     *
     * */
    public Iterable<Picture> getPicturesPage(int pageIndex, String titleBeginsWith) {
        return pictureRepository.findByTitleLike(titleBeginsWith, PageRequest.of(pageIndex, PAGE_LENGTH));
    }


}
