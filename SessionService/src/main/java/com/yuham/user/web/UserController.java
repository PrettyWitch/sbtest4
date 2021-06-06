package com.yuham.user.web;

import com.yuham.user.enetity.User;
import com.yuham.user.exception.ErrorResponse;
import com.yuham.user.jwt.AjaxResult;
import com.yuham.user.jwt.JwtUtil;
import com.yuham.user.repository.UserRepository;
import com.yuham.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author yuhan
 * @date 06.05.2021 - 19:45
 * @purpose
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/findall")
    public List<User> findAll(){
        return userService.findAll();
    }


    @Operation(summary = "Find User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find User"),
            @ApiResponse(responseCode = "404", description = "User not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{userUid}")
    public User findByUid(@PathVariable int userUid){
        return userService.findByUserUid(userUid);
    }

    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create User"),
            @ApiResponse(responseCode = "400", description = "Bad request format", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
//            @ApiResponse(responseCode = "409", description = "Item not available", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/create")
    public String CreateUser(@Valid @RequestBody User user){
        return userService.CreateUser(user);
    }


    @Operation(summary = "User login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User login"),
            @ApiResponse(responseCode = "400", description = "Bad request format", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
//            @ApiResponse(responseCode = "409", description = "Item not available", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/login")
    public AjaxResult Login(@Valid @RequestBody User user){
        String name = user.getName();
        String password = user.getPassword();
//        //身份验证
//        boolean isSuccess =  userService.checkUser(name,password);
//        if (isSuccess){
//
//        }
        if (userService.Login(user)){
            String token = JwtUtil.sign(name,password);
            if (token != null) {
                return AjaxResult.success("成功",token);
            }
        }
        return AjaxResult.fail();
    }

    @PostMapping("/getUser")
    public AjaxResult getUser(HttpServletRequest request, @Valid @RequestBody User user){
        String name = user.getName();
//        String token = request.getHeader("Authorization");
        String token = request.getHeader("token");
        boolean verity = JwtUtil.verity(token);
        if (verity){
            User user2 = userService.getUser(name);
            if (user2 != null){
                return AjaxResult.success("成功", user2);
            }
        }
        return AjaxResult.fail();
    }

    @PostMapping("/getUserInfo")
    public AjaxResult getUserInfo(HttpServletRequest request){
//        String usernameFromToken = JwtUtil.getUsernameFromToken(request.getHeader("token"));
        String usernameFromToken = JwtUtil.getUsernameFromToken(request.getHeader("Authorization"));
        User user = userService.getUser(usernameFromToken);
        return AjaxResult.success("成功", user.toString());
    }
}
