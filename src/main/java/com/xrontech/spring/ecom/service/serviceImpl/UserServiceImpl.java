package com.xrontech.spring.ecom.service.serviceImpl;

import com.xrontech.spring.ecom.dto.UserUpdateProfileDTO;
import com.xrontech.spring.ecom.model.AddressType;
import com.xrontech.spring.ecom.model.User;
import com.xrontech.spring.ecom.model.UserAddress;
import com.xrontech.spring.ecom.repository.UserAddressRepository;
import com.xrontech.spring.ecom.repository.UserRepository;
import com.xrontech.spring.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;

    @Override
    public ResponseEntity<?> userUpdateProfile(UserUpdateProfileDTO userUpdateProfileDTO) {
        if (userUpdateProfileDTO.getFirstName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("First Name not Found");
        } else if (userUpdateProfileDTO.getLastName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Last Name not Found");
        } else if (userUpdateProfileDTO.getMobile().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mobile not Found");
        } else if (!Pattern.compile("^07[01245678][0-9]{7}$").matcher(userUpdateProfileDTO.getMobile()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Mobile Number");
        } else if ((userRepository.findByMobile(userUpdateProfileDTO.getMobile()).isPresent()) && (!userRepository.findById(1L).get().getMobile().equals(userRepository.findByMobile(userUpdateProfileDTO.getMobile()).get().getMobile()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile Already Exist");
        } else if ((!userUpdateProfileDTO.getZipCode().equals("")) && (userUpdateProfileDTO.getZipCode().length() != 5)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Zip Code");
        } else {

            User user = userRepository.findById(1L).get();
            user.setFirstName(userUpdateProfileDTO.getFirstName());
            user.setLastName(userUpdateProfileDTO.getLastName());
            user.setMobile(userUpdateProfileDTO.getMobile());

            userRepository.save(user);

            UserAddress userAddress;

            Optional<UserAddress> optionalUserAddress = userAddressRepository.findByUserId(1L);
            if (optionalUserAddress.isEmpty()) {
                userAddress = new UserAddress();
                userAddress.setUserId(1L);
                userAddress.setAddressNumber(userUpdateProfileDTO.getAddressNumber());
                userAddress.setStreetName(userUpdateProfileDTO.getStreetName());
                userAddress.setCity(userUpdateProfileDTO.getCity());
                userAddress.setZipCode(userUpdateProfileDTO.getZipCode());
                userAddress.setAddressType(AddressType.HOME);
            } else {
                if (userUpdateProfileDTO.getAddressNumber().equals("")) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address Number not Found");
                } else if (userUpdateProfileDTO.getStreetName().equals("")) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Street Name not Found");
                } else if (userUpdateProfileDTO.getCity().equals("")) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not Found");
                } else if (userUpdateProfileDTO.getZipCode().equals("")) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Zip Code not Found");
                } else if (userUpdateProfileDTO.getZipCode().length() != 5) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Zip Code");
                } else {
                    userAddress = optionalUserAddress.get();
                    userAddress.setUserId(1L);
                    userAddress.setAddressNumber(userUpdateProfileDTO.getAddressNumber());
                    userAddress.setStreetName(userUpdateProfileDTO.getStreetName());
                    userAddress.setCity(userUpdateProfileDTO.getCity());
                    userAddress.setZipCode(userUpdateProfileDTO.getZipCode());
                    userAddress.setAddressType(AddressType.HOME);
                }
            }

            userAddressRepository.save(userAddress);

            return ResponseEntity.status(HttpStatus.OK).body("User Profile Updated Successfully!");
        }
    }
}
