package com.mdv.curso.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationQuery {

    private int page;
    private int size;

}
