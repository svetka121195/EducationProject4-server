package app.controller;

import app.dto.RoleDto;
import app.dto.UserDto;
import app.model.Role;
import app.model.User;
import app.service.RoleServiceImp;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.naming.Context;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Светлана on 30.07.2018.
 */

@RestController
@RequestMapping("/admin")
public class UserController {
    private UserService userService;
    private RoleServiceImp roleService;

    @Autowired
    public void setUserService(UserService userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

   /*@GetMapping("/")
    public String home(Model model, @RequestHeader HttpHeaders headers){
        return "home";
    }*/

    /*@RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(Model model){
        return "error";
    }*/

    /*@RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model,  @AuthenticationPrincipal User principal){
        model.addAttribute("currentUser", principal);
        return "user";
    }*/

    @GetMapping("/users")
    public List<UserDto> usersList(){
        List<UserDto> list = handleUsers(userService.getAllUsers());
        return list;
    }

    @GetMapping("/roles")
    public @ResponseBody Set<RoleDto> rolesList(){
        Set<RoleDto> list = handleRoles(roleService.getAllRoles());
        return list;
    }

    @PostMapping("/users")
    public void addUser(@RequestBody UserDto user){
        User newUser = new User(user.getName(), user.getLogin(), user.getPassword(), processRolesDto(user.getRoles()));
        userService.addUser(newUser);
        //return userService.getUserByLogin(user.getLogin());
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody UserDto getUser(@PathVariable("id") long id){
        return new UserDto(userService.getUser(id));
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("id") long id, @RequestBody UserDto user){
        User newUser = new User(user.getName(), user.getLogin(), user.getPassword(), processRolesDto(user.getRoles()));
        newUser.setId(id);
        userService.updateUser(newUser);
    }



    private List<UserDto> handleUsers(List<User> oldList){
        ArrayList<UserDto> list = new ArrayList<>();
        for (User user: oldList){
            list.add(new UserDto(user));
        }
        return list;
    }

    private Set<RoleDto> handleRoles(Set<Role> oldList){
        HashSet<RoleDto> list = new HashSet<>();
        for (Role role: oldList){
            list.add(new RoleDto(role));
        }
        return list;
    }

    private Role getRole(RoleDto role) {
        return roleService.getRoleByName(role.getName());
    }

    private Set<Role> processRolesDto(Set<RoleDto> oldList){
        HashSet<Role> list = new HashSet<>();
        for (RoleDto role: oldList){
            list.add(roleService.getRoleByName(role.getName()));
        }
        return list;
    }

}
