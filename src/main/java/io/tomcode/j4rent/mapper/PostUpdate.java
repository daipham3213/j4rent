package io.tomcode.j4rent.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdate {
    private UUID id;

    private String content;

    private double latitude;

    private double longitude;

    private double price;

    private float floorArea;

    private String address;

    private String furnitureStatus;

    private AlbumCreate album;
}
