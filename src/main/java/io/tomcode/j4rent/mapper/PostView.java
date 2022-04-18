package io.tomcode.j4rent.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class
PostView {
    private String contents;

    private double latitude;

    private double longitude;

    private double price;

    private float floorArea;

    private String furnitureStatus;

    private AlbumView album;

}
