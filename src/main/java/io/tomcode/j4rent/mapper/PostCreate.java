package io.tomcode.j4rent.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreate {

    private String contents;

    private double latitude;

    private double longitude;

    private BigInteger price;

    private float floorArea;

    private String address;

    private String furnitureStatus;

    private UUID createdById;

    private Date createdAt;

    private AlbumCreate album;
}
