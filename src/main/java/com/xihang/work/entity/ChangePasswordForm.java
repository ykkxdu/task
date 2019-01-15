package com.xihang.work.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordForm {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    public ChangePasswordForm() {
    }
}
