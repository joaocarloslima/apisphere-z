package br.com.fiap.apisphere.user;

import br.com.fiap.apisphere.user.dto.UserFormRequest;
import br.com.fiap.apisphere.user.dto.UserProfileResponse;
import br.com.fiap.apisphere.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public List<UserResponse> findAll(@RequestParam String name){
        return service
                .findByName(name)
                .stream()
                .map(UserResponse::fromModel)
                .toList();
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserFormRequest userForm, UriComponentsBuilder uriBuilder){
        var user = service.create(userForm.toModel());

        var uri = uriBuilder
                .path("/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(UserResponse.fromModel(user));
    }

    @GetMapping("profile")
    public UserProfileResponse getUserProfile(){
        var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return service.getUserProfile(email);
    }

    @PostMapping("avatar")
    public void uploadAvatar(@RequestBody MultipartFile file){
        var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        service.setUserAvatar(email, file);
    }

    @GetMapping("avatar/{filename}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String filename){
        return service.getAvatar(filename);
    }

}
