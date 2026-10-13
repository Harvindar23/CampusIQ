package com.campusiq.auth.dto; import com.campusiq.user.dto.UserResponse; public record AuthResponse(String token, UserResponse user){}
