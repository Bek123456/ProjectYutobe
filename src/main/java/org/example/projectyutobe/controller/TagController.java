package org.example.projectyutobe.controller;


import org.example.projectyutobe.dto.TagDTO;
import org.example.projectyutobe.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @PostMapping("/create")
    public ResponseEntity<String>create(@RequestBody TagDTO tagDTO){
     return ResponseEntity.ok(tagService.create(tagDTO));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>update(@PathVariable Integer id,
                                        @RequestBody TagDTO tagDTO){
        String update = tagService.update(id, tagDTO);
        return ResponseEntity.ok(update);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>delete(@PathVariable Integer id){
        String update = tagService.delete(id);
        return ResponseEntity.ok(update);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<TagDTO>> getAll(){
       return ResponseEntity.ok(tagService.getAll());
    }

}
