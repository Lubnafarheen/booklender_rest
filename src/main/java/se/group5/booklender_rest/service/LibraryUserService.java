package se.group5.booklender_rest.service;

import se.group5.booklender_rest.models.dto.LibraryUserDto;

import java.util.List;

public interface LibraryUserService {

    LibraryUserDto findById(Integer userId);

    LibraryUserDto findByEmail(String email);

    List<LibraryUserDto> findAll();

    LibraryUserDto create(LibraryUserDto libraryUserDto);

    LibraryUserDto update(LibraryUserDto libraryUserDto);

    boolean delete(Integer userId);


}
