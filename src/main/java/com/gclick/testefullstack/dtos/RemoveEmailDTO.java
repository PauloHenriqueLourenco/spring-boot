package com.gclick.testefullstack.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemoveEmailDTO {

    private final Long clienteId;
    private final Long emailId;

}
