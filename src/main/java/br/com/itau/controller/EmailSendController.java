package br.com.itau.controller;

import br.com.itau.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSendController {

    @Autowired
    private EmailSendService emailSendService;

    @PostMapping("/envia")
    public ResponseEntity<HttpStatus> emailSendService(@RequestBody String req) throws Exception {
        String resposta = emailSendService.sendEmail();
        if (resposta.equals("200")){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

}
