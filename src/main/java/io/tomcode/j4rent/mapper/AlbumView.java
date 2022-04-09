package io.tomcode.j4rent.mapper;

import io.tomcode.j4rent.core.entities.Comment;
import io.tomcode.j4rent.core.entities.Image;
import io.tomcode.j4rent.core.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlbumView {

    private String name;

    private List<ImageView> images ;

}
