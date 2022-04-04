package io.tomcode.j4rent.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DocumentCreate {

    String documentCode;

    JsonNode data;

}
