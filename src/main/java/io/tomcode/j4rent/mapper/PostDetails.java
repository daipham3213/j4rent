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

public class PostDetails {

    private UUID documentId;

    private String contents;

    private double latitude;

    private double longitude;

    private BigInteger price;

    private float floorArea;

    private String furnitureStatus;

    private Date createdAt;

    private UserInfo createdBy;

    private int views;

    private AlbumView album;

    private int sumComment;

}
