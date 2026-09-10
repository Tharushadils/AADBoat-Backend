package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.UserDTO;
import lk.ijse.boat_reservation.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userDTO.getUserId(), userDTO);
        return new CommonResponse(OPERATION_SUCCESS, updatedUser, SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getUserById(@PathVariable long userId) {
        UserDTO user = userService.getUserById(userId);
        return new CommonResponse(OPERATION_SUCCESS, user, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new CommonResponse(OPERATION_SUCCESS, users, SUCCESS_MESSAGE);
    }
}