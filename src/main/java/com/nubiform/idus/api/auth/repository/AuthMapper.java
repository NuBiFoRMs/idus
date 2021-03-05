package com.nubiform.idus.api.auth.repository;

import com.nubiform.idus.api.auth.model.Auth;
import com.nubiform.idus.api.auth.model.Sign;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AuthMapper {
    Auth signIn(Sign sign);
}
