package com.example.SpringProject.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateReadingListDTO {
    @NotNull
    private String name;
}
//    // Convert the signUpDTO to a signup entity
//    Signup signup = mapper.map(signupDTO, Signup.class);
//        signup.setCamper(camperRepository.findById(signupDTO.getCamperId()).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "[validation errors]")
//                ));
//                signup.setActivity(activityRepository.findById(signupDTO.getActivityId())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "[validation errors]")));
//                try {
//                signup = signupRepository.save(signup);
//                signupDTO = mapper.map(signup, SignupDTO.class);
//        signupDTO.setActivityId(signup.getActivity().getId());
//        signupDTO.setCamperId(signup.getCamper().getId());
//        return activityService.getActivity(signupDTO.getActivityId());
//        } catch (Exception e) {
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "was not created");
//        }
//        }