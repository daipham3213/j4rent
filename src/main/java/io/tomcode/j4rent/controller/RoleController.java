package io.tomcode.j4rent.controller;


import io.tomcode.j4rent.core.entities.Role;
import io.tomcode.j4rent.core.services.IRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/role")
public class        RoleController {
    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping (value = "/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        try{
            Role newRole = roleService.createRole(role);
            return new ResponseEntity<>(newRole,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<Iterable<Role>> getAll(){
        return new ResponseEntity<>(roleService.getAllRole(),HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Role>getRoleByName(@PathVariable String name) {
        return new ResponseEntity<>(roleService.getRoleByName(name), HttpStatus.OK);
    }

}
