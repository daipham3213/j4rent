package io.tomcode.j4rent.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    double distance;
    double  latitude;
    double longitude;
    int floorArea;
    int minPrice;
    int maxPrice;
}
