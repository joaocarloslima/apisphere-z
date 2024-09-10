package br.com.fiap.apisphere.user;

import br.com.fiap.apisphere.user.dto.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public UserProfileResponse getUserProfile(String email) {
        return repository.findByEmail(email)
                .map(UserProfileResponse::new)
                .orElseThrow(() -> new UsernameNotFoundException("Uset not found"));
    }

    public void setUserAvatar(String email, MultipartFile file) {

        // verificar se o arquivo existe
        if (file.isEmpty()){
            throw new RuntimeException("File is required");
        }

        // copiar o arquivo para o disco
        try (InputStream in = file.getInputStream()){
            Path directory = Path.of("src/main/resources/static/avatars");
            Path pathFile = directory
                    .resolve(  System.currentTimeMillis() + email + file.getOriginalFilename() )
                    .normalize()
                    .toAbsolutePath();

            Files.copy(in, pathFile);

            System.out.println("Arquivo copiado");

            var avatarUrl = "http://localhost:8082/users/avatar/" + pathFile.getFileName();
            var user = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            user.setAvatar(avatarUrl);
            repository.save(user);

        }catch (Exception e){
            System.out.println("Erro ao copiar arquivo");
        }

    }

    public ResponseEntity<Resource> getAvatar(String filename) {

        Path path = Path.of("src/main/resources/static/avatars/" + filename);
        Resource file = UrlResource.from(path.toUri());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);

    }
}
