package org.example.projectyutobe.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.projectyutobe.dto.AttachDTO;
import org.example.projectyutobe.service.AttachService;
import org.example.projectyutobe.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;
    @PostMapping("/upload")
    @Operation( summary = "Attach", description = "this api used for attach")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(attachService.save(file));
        //return ResponseEntity.ok(attachService.saveToSystem(file));
    }
   @GetMapping(value = "/openById/{id}" , produces = MediaType.IMAGE_PNG_VALUE)
   @Operation( summary = "photoById", description = "thid api user photo by id open")
    public byte[] openbyId(@PathVariable String id){
//        return attachService.openById(id);
       return attachService.open_general(id);
   }

   @GetMapping("/download/{fineName}")
   public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
        return attachService.download(fileName);
    }
    @GetMapping("/paginationAttach")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageImpl<AttachDTO>> paginationAttach(@RequestParam(name = "page",defaultValue = "1")Integer page,
                                                                @RequestParam(name = "size",defaultValue = "1")Integer size){
        SpringSecurityUtil.getCurrentUser().getId();
        return ResponseEntity.ok(attachService.getPage(page,size));

    }
    @DeleteMapping("/deleted/{attachId}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<String>deletedById(@PathVariable String attachId){
        return ResponseEntity.ok(attachService.deleted(attachId));
    }
}
