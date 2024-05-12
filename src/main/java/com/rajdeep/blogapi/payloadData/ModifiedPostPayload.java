package com.rajdeep.blogapi.payloadData;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data

public class ModifiedPostPayload {

    private List<PostPayload> content;

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElements;

    private boolean isLastPage;

    private Integer totalPages;

}
