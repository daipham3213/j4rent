package io.tomcode.j4rent.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreate {

    private String content;

    private double latitude;

    private double longitude;

    private double price;

    private float floorArea;

    private String address;

    private String furnitureStatus;

    private Date createdDate;

    private AlbumCreate album;
}
