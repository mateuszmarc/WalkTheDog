package com.mateuszmarcyk.walkthedog.user;

import com.mateuszmarcyk.walkthedog.conversation.dto.ConversationDTO;
import com.mateuszmarcyk.walkthedog.conversation.dto.ConversationMapper;
import com.mateuszmarcyk.walkthedog.dog.dto.DogDTO;
import com.mateuszmarcyk.walkthedog.dog.dto.DogMapper;
import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.exception.UserAlreadyExistsException;
import com.mateuszmarcyk.walkthedog.registration.RegistrationRequest;
import com.mateuszmarcyk.walkthedog.registration.token.VerificationToken;
import com.mateuszmarcyk.walkthedog.registration.token.VerificationTokenRepository;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import com.mateuszmarcyk.walkthedog.user.dto.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ALREADY_EXISTS_MESSAGE = "User with email '%s' already exists.";

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    @Value("${userWithEmailNotFoundExceptionMessage}")
    private String userWithEmailNotFoundExceptionMessage;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserMapper userMapper;
    private final DogMapper dogMapper;
    private final ConversationMapper conversationMapper;

    private UserDTO getUserFetchDogsDTO(User user) {
        UserDTO userDTO = userMapper.toDTO(user);
        List<DogDTO> dogDTOs = user.getDogs().stream().map(dogMapper::toDTO).toList();
        userDTO.setDogsDto(dogDTOs);
        return userDTO;
    }

    private UserDTO getUserFetchFriendsDTO(User user) {
        UserDTO userDTO = userMapper.toDTO(user);
        List<UserDTO> friendsDTO = user.getFriends().stream().map(userMapper::toDTO).toList();
        userDTO.setFriendsDto(friendsDTO);
        return userDTO;
    }

    private UserDTO getUserFetchConversationsDTO(User user) {
        UserDTO userDTO = userMapper.toDTO(user);
        List<ConversationDTO> conversationDTOs = user.getConversations().stream().map(conversationMapper::toDTO).toList();
        userDTO.setConversationsDto(conversationDTOs);
        return userDTO;
    }


    @Override
    public List<UserDTO> findAll() {

        List<User> users = userRepository.findAll();

        List<UserDTO> userDTOs = users.stream().map(userMapper::toDTO).toList();
        return userDTOs;
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);

        return foundUser.map(userMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("User", id)));
    }

    @Override
    public User findUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("User", id)));
    }

    @Override
    public UserDTO findByIdJoinFetchDogs(Long id) {
        Optional<User> optionalUser = userRepository.findByIdJoinFetchDogs(id);

        return optionalUser.map(this::getUserFetchDogsDTO)
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("User", id)));
    }


    @Override
    public UserDTO findByEmailJoinFetchDogs(String email) {
        Optional<User> optionalUser = userRepository.findByEmailJoinFetchDogs(email);

        return optionalUser.map(this::getUserFetchDogsDTO)
                .orElseThrow(() -> new ResourceNotFoundException(userWithEmailNotFoundExceptionMessage.formatted(email)));

    }


    @Override
    public UserDTO findByEmailFetchFriends(String email) {

        Optional<User> foundUser = userRepository.findByEmailFetchFriends(email);
        return foundUser.
                map(this::getUserFetchFriendsDTO).
                orElseThrow(() -> new ResourceNotFoundException(userWithEmailNotFoundExceptionMessage.formatted("User", email)));

    }


    @Override
    public UserDTO findByEmailFetchConversations(String email) {

        Optional<User> foundUser = userRepository.findByEmailFetchConversations(email);
        return foundUser
                .map(this::getUserFetchConversationsDTO)
                .orElseThrow(() -> new ResourceNotFoundException(userWithEmailNotFoundExceptionMessage.formatted("User", email)));

    }


    @Override
    public User register(RegistrationRequest request) {

        Optional<User> foundUser = userRepository.findByEmail(request.getEmail());

        if (foundUser.isPresent()) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS_MESSAGE.formatted(request.getEmail()));
        }

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

        return userRepository.save(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.map(userMapper::toDTO)
                .orElseThrow(() -> new UsernameNotFoundException(userWithEmailNotFoundExceptionMessage.formatted(email)));
    }

    @Override
    public User findUserByEmail(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        return optionalUser.orElseThrow(() -> new ResourceNotFoundException(userWithEmailNotFoundExceptionMessage.formatted(email)));
    }

    @Override
    public void saveVerificationToken(User user, String verificationToken) {

        VerificationToken token = new VerificationToken(verificationToken, user);
        verificationTokenRepository.save(token);
    }

    @Override
    public String validateToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            return "Invalid verification token";
        }
        User user = verificationToken.getUser();

        Calendar calendar = Calendar.getInstance();

        if (verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "Token already expired";
        }

        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public UserDTO save(User user, UserDTO userDTO) {

        setUserFieldsFromUserDTO(user, userDTO);

        User saved = userRepository.save(user);
       return userMapper.toDTO(saved);

    }

    private static void setUserFieldsFromUserDTO(User user, UserDTO userDTO) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setBio(userDTO.getBio());
        user.setProfileImageUrl(userDTO.getProfileImageUrl());
    }

    @Override
    public UserDTO changePassword(User user, String password) {

        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return userMapper.toDTO(user);
    }


}
