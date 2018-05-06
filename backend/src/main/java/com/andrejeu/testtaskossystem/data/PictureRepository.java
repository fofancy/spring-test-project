package com.andrejeu.testtaskossystem.data;

import com.andrejeu.testtaskossystem.model.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  paging and sorting repository for
 * {@link com.andrejeu.testtaskossystem.model.Picture} entity.
 *
 * */
@Repository
public interface PictureRepository extends PagingAndSortingRepository<Picture, Long> {

    /**
     * Method which is used for deleting pictures by their id
     *
     * @param id id of a picture
     * */
    @Transactional
    void removeById(long id);

    /**
     * Returns specified page of pictures which title begins with appropriate string
     *
     * @param title string from which picture title must begin with
     * @param page the number and size of page which is searched
     *
     * @return specified page of pictures which title begins with appropriate string
     *
     * */
    @Query("Select p from Picture p where p.title like :title%")
    Page<Picture> findByTitleLike(@Param("title") String title, Pageable page);
}
