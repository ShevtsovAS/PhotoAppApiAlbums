/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.photoapp.api.albums.io.controllers;

import com.appsdeveloperblog.photoapp.api.albums.service.AlbumsService;
import com.appsdeveloperblog.photoapp.api.albums.ui.model.AlbumResponseModel;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users/{id}/albums")
public class AlbumsController {

    private final AlbumsService albumsService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AlbumsController(AlbumsService albumsService) {
        this.albumsService = albumsService;
    }

    @GetMapping
    public List<AlbumResponseModel> userAlbums(@PathVariable String id) {
        ModelMapper mapper = new ModelMapper();
        List<AlbumResponseModel> userAlbums = albumsService.getAlbums(id).stream()
                .map(albumEntity -> mapper.map(albumEntity, AlbumResponseModel.class))
                .collect(toList());
        logger.info("Returning " + userAlbums.size() + " albums");
        return userAlbums;
    }
}
