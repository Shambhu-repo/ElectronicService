package com.lcwd.electronic.store.services.implementation;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exception.ResourceNotFounException;
import com.lcwd.electronic.store.helper.Helper;
import com.lcwd.electronic.store.repositories.UserRepository;
import com.lcwd.electronic.store.services.UserService;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

Logger logger = Logger.getLogger(UserServiceImpl.class);
    //  private final ObjectMapper objectMapper = new ObjectMapper();    --> this is for the maping json to  object  ( from jacson library)
//   ModelMapper ---> it is used to ModelMapper is focused on object-to-object mapping within Java , like entity to DTO and DTO to Entity
    @Autowired
    private UserRepository userRepository;

    @Value("${user.profile.image.path}")
    private String imagePath;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createuser(UserDto userDto) {
        //generate random userId for the user  , here i am setting the UUID to the DTO
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);


        //dto --> entity
        User user = dtoToUser(userDto);
        User savedUser = userRepository.save(user);
//userDto --> user
        UserDto userdto = entityToDto(savedUser);
        return userdto;
    }


    @Override
    public UserDto updateuser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFounException("user not found by given id"));
        user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        User Updateduser = userRepository.save(user);

        UserDto updateDto = entityToDto(Updateduser);
        return updateDto;
    }

    @Override
    public void deleteuser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFounException("user not find to delete"));
        //delete user profile image  ---> it will delete the file or image from the images/users  folder im projec main root

        try {
            String fullPath = imagePath + user.getImageName();
            Path path = Paths.get(fullPath);
            Files.delete(path);
        }
        catch(NoSuchFileException noSuchFileException){
logger.info("-------------user Image file not found in folder , this is from noSuchfileException --------------- ");
noSuchFileException.printStackTrace();
        }
        catch(IOException ioException){
            logger.info("---------user Image file not found in folder  , this is from IOException  block-------------");
            ioException.printStackTrace();
        }


        userRepository.delete(user);

    }

    @Override
    public PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {
        // Sort sort =  Sort.by(sortBy);
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> page = userRepository.findAll(pageable); //paginationandsorting interface gives this method to take the pagable and gie page object
        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);
        return response;
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFounException("user not find by the given id"));
        UserDto userDto = entityToDto(user);

        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFounException("no user with given email id"));
        UserDto userDto = entityToDto(user);

        return userDto;

    }

    //it will search the user by matching or containing the keywords
    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> userDtoList = users.stream().map(this::entityToDto).collect(Collectors.toList());

        return userDtoList;
    }

    private UserDto entityToDto(User savedUser) {   // it will take User Entity and give UserDto

        //        UserDto userDto = UserDto.builder()
//        .userId(savedUser.getUserId())
//        .name(savedUser.getName())
//        .email(savedUser.getEmail())
//        .password(savedUser.getPassword())
//        .about(savedUser.getAbout())
//        .gender(savedUser.getGender())
//        .imageName(savedUser.getImageName())
//        .build();


        return modelMapper.map(savedUser, UserDto.class);  // this is by using mapper class
    }

    private User dtoToUser(UserDto userDto) {     // it will take userDto and giev user entity

//here using builder pattern we are creating the user object from the UserDto
//        User user =   User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();

        return modelMapper.map(userDto, User.class);


        /* given below code can use while using jacson library

        User user = objectMapper.convertValue(userDto, User.class);
        return user;
         */


    }

}
