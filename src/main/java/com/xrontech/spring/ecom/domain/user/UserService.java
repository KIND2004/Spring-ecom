package com.xrontech.spring.ecom.domain.user;

import com.xrontech.spring.ecom.domain.user.address.AddressType;
import com.xrontech.spring.ecom.domain.user.address.UserAddress;
import com.xrontech.spring.ecom.domain.user.address.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;

    public ResponseEntity<?> userUpdateProfile(UserUpdateProfileDTO userUpdateProfileDTO) {
        if ((userRepository.findByMobile(userUpdateProfileDTO.getMobile()).isPresent()) && (!userRepository.findById(1L).get().getMobile().equals(userRepository.findByMobile(userUpdateProfileDTO.getMobile()).get().getMobile()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile Already Exist");
        } else {
            Optional<User> optionalUser = userRepository.findById(1L);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setFirstName(userUpdateProfileDTO.getFirstName());
                user.setLastName(userUpdateProfileDTO.getLastName());
                user.setMobile(userUpdateProfileDTO.getMobile());
                userRepository.save(user);
            }

            UserAddress userAddress;
            Optional<UserAddress> optionalUserAddress = userAddressRepository.findByUserId(1L);
            if (optionalUserAddress.isEmpty()) {
                userAddress = new UserAddress();
            } else {
                userAddress = optionalUserAddress.get();
            }
            userAddress.setUserId(1L);
            userAddress.setAddressNumber(userUpdateProfileDTO.getAddressNumber());
            userAddress.setStreetName(userUpdateProfileDTO.getStreetName());
            userAddress.setCity(userUpdateProfileDTO.getCity());
            userAddress.setZipCode(userUpdateProfileDTO.getZipCode());
            userAddress.setAddressType(AddressType.HOME);

            userAddressRepository.save(userAddress);

            return ResponseEntity.status(HttpStatus.OK).body("User Profile Updated Successfully!");
        }
    }

    public ResponseEntity<?> userUpdateProfileImage(MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Upload Profile Image");
        } else {
            try {
                String projectRoot = System.getProperty("user.dir");

                String originalFilename = file.getOriginalFilename();
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFileName = UUID.randomUUID() + fileExtension;

                String imagePath = "/uploads/" + newFileName;
                Path path = Paths.get(projectRoot + imagePath);
                File saveFile = new File(String.valueOf(path));
                file.transferTo(saveFile);

                User user = userRepository.findById(1L).get();
                user.setProfileImagePath(imagePath);
                userRepository.save(user);

                return ResponseEntity.status(HttpStatus.OK).body("Profile Image Updated Successfully!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
