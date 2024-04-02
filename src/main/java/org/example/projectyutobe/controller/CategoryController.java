package org.example.projectyutobe.controller;


import org.example.projectyutobe.dto.CategoryDTO;
import org.example.projectyutobe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controller")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>created(@RequestBody CategoryDTO dto){
      String create=categoryService.created(dto);
      return ResponseEntity.ok(create);
    }

   @PutMapping("/update/{categoryId}")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<String>update(@RequestBody CategoryDTO dto,
                                       @PathVariable Integer categoryId){
       String create=categoryService.update(dto,categoryId);
       return ResponseEntity.ok(create);
   }
   @DeleteMapping("/delete/{categoryId}")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<String>delete(@PathVariable Integer categoryId){
      return ResponseEntity.ok(categoryService.delete(categoryId));
   }
   @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>>getAll(){
        return ResponseEntity.ok(categoryService.getAll());
   }

}
