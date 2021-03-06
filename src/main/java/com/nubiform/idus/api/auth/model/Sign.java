package com.nubiform.idus.api.auth.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sign {
    private String id;
    private String password;
}
