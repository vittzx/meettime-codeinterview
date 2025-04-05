package com.meet.time.interview.infra.adapters.input;

import com.meet.time.interview.application.port.input.AccessTokenUseCase;
import com.meet.time.interview.domain.enums.STATUS_RESPONSE_REST_REQUEST;
import com.meet.time.interview.domain.mapper.AccessTokenRestMapper;
import com.meet.time.interview.domain.model.AccessToken;
import com.meet.time.interview.infra.adapters.input.data.response.access_token.AccessTokenResponseDTO;
import com.meet.time.interview.infra.adapters.input.data.response.access_token.UrlAuthenticationResponseDTO;
import com.meet.time.interview.infra.adapters.input.data.response.global.DefaultRestResponse;
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
    private final AccessTokenRestMapper accessTokenRestMapper;

    @GetMapping("/token")
    public ResponseEntity<DefaultRestResponse<UrlAuthenticationResponseDTO>> getAuth(HttpServletResponse response)throws IOException {
        log.debug("STARTED GET /auth/v1/token");
        String url = getAccessTokenUseCase.createRedirectUserUrl();
        log.debug("Redirecting GET /auth/v1/token to URL {}", url);
        // response.sendRedirect(url); -> serve como opção.
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultRestResponse<>(STATUS_RESPONSE_REST_REQUEST.SUCCESS, UrlAuthenticationResponseDTO.builder().url(url).build())
                );
    }


    @GetMapping("/redirect")
    public ResponseEntity<DefaultRestResponse<AccessTokenResponseDTO>> redirectAuth(@RequestParam String code){
        log.debug("STARTED GET /auth/v1/redirect");
        AccessToken accessToken = getAccessTokenUseCase.returnAccessToken(code);
        AccessTokenResponseDTO response = accessTokenRestMapper.toAccessTokenResponseDTO(accessToken);
        log.debug("FINISHED GET /auth/v1/redirect");
        return ResponseEntity.status(HttpStatus.OK).body(
                new DefaultRestResponse<>(STATUS_RESPONSE_REST_REQUEST.SUCCESS, response)
        );
    }
}
