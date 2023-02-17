package se.group5.booklender_rest.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.group5.booklender_rest.exception.DataDuplicateException;
import se.group5.booklender_rest.exception.DataNotFoundException;
import se.group5.booklender_rest.models.LibraryUser;
import se.group5.booklender_rest.models.dto.LibraryUserDto;
import se.group5.booklender_rest.repository.LibraryUserRepository;
import se.group5.booklender_rest.service.LibraryUserService;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {

    @Autowired
    LibraryUserRepository libraryUserRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public LibraryUserDto findById(Integer userId) {
        if (userId == null) throw new IllegalArgumentException("user Id was null");
        Optional<LibraryUser> optional = libraryUserRepository.findById(userId);
        if (optional.isPresent()) {
            LibraryUser entity = optional.get();
            return modelMapper.map(entity, LibraryUserDto.class);
        }
        return null;
    }

    @Override
    public LibraryUserDto findByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("user email was null");
        Optional<LibraryUser> optional = libraryUserRepository.findByEmail(email);
        if (optional.isPresent()) {
            LibraryUser entity = optional.get();
            return modelMapper.map(entity, LibraryUserDto.class);
        }
        return null;
    }

    @Override
    public List<LibraryUserDto> findAll() {
        Iterable<LibraryUser> libraryUsersList = libraryUserRepository.findAll();
        return modelMapper.map(libraryUsersList, new TypeToken<List<LibraryUserDto>>() {
        }.getType());
    }

    @Override
    public LibraryUserDto create(LibraryUserDto libraryUserDto) {
        if (libraryUserDto == null) throw new IllegalArgumentException("libraryUser data was null");
        if (libraryUserDto.getUserId() != 0)
            throw new IllegalArgumentException("libraryUser Id should not be null or zero");

        LibraryUser entity = libraryUserRepository.save(modelMapper.map(libraryUserDto, LibraryUser.class));
        return modelMapper.map(entity, LibraryUserDto.class);
    }

    @Override
    public LibraryUserDto update(LibraryUserDto libraryUserDto) {
        if (libraryUserDto == null) throw new IllegalArgumentException("libraryUser data was null");
        if (libraryUserDto.getUserId() == 0)
            throw new IllegalArgumentException("libraryUser Id should not be null or zero");
        if (!libraryUserRepository.findById(libraryUserDto.getUserId()).isPresent())
            throw new DataNotFoundException("Data not found");
        if (libraryUserRepository.findByEmail(libraryUserDto.getEmail()).isPresent())
            throw new DataDuplicateException("Duplicate error");
        LibraryUser libraryUser = libraryUserRepository.save(modelMapper.map(libraryUserDto, LibraryUser.class));
        return modelMapper.map(libraryUser, LibraryUserDto.class);
    }

    @Override
    public boolean delete(Integer userId) {
        LibraryUserDto libraryUserDto = findById(userId);
        if (libraryUserDto != null) {
            libraryUserRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
