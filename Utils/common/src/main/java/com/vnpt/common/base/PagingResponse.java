package com.vnpt.common.base;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PagingResponse<T> extends BaseResponse<T> {

    private long recordsTotal;

    private int totalPages;

    private int sizeOfPage;

    private int recordsFiltered;

}
