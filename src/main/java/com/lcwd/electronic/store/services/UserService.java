package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserDto createuser(UserDto user);

    UserDto updateuser(UserDto user, String userId);

    void deleteuser(String id) throws IOException;


    PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

    UserDto getUserById(String id);

    UserDto getUserByEmail(String email);

  List<UserDto> searchUser(String Keyword);



}
