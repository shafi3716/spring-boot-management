package com.shafi.springmanagement.config.auditoring;

import com.shafi.springmanagement.authentication.service.MyUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware {

    @Override
    public Optional getCurrentAuditor() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            MyUserDetails myUserDetails = (MyUserDetails) principal;
            Long userId = myUserDetails.getId();
            return Optional.of(userId);
        }
        catch (Exception ex){
            return Optional.empty();
        }
    }
}
