package com.enb.curtainmanagement.admin.model;

import com.enb.curtainmanagement.common.model.BaseDomainModel;
import com.enb.curtainmanagement.auth.model.enums.UserStatus;
import com.enb.curtainmanagement.auth.model.enums.UserType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends BaseDomainModel {

    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Builder.Default
    private UserType userType = UserType.ADMIN;

    @Builder.Default
    private UserStatus userStatus = UserStatus.ACTIVE;

}
