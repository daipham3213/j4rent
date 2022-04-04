package io.tomcode.j4rent.mapper;

import io.tomcode.j4rent.core.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PostResult {
    private List<PostDetails> posts ;
    private int page;
    private int size;


    public PostResult(List<PostDetails> content, int size, int pageNumber) {
        this.posts =content;
        this.size=size;
        this.page = pageNumber;
    }
}
