package com.andrejeu.springtestproject.controllers;

import com.andrejeu.springtestproject.model.Category;
import com.andrejeu.springtestproject.model.Picture;
import com.andrejeu.springtestproject.model.PictureDto;
import com.andrejeu.springtestproject.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * Rest controller for pictures manipulation. The base url is "/picture". Controller
 * contains methods which are mapped to GET, POST, DELETE, PUT http requests.
 *
 * */
@RestController("/picture")
public class PictureController {

    /**
     * Service for pictures data processing
     * */
    @Autowired
    PictureService pictureService;

    /**
     * GET http method processor for pictures. Returns picture by id and takes it's id as a parameter
     * for picture searching and delegates search to
     * {@link com.andrejeu.springtestproject.services.PictureService#getPictureById(Long)}
     *
     * @param pictureId the Id of a picture which is searched
     * @return {@link com.andrejeu.springtestproject.model.PictureDto} object instance to client
     * */
    @RequestMapping(
            method = RequestMethod.GET,
            params = {"id"}
    )
    public PictureDto getPictureById(@RequestParam("id") Long pictureId) {
        return pictureService.getPictureById(pictureId);
    }

    /**
     * POST http method processor. It persists a picture into db.
     * Client has to send data with header "Content-Type" with value multipart/form-data.
     * The data needs to be presented in body of a request in form data format.
     * It must have following fields:
     *  1) Image data;
     *  2) Category;
     *  3) Title;
     *  4) Description;
     * Method converts image data into bytes, puts it in {@link com.andrejeu.springtestproject.model.PictureDto}
     * and delegates persistence further to
     * {@link com.andrejeu.springtestproject.services.PictureService#postPicture(PictureDto)}
     *
     * @param uploadedFile expected image data
     * @param category category of a picture
     * @param title title of a picture
     * @param description description of a picture
     * */
    @RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    public void postPicture(
            @RequestParam("image") MultipartFile uploadedFile,
            @RequestParam("category") String category,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = uploadedFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PictureDto picture = new PictureDto ();

        picture.setImage(imageBytes);
        picture.setCategory(Category.valueOf(category));
        picture.setTitle(title);
        picture.setDescription(description);

        System.out.println(picture);

        pictureService.postPicture(picture);
    }

    /**
     * PUT  method processor. It update a picture in a db.
     * Client has to send data with header "Content-Type" with value multipart/form-data.
     * The data needs to be presented in body of a request in form data format.
     * It must have following fields:
     *  1) Image data;
     *  2) Category;
     *  3) Title;
     *  4) Description;
     * Method converts image data into bytes, puts it in {@link com.andrejeu.springtestproject.model.PictureDto}
     * and delegates persistence further to
     * {@link com.andrejeu.springtestproject.services.PictureService#editPicture(Picture)}
     *
     * @param uploadedFile expected image data
     * @param category category of a picture
     * @param title title of a picture
     * @param description description of a picture
     * */
    @RequestMapping(method = RequestMethod.PUT, consumes = "multipart/form-data")
    public void putPicture(
            @RequestParam("image") MultipartFile uploadedFile,
            @RequestParam("id") Long id,
            @RequestParam("category") String category,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = uploadedFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picture picture = new Picture ();

        picture.setId(id);
        picture.setImage(imageBytes);
        picture.setCategory(Category.valueOf(category));
        picture.setTitle(title);
        picture.setDescription(description);

        System.out.println(picture);

        pictureService.editPicture(picture);
    }

    /**
     * DELETE http method processor for pictures. It deletes picture by id and takes it's id as a parameter
     * for picture deleting and delegates search to
     * {@link com.andrejeu.springtestproject.services.PictureService#removePicture(Long)}
     *
     * @param pictureId the Id of a picture which is deleted
     * */
    @RequestMapping(
            method = RequestMethod.DELETE,
            params = "id"
    )
    public void deletePicture(@RequestParam("id") Long pictureId) {
        pictureService.removePicture(pictureId);
    }

    /**
     * GET http method processor for pictures with pagination. It get pictures page with 10 pictures by default
     * by page-index. It delegates the search to
     * {@link com.andrejeu.springtestproject.services.PictureService#getPicturesPage(int)}
     *
     * @param pageIndex the index of a page of picture
     *
     * @return Page of pictures
     * */
    @RequestMapping(
            method = RequestMethod.GET,
            params = {"page-index"}
    )
    public Iterable<Picture> getPicturesPage(@RequestParam("page-index") Integer pageIndex) {
        return pictureService.getPicturesPage(pageIndex);
    }


    /**
     * GET http method processor for pictures with pagination. It get pictures page with 10 pictures by default
     * by page-index. Also filters pictures by beginning of a title
     * It delegates the search to
     * {@link com.andrejeu.springtestproject.services.PictureService#getPicturesPage(int, String)}
     *
     * @param pageIndex the index of a page of picture
     * @param titleBeginsWith the string from which the picture title needs to begin
     *
     * @return Page of pictures
     * */
    @RequestMapping(
            method = RequestMethod.GET,
            params = {"page-index", "title-begins-with"}
    )
    public Iterable<Picture> getPicturesPage(
            @RequestParam("page-index") Integer pageIndex,
            @RequestParam("title-begins-with") String titleBeginsWith
     ) {
        System.out.println("Begins with : " + titleBeginsWith);

        return pictureService.getPicturesPage(pageIndex, titleBeginsWith);
    }


}
