package se.group5.booklender_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.group5.booklender_rest.models.dto.LibraryUserDto;
import se.group5.booklender_rest.service.LibraryUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libraryUser")
public class LibraryUserController {

    @Autowired
    LibraryUserService libraryUserService;

    @GetMapping("/{userId}")
    public ResponseEntity<LibraryUserDto> findById(@PathVariable Integer userId) {
        return ResponseEntity.ok(libraryUserService.findById(userId));
    }

    @GetMapping("/{email}")
    public ResponseEntity<LibraryUserDto> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(libraryUserService.findByEmail(email));
    }

    @GetMapping("/")
    public ResponseEntity<List<LibraryUserDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(libraryUserService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<LibraryUserDto> create(@RequestBody LibraryUserDto libraryUserDto) {
        LibraryUserDto createdlibraryUserDto = libraryUserService.create(libraryUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdlibraryUserDto);
    }

    @PutMapping("/")
    public ResponseEntity<LibraryUserDto> update(@RequestBody LibraryUserDto libraryUserDto) {
        LibraryUserDto updateDto = libraryUserService.update(libraryUserDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateDto);
    }

}
