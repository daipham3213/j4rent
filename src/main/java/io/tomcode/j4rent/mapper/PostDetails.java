package io.tomcode.j4rent.mapper;

import io.tomcode.j4rent.core.entities.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostDetails {

    private String content;

    private double latitude;

    private double longitude;

    private double price;

    private float floorArea;

    private String address;

    private String furnitureStatus;

    private AlbumLoad album;

}
