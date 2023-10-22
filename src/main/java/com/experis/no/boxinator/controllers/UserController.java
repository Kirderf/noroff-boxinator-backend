package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.exceptions.UserNotFoundException;
import com.experis.no.boxinator.mappers.UserMapper;
import com.experis.no.boxinator.models.User;
import com.experis.no.boxinator.models.dto.user.UserDTO;
import com.experis.no.boxinator.models.dto.user.UserPostDTO;
import com.experis.no.boxinator.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Fetch all the users.
     *
     * @return list of users
     */
    @GetMapping
    @Operation(summary = "Gets all Users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAll() {
        logger.info("Fetching all users.");
        Collection<UserDTO> users = userMapper.userToUserDTO(userService.findAll());
        logger.info("Successfully fetched all users.");
        return ResponseEntity.ok(users);
    }

    /**
     * Fetch user by ID.
     *
     * @param id user ID
     * @return user details
     */
    @GetMapping("{id}")
    @Operation(summary = "Gets a user by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            )

    })
    @PreAuthorize("hasAuthority('ID_' + #id) or hasRole('ADMIN')")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            logger.log(Level.INFO, "Fetching user with ID: {}", id);
            UserDTO user = userMapper.userToUserDTO(userService.findById(id));
            logger.log(Level.INFO, "Successfully fetched user with ID: {}", id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            logger.log(Level.WARNING, "User not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Something went wrong when fetching user with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Add a new user.
     *
     * @param entity user data
     * @return created status
     */
    @PostMapping
    @Operation(summary = "Adds a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserPostDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PreAuthorize("hasAuthority('ID_' + #entity.id) or hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody UserPostDTO entity) throws URISyntaxException {
        User user = userService.add(userMapper.userPostDTOToUser(entity));
        URI uri = new URI("api/v1/user/" + user.getId());
        logger.log(Level.INFO, "User with ID {} was created.", user.getId());
        return ResponseEntity.created(uri).build();
    }

    /**
     * Patch specific fields of a user.
     *
     * @param entity user data with changes
     * @return updated user data or bad request
     */
    @PatchMapping
    @Operation(summary = "Patches fields on a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserPostDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PreAuthorize("hasAuthority('ID_' + #entity.id) or hasRole('ADMIN')")
    public ResponseEntity<?> patchFields(@RequestBody UserPostDTO entity) {
        if (userService.exists(entity.getId())) {
            logger.log(Level.INFO, "Patching user with ID: {}", entity.getId());
            User user = userService.update(userMapper.userPostDTOToUser(entity));
            logger.log(Level.INFO, "Successfully patched user with ID: {}", entity.getId());
            return ResponseEntity.ok(user);
        }
        logger.log(Level.WARNING, "Failed to patch user with ID: {}. User does not exist.", entity.getId());
        return ResponseEntity.badRequest().build();
    }
}
