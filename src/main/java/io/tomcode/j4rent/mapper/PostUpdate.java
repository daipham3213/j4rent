package io.tomcode.j4rent.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdate {
    private UUID id;

    private String contents;

    private double latitude;

    private double longitude;

    private BigInteger price;

    private float floorArea;

    private String address;

    private String furnitureStatus;

    private AlbumCreate album;

}
