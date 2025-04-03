package com.meet.time.interview.infra.adapters.input;

import com.meet.time.interview.application.port.input.AccessTokenUseCase;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
@Slf4j
public class OAuthtRestController {

    private final AccessTokenUseCase getAccessTokenUseCase;

    @GetMapping("/token")
    public void getAuth(HttpServletResponse response)throws IOException {
        log.debug("STARTED GET /auth/v1/token");
        String url = getAccessTokenUseCase.createRedirectUserUrl();
        log.debug("FINISHED GET /auth/v1/token");
        log.debug("Redirecting to URL {}", url);
        response.sendRedirect(url);
    }


    @GetMapping("/redirect")
    public ResponseEntity<Void> redirectAuth(@RequestParam String code){
        log.debug("STARTED GET /auth/v1/redirect");
        log.debug("Code {}", code);
        getAccessTokenUseCase.returnAccessToken(code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
