package com.denteconvenio.planoservice.infra.security;

import java.util.UUID;


public record UserDTO(

    UUID id,
    String username,
    String email,
    String tipo
    
) {}