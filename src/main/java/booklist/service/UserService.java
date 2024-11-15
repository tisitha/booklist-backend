package booklist.service;

import booklist.model.User;
import booklist.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepo userRepo;

    Map<String,String> res = new HashMap<>();

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<String> registerUser(User user) {
        if(userRepo.findByUsername(user.getUsername())==null){
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
            return new ResponseEntity<>("Your account is successfully registered",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Username has taken",HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<?> login(User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            String uid = (userRepo.findByUsername(user.getUsername())).getId().toString();
            res.put("uid",uid);
            res.put("token",jwtService.generateToken(user.getUsername()));
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
        return new ResponseEntity<>("Incorrect username or password",HttpStatus.BAD_REQUEST);
    }

}
