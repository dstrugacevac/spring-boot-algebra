package algebra.spring_boot.auth;

import algebra.spring_boot.auth.dto.AccessTokenResponseDto;
import algebra.spring_boot.auth.dto.GenerateRefreshTokenDto;
import algebra.spring_boot.auth.dto.LoginDto;
import algebra.spring_boot.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/api/v1")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponseDto> login(@Valid @RequestBody LoginDto dto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        if (!authentication.isAuthenticated()){
            throw new UsernameNotFoundException("Wrong credentials.");
        }

        String token = jwtService.generateToken(dto.getUsername());
        AccessTokenResponseDto accessTokenResponseDto = new AccessTokenResponseDto(token);

        return ResponseEntity.ok(accessTokenResponseDto);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity generateRefreshToken(@Valid @RequestBody GenerateRefreshTokenDto dto){
        return ResponseEntity.ok(new Object());
    }
}
