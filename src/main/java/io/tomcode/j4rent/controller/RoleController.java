package io.tomcode.j4rent.controller;


import io.tomcode.j4rent.core.entities.Role;
import io.tomcode.j4rent.core.services.IRoleService;
import io.tomcode.j4rent.mapper.ResponseResult;
import io.tomcode.j4rent.mapper.RoleDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/role")
@CrossOrigin(origins = "${app.security.cors.origin}", allowedHeaders = "*")
public class RoleController {
    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Role> createRole(@RequestBody String role) {
        try {
            Role newRole = roleService.createRole(role);
            return new ResponseEntity<>(newRole, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<ResponseResult> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                 @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<RoleDetails> pageRole = roleService.getAllRole(pageable);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", pageRole), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        return new ResponseEntity<>(roleService.getRoleByName(name), HttpStatus.OK);
    }

}
