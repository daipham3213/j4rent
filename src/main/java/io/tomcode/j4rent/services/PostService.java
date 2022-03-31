package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Document;
import io.tomcode.j4rent.core.entities.Post;
import io.tomcode.j4rent.core.repositories.PostRepository;
import io.tomcode.j4rent.core.services.IPostService;
import org.springframework.stereotype.Service;

@Service("postService")
public class PostService implements IPostService {
    private  final PostRepository postRepository;
    private  final DocumentService documentService;

    public PostService(PostRepository postRepository, DocumentService documentService) {
        this.postRepository = postRepository;
        this.documentService = documentService;
    }

    @Override
    public void createPost(Post post) {
        Document document = documentService.createDocument("post" , post);
        documentService.createDocument(document);
        postRepository.save(post);
    }
}
