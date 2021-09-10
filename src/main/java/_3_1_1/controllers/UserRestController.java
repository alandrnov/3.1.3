package _3_1_1.controllers;

import _3_1_1.models.Role;
import _3_1_1.models.User;
import _3_1_1.service.UserService;
import _3_1_1.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api")
public class UserRestController {
    private UserService service;

    @Autowired
    public UserRestController(UserServiceImpl userService) {
        this.service = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(service.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        return ResponseEntity.ok().body(service.getUserById(id));
    }

    @PostMapping("")
    //public  ResponseEntity<User> addUser(@RequestBody User user, @RequestParam("user_roles") String rol){
    public  ResponseEntity<User> addUser(@RequestBody User user){
        //user.setRoles(service.getRolesFromText(rol));
        service.addUser(user);
        return ResponseEntity.ok().body(user);
    }
    @PutMapping("")
    public ResponseEntity <User> updateUser (@RequestBody User user){

        service.updateUser(user);
        return ResponseEntity.ok().body(user);

    }
    @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable long id){
        service.deleteUser(id);
        return "User " + id + " deleted";
  }

}
